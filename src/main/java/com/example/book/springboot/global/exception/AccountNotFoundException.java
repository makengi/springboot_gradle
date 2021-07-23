package com.example.book.springboot.global.exception;

import javafx.scene.control.Accordion;
import lombok.Getter;

@Getter
public class AccountNotFoundException extends RuntimeException{
    private Long id;
    private String email;

    public AccountNotFoundException(Long id){
        this.id = id;
    }

    public AccountNotFoundException(String email){
        this.email = email;
    }
}
