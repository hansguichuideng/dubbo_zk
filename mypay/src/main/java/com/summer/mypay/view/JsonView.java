package com.summer.mypay.view;

import com.google.common.base.Charsets;
import com.summer.mypay.pojo.WebSocketResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;


@Configuration
public class JsonView implements View {


    public static String BEANNAME = "jsonView";

    Logger logger = LoggerFactory.getLogger(this.getClass());


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

        logger.debug("调用 json 类解析器");

        httpServletResponse.setCharacterEncoding(Charsets.UTF_8.name());
        httpServletResponse.setContentType("text/json; charset=utf-8");

        PrintWriter printWriter = null;
        try {
            printWriter.print(map.get(WebSocketResult.content));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (printWriter != null) {
                printWriter.flush();
                printWriter.close();
            }
        }
    }

}
