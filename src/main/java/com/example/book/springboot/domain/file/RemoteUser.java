package com.example.book.springboot.domain.file;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RemoteUser {
    private String name;
    private String password;

    @Builder
    public RemoteUser(String name,String password){
        this.name = name;
        this.password = password;
    }
}
