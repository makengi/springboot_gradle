package com.example.book.springboot.domain.file;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
public class RemoteStoreInfo {
    private List<CustomFile> fileList;
    private Date now;
    private String address;
    private int port;

    @Builder
    public RemoteStoreInfo(Date now,String address, int port){
        this.now = now;
        this.address = address;
        this.port = port;
    }
}
