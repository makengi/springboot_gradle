package com.example.book.springboot.domain.user.service;

import com.example.book.springboot.domain.user.User;
import com.example.book.springboot.domain.user.dto.UserResponseDto;
import com.example.book.springboot.domain.user.dto.UserSignUpDto;

import java.util.List;

public interface UserServiceInf {
    public User save(UserSignUpDto signUpDto);
    public User findByUid(String uid);
    public UserResponseDto getOne(Long id);
    public List<UserResponseDto> getAll();
}
