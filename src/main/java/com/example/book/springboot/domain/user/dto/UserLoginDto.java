package com.example.book.springboot.domain.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class UserLoginDto {
    private final String id;
    private final String password;
}
