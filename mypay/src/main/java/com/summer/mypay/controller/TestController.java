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


    public static void main(String[] args) {
        String s = "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\",\"type\":6}//qr.alipay.com/fkx05438j437empjrbojr0a?t=1541141829896\\\\\\\"}\\\"}\",\"mid\":\"465201959198443_1541141829122\",\"type\":2}";


        System.out.println(s.length());
        System.out.println(s.contains("收款到账"));
        System.out.println(s.contains("收款金额"));


    }

}
