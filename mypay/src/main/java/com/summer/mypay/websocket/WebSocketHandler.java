package com.summer.mypay.websocket;


import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.summer.mypay.pojo.ClientMessage;
import com.summer.mypay.pojo.ReturnResult;
import com.summer.mypay.service.websocket.WebSocketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;

@Service
public class WebSocketHandler extends TextWebSocketHandler {


    Map<String, WebSocketSession> sessionMap = Maps.newHashMap();

    Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private WebSocketService webSocketService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        logger.debug("收到信息: {}", message.getPayload());

        ClientMessage clientMessage = JSONObject.parseObject(message.getPayload(), ClientMessage.class);

        //当为0的时候表示初始化信息
        if (clientMessage.getType() == ClientMessage.init) {
            //加入session到缓存
            sessionMap.put(clientMessage.getClientName(), session);
            return;
        } else if (clientMessage.getType() == ClientMessage.living) {
            logger.debug("收到 设备: {} 保存活信息", clientMessage.getClientName());
            return;
        }

        logger.debug("收到 设备: {} 业务信息: {}", clientMessage.getClientName(), clientMessage.getContent());

        //收到其它信息->收到其它信息写入结果集合
        webSocketService.writeWebScoketResult(clientMessage.getMid(), clientMessage.getContent());
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        logger.debug("创建websocket成功sessionid为:{} " + session.getId());
    }

    //发送信息
    public ReturnResult sendMessage(ClientMessage clientMessage) {

        logger.debug("向设备:{} 发送请求: {}", clientMessage.getClientName(), JSONObject.toJSONString(clientMessage));
        System.out.println("向设备:" + clientMessage.getClientName() + " 发送请求: " + JSONObject.toJSONString(clientMessage));

        try {
            if (sessionMap.get(clientMessage.getClientName()) != null) {
                sessionMap.get(clientMessage.getClientName()).sendMessage(new TextMessage(JSONObject.toJSONString(clientMessage)));
            } else {
                return new ReturnResult(-1, "该设置连接未建立,不可使用");
            }
        } catch (IOException e) {
            logger.error("发送信息到设备: {} 失败,内容为: {}", clientMessage.getClientName(), JSONObject.toJSONString(clientMessage));
        }
        return new ReturnResult();
    }


    public WebSocketHandler() {
    }


}