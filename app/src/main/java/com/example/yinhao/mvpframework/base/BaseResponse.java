package com.example.yinhao.mvpframework.base;

public class BaseResponse<T> {
    private int code;
    private String message;
    private T body;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return body;
    }

    public void setData(T data) {
        this.body = data;
    }
}
