package com.summer.mypay.controller;

import com.google.common.collect.Maps;
import com.summer.mypay.pojo.ReturnResult;
import com.summer.mypay.pojo.WebSocketResult;
import com.summer.mypay.view.WebsocketView;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RestController
public class TestController {


    @RequestMapping("test2")
    @ResponseBody
    public Object test2() {
        return new ReturnResult();
    }

    @RequestMapping("test")
    public ModelAndView test() {
//        Request request = new Request.Builder().url("ws://127.0.0.1:18888/channel").build();
//
//        OkHttpClient okHttpClient = new OkHttpClient();
//
//        WebSocket webSocket = okHttpClient.newWebSocket(request, new WebSocketListener() {
//            @Override
//            public void onOpen(WebSocket webSocket, Response response) {
//                super.onOpen(webSocket, response);
//            }
//
//            @Override
//            public void onMessage(WebSocket webSocket, String text) {
//                super.onMessage(webSocket, text);
//            }
//
//            @Override
//            public void onMessage(WebSocket webSocket, ByteString bytes) {
//                super.onMessage(webSocket, bytes);
//            }
//
//            @Override
//            public void onClosing(WebSocket webSocket, int code, String reason) {
//                super.onClosing(webSocket, code, reason);
//            }
//
//            @Override
//            public void onClosed(WebSocket webSocket, int code, String reason) {
//                super.onClosed(webSocket, code, reason);
//            }
//
//            @Override
//            public void onFailure(WebSocket webSocket, Throwable t, @Nullable Response response) {
//                super.onFailure(webSocket, t, response);
//            }
//        });
//
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                ThreadUtil.sleep(5000);
//                webSocket.send("iweifeihwfi");
//            }
//        }).start();

        Map<String, String> params = Maps.newHashMap();

        params.put("name", "hello");

        return new ModelAndView(WebsocketView.BEANNAME, new WebSocketResult("1", "hello my test"));


    }


}
