package com.summer.mypay.controller.websocket;

import com.alibaba.fastjson.JSONObject;
import com.summer.mypay.pojo.ClientMessage;
import com.summer.mypay.pojo.ReturnResult;
import com.summer.mypay.pojo.WebSocketResult;
import com.summer.mypay.service.websocket.WebSocketService;
import com.summer.mypay.view.JsonView;
import com.summer.mypay.view.WebsocketView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


/**
 * websocket的controller层
 * <p>
 * <p>
 * 当外界请求websocket数据时,得到请求的参数会包装成ClientMessage对像
 * clientName: 请求客户端
 * mid:        请求id全局唯一
 * content:    json串格式的请求内容
 * <p>
 * 返回数据永远为 ReturnResult 格式, 在视图解释器里做操作
 */
@RestController
public class WebsocketController {


    @Autowired
    private WebSocketService webSocketService;

    /**
     * 请求二维码
     *
     * @param clientName    请求客户端
     * @param money         金额
     * @param mark          自定义交易号
     * @return
     */
    @RequestMapping("requestQR")
    public Object requestQR(@RequestParam("clientName") String clientName, @RequestParam("money") String money, @RequestParam("mark") String mark) {

        JSONObject params = new JSONObject();
        params.put("clientName", clientName);
        params.put("money", money);
        params.put("mark", mark);

        ClientMessage clientMessage = new ClientMessage(clientName, params.toJSONString());
        clientMessage.setType(ClientMessage.qr_query);
        ReturnResult tmpResult = webSocketService.sendMessage(clientMessage);

        if (tmpResult.getCode() != 200) {
            return new ModelAndView(JsonView.BEANNAME, new WebSocketResult(clientMessage.getMid(), JSONObject.toJSONString(tmpResult)));
        }
        return new ModelAndView(WebsocketView.BEANNAME, new WebSocketResult(clientMessage.getMid(), null));
    }


    /**
     * 请求交易信息
     *
     * @param clientName    请求客户端
     * @param mark          自定义交易号
     * @return
     */
    @RequestMapping("requestOrderInfo")
    public ModelAndView requestOrderInfo(@RequestParam("clientName") String clientName, @RequestParam("mark") String mark) {

        JSONObject params = new JSONObject();
        params.put("clientName", clientName);
        params.put("mark", mark);

        ClientMessage clientMessage = new ClientMessage(clientName, params.toJSONString());
        clientMessage.setType(ClientMessage.orderInfo);
        ReturnResult tmpResult = webSocketService.sendMessage(clientMessage);

        if (tmpResult.getCode() != 200) {
            return new ModelAndView(JsonView.BEANNAME, new WebSocketResult(clientMessage.getMid(), JSONObject.toJSONString(tmpResult)));
        }
        return new ModelAndView(WebsocketView.BEANNAME, new WebSocketResult(clientMessage.getMid(), null));
    }



}
