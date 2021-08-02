package com.example.book.springboot.domain.file.service;

import com.example.book.springboot.domain.connector.JSchConnector;
import com.example.book.springboot.domain.file.RemoteStoreInfo;
import com.example.book.springboot.domain.file.RemoteUser;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Date;


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class FileDownloadServiceTest {


    private FileDownloadService downloadService;

    @BeforeEach
    void setUp() {
        JSchConnector connector = new JSchConnector();
        connector.setNoHostKeySetting();
        downloadService = new FileDownloadService(connector);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findFiles() {
    }

    @Test
    void selectedFilesDownload() {
    }

    @Test
    void downloadOne() throws JSchException, SftpException, IOException {
        Assertions.assertThat(downloadService).isNotNull();
        RemoteStoreInfo remoteStoreInfo = RemoteStoreInfo.builder().now(new Date()).address("223.171.77.204").port(22).build();
        RemoteUser remoteUser = RemoteUser.builder().name("imr").password("8922").build();
        downloadService.downloadOne(remoteStoreInfo,remoteUser);
    }
}