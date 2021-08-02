package com.example.book.springboot.domain.file.service;

import com.example.book.springboot.domain.connector.JSchConnector;
import com.example.book.springboot.domain.file.CustomFile;
import com.example.book.springboot.domain.file.RemoteStoreInfo;
import com.example.book.springboot.domain.file.RemoteUser;
import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

@Service
@Slf4j
public class FileDownloadService implements DownloadService{

    private JSch jSch;
    private Properties properties;
    private Session session;

    @Autowired
    public FileDownloadService(JSchConnector connector){
        this.jSch = connector.getJSch();
        this.properties = connector.getProperties();
    }



    @Override
    public List<CustomFile> findFiles(RemoteStoreInfo store, RemoteUser user) {
        try {
            session = jSch.getSession(user.getName(), store.getAddress(), store.getPort());
            session.setPassword(user.getPassword());
        }catch (JSchException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CustomFile> selectedFilesDownload() {
        return null;
    }



    @Override
    public CustomFile downloadOne(RemoteStoreInfo store, RemoteUser user) throws IOException, SftpException, JSchException {

        Channel channel = null;
        Session session = null;
        try{
            log.info("@ name, address, port, password {}, {}, {}, {}",user.getName(), store.getAddress(),store.getPort(),user.getPassword());
            session = jSch.getSession(user.getName(),store.getAddress(),store.getPort());
            session.setPassword(user.getPassword());
            log.info("@ properties: {}",properties.toString());
            session.setConfig(properties);
            session.connect();
            log.info("@ Connection established...");
            log.info("@ Creating SFTP Channel...");
            channel = session.openChannel("sftp");
            channel.connect();
        }catch (Exception e){
            e.printStackTrace();
        }
        readFile((ChannelSftp) channel, session);
        return null;
    }

    private void readFile(ChannelSftp channel, Session session) throws IOException {
        InputStream inputStream = null;
        ChannelSftp channelSftp;
        channelSftp = channel;
        FileOutputStream fileoutputStream = null;
        try {
            channelSftp.chmod(0777,"/home/imr/bag");
            channelSftp.cd("/home/imr/bag");
            channelSftp.ls("/home/imr/bag").forEach(file->log.info("@file: {}",file));
            inputStream = channelSftp.get("in_left.bag");
            File file = new File("D:\\ftp_files\\in_left.bag");
            fileoutputStream = new FileOutputStream(file);
            int i;

            while ((i = inputStream.read()) != -1) {
                fileoutputStream.write(i);
            }
            log.info("@ file copy sucecss..");
        }catch (IOException | SftpException e ){
            e.printStackTrace();
        }finally {
            fileoutputStream.close();
            inputStream.close();
            channelSftp.quit();
            channelSftp.disconnect();
            session.disconnect();
        }
    }

    ;
}
