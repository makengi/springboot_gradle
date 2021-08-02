package com.example.book.springboot.domain.file.service;

import com.example.book.springboot.domain.file.CustomFile;
import com.example.book.springboot.domain.file.RemoteStoreInfo;
import com.example.book.springboot.domain.file.RemoteUser;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import sun.net.ftp.FtpProtocolException;

import java.io.IOException;
import java.util.List;

public interface DownloadService {
    public List<CustomFile> findFiles(RemoteStoreInfo store, RemoteUser user);
    public List<CustomFile> selectedFilesDownload();
    public CustomFile downloadOne(RemoteStoreInfo store, RemoteUser user) throws IOException, SftpException, JSchException;
}
