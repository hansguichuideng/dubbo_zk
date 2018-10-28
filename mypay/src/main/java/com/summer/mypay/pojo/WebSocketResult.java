package com.summer.mypay.pojo;

import java.util.HashMap;

public class WebSocketResult extends HashMap<String, String> {


    public static String mid = "mid";
    public static String content = "content";

    public WebSocketResult(String _mid, String _content) {

        super.put(mid, _mid);
        super.put(content, _content);
    }


    public static void main(String[] args) {
        System.out.println(new WebSocketResult("1", "hello my test"));
    }
}
