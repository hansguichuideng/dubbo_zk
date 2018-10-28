package com.summer.mypay.pojo;

/**
 * 客户端发送到系统的pojo类,要求,每个客户端信息要带上clientName字段
 */
public class ClientMessage {

    public static int init = 0;
    public static int living = 1;       //保活
    public static int qr_query = 2;     //二维码请求
    public static int orderInfo = 3;    //查看订单信息

    private String clientName;

    private Integer type;   //0:表示初始化, 1:保活信息 2:业务信息需要逻辑处理, 3二维码请求


    private String mid;

    private String content;


    public ClientMessage() {
    }

    public ClientMessage(String clientName, String content) {
        this.clientName = clientName;
        this.mid = new StringBuffer(clientName).append("_").append(System.currentTimeMillis()).toString();
        this.content = content;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }
}
