package com.example.memo.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter @RequiredArgsConstructor
public class ReqPostDto {

    private final String head;

    private final String content;


}
