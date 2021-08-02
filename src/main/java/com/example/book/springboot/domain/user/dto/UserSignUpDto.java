package com.example.book.springboot.domain.user.dto;

import com.example.book.springboot.domain.user.User;
import com.example.book.springboot.utils.PasswordController;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class UserSignUpDto {

    private String id;
    private String password;
    private String name;
    private String phone;

    public UserSignUpDto(String id, String password, String name, String phone) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }
    public User toEntity(){

        return User.builder()
                .uid(id)
                .passwd(PasswordController.encode(password))
                .name(name)
                .phone(phone)
                .roles(Collections.singletonList("ROLE_USER"))
                .build();

    }
}
