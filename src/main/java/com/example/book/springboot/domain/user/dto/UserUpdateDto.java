package com.example.book.springboot.domain.user.dto;

import com.example.book.springboot.domain.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.h2.engine.UserBuilder;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserUpdateDto {

    /*
         핸드폰 번호만 업데이트
     */
    private Long id;
    private String phone;


}
