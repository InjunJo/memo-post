package com.example.memo.response;

import lombok.Getter;

@Getter
public class UnAuthorizedResp {

    private final String msg;

    public UnAuthorizedResp(){
        this.msg = "비밀번호가 일치하지 않습니다";
    }

    public UnAuthorizedResp(String msg) {
        this.msg = msg;
    }
}
