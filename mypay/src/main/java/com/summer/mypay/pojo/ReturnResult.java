package com.summer.mypay.pojo;

public class ReturnResult {

    private int code = 200;

    private Object data;

    private String msg;


    public ReturnResult() {
    }

    public ReturnResult(Object data) {
        this.data = data;
    }

    public ReturnResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
