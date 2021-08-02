package com.example.book.springboot.domain.file.controller;

import com.example.book.springboot.domain.file.CustomFile;
import com.example.book.springboot.domain.file.RemoteStoreInfo;
import com.example.book.springboot.domain.file.RemoteUser;
import com.example.book.springboot.domain.file.service.FileDownloadService;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/files")
@RequiredArgsConstructor
@Slf4j
public class FileDownController {

    private final FileDownloadService service;

    @GetMapping
    public List<CustomFile> havingFileList(RemoteStoreInfo store, RemoteUser user){
        return service.findFiles(store,user);

    }
    @GetMapping("/download")
    public CustomFile downloadOneFile(RemoteStoreInfo store, RemoteUser user){
        try {
          log.info("@ user: {}", user.toString());
          return service.downloadOne(store,user);
        }catch (IOException | SftpException | JSchException e){
            e.printStackTrace();
        }
        return null;
    }

}
