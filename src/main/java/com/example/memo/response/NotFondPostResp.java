package com.example.memo.response;

import lombok.Getter;

@Getter
public class NotFondPostResp {
    private final String msg;

    public NotFondPostResp(){
        this.msg = "해당 페이지가 없습니다.";
    }

    public NotFondPostResp(String msg) {
        this.msg = msg;
    }


}
