package com.example.book.springboot.global.exception;

import lombok.Getter;

public enum ErrorCode {

    NOT_NULL("ERROR_CODE_0001","필수값이 누락되었습니다",400)
    ,MIN_VALUE("ERROR_CODE_0002","요청한 정보가 최소값보다 커야합니다.",400),
    ACCOUNT_NOT_FOUND("USER_ERROR_0001","해당 회원을 찾을 수 없습니다.",400),
    EMAIL_DUPLICATE("USER_ERROR_0002","이메일이 중복되었습니다.",400),
    EMAIL_INVALID("USER_ERROR_0003", "이메일이 정확하지 않습니다.",409),
    INPUT_VALUE_INVALID("GLOBAL_ERROR_9001","입력값이 올바르지 않습니다.",400);

    @Getter
    private String code;

    @Getter
    private String message;

    @Getter
    private  int status;

    ErrorCode(String code, String message, int status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
