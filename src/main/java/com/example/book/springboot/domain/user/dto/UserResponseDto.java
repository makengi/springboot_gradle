package com.example.book.springboot.domain.user.dto;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class UserResponseDto {

    private Long id;
    private String uid;
    private String name;

    @Builder
    public UserResponseDto(Long id,String uid, String name){
        this.id = id;
        this.uid = uid;
        this.name = name;
    }
}
