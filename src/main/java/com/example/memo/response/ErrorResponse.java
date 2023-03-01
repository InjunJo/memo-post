package com.example.memo.response;

public class ErrorResponse {

    private String msg;

    public ErrorResponse(String msg){
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
