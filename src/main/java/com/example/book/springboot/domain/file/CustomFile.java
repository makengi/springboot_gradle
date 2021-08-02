package com.example.book.springboot.domain.file;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sun.net.ftp.FtpClient;

import java.util.Date;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomFile {

    private String localName;
    private String localPath;
    private int size;
}
