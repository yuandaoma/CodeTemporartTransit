//package com.bporcv.ftppool;
//
//import org.apache.commons.net.ftp.FTPClient;
//
//import java.io.IOException;
//
//public class FTPClientHolder {
//
//    private String hostname;
//    private Integer port;
//    private String username;
//    private String password;
//
//    private FTPClient ftpClient;
//
//
//    public FTPClientHolder(String hostname, Integer port, String username, String password) {
//        this.hostname = hostname;
//        this.port = port;
//        this.username = username;
//        this.password = password;
//        this.ftpClient = new FTPClient();
//        try {
//            this.ftpClient.connect(this.hostname, this.port);
//            this.ftpClient.login(this.username, this.password);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//}
