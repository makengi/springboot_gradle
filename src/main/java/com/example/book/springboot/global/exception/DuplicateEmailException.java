package com.example.book.springboot.global.exception;

import lombok.Getter;

@Getter
public class DuplicateEmailException extends RuntimeException{

    private String email;
    private String field;

    public DuplicateEmailException(String email){
        this.field ="email";
        this.email = email;
    }
}
