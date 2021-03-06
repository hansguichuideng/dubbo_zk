package com.summer.mypay.view;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import com.summer.mypay.pojo.ReturnResult;
import com.summer.mypay.pojo.WebSocketResult;
import com.summer.mypay.service.websocket.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;


/**
 * webscoket视图解释器
 * <p>
 * <p>
 * 前提,当外界访问最终会到客户端上请求的接口的时候,系统会直接调用websocket,所以出现问题: 外界调用session与系统访问websocket不在一个会话之中
 * <p>
 * 处理方式:
 * 1:外界访问到系统时,系统会为这个访问配置一个id,该id唯一
 * 2:系统调用相应的websocket发送请求到客户端,客户端返回后把请求结果加入到请求返果集中 (WebSocketService类中定义是 map集合)
 * 3:在该视图解析器中,等待30秒,查看是否有相应的返回,如果有则包装接果并返回,如果没有,则返回相应结果
 */

@Configuration
public class WebsocketView implements View {
    public static String BEANNAME = "websocketView";


    @Autowired
    private WebSocketService webSocketService;

    @Override
    public String getContentType() {
        return "text/json";
    }


    /**
     * @param map                 返回的结果,返回的结果中,一定用有一个 mid 的唯一标识
     * @param httpServletRequest
     * @param httpServletResponse
     */
    @Override
    public void render(Map<String, ?> map, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {


        httpServletResponse.setCharacterEncoding(Charsets.UTF_8.name());
        httpServletResponse.setContentType("text/json; charset=utf-8");

        PrintWriter printWriter = null;
        try {
            printWriter = httpServletResponse.getWriter();
            String mid = map.get(WebSocketResult.mid).toString();


            //现在开始等每一秒查看返回结果集
            int time = 0;
            while (webSocketService.readWebScoketResult(mid) == null && time <= 30) {
                try {
                    time++;
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
            }


            Object or = webSocketService.readWebScoketResult(mid);
            if (or == null) {
                printWriter.print(JSONObject.toJSONString(new ReturnResult(-1, "对不起,系统正忙,请稍候再试")));
                return;
            }
            String result = webSocketService.readWebScoketResult(mid).toString();

            printWriter.print(JSONObject.parseObject(result));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (printWriter != null) {
                printWriter.flush();
                printWriter.close();
            }
        }
    }

}
