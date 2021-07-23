package com.example.book.springboot.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
public class PasswordController {

    private static PasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String encode(String password){
        return encoder.encode(password);
    }

    public static boolean match(String origin, String target){
        log.info("@ password: {}, {}", origin, target);
        return encoder.matches(origin, target);
    }
}
