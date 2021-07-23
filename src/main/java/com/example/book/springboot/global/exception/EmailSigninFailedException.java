package com.example.book.springboot.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class EmailSigninFailedException extends RuntimeException{
    private final String email;
    private final String field = "email";

    public EmailSigninFailedException(String email){
        this.email = email;
    }
}
