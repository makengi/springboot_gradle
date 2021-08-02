package com.example.book.springboot.config;

import com.example.book.springboot.domain.connector.JSchConnector;
import com.jcraft.jsch.JSch;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;


@Configuration
public class FTPConnectionConfiguration {

    @Bean
    public JSchConnector connector(){
        JSchConnector connector = new JSchConnector();
        connector.setNoHostKeySetting();
        return connector;
    }
}
