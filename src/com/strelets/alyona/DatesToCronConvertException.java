package com.strelets.alyona;

public class DatesToCronConvertException extends Exception {
    private String msg = "";

    public DatesToCronConvertException(String code, String message) {
        super(message);
        this.setMsg(code);
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}