package com.example.memo.execption;

import com.example.memo.service.UserService;

/**
 * {@link UserService}에서 회원 가입을 위해 요청 받은 Username이 중복 될 경우 던져 진다.
 */
public class DuplicateUserException extends RuntimeException {

    public DuplicateUserException() {
    }

    public DuplicateUserException(String message) {
        super(message);
    }

}
