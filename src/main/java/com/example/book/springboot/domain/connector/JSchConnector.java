package com.example.book.springboot.domain.connector;

import com.jcraft.jsch.JSch;
import lombok.Getter;
import lombok.Setter;

import java.util.Properties;

@Getter
public class JSchConnector {
    private JSch jSch;
    private Properties properties;

    public JSchConnector(){
        this.jSch = new JSch();
        this.properties = new Properties();
    }

    public void setNoHostKeySetting(){
        properties.put("StrictHostKeyChecking", "no");
    }
}
