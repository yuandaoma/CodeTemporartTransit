//package com.bporcv.test1;
//
//
//import org.apache.commons.net.ftp.FTPClient;
//import org.apache.commons.pool.KeyedPoolableObjectFactory;
//
//public class TestKeyPoolableFactory implements KeyedPoolableObjectFactory<String, FTPClient> {
//
//
//    //重新初始化实例返回池
//    @Override
//    public void activateObject(String arg0, FTPClient arg1) throws Exception {
//        System.out.println("[activateObject] -->  ftp connection");
//        String hostname;
//        Integer port = 21;
//        String username;
//        String password;
//        switch (arg0){
//            case "1":
//                hostname = "192.168.56.129";
//                username = "da";
//                password = "da";
//                break;
//            case "2":
//                hostname = "10.10.0.152";
//                username = "ftpuser";
//                password = "xdkj@2018";
//                break;
//            default:
//                throw new IllegalArgumentException("参数错误");
//        }
//        arg1.connect(hostname,port);
//        arg1.login(username,password);
//    }
//
//    //销毁被破坏的实例
//    @Override
//    public void destroyObject(String arg0, FTPClient arg1) throws Exception {
//        System.out.println("[destroyObject] -->  ftp connection");
//        arg1 = null;
//    }
//
//    //创建一个实例到对象池
//    @Override
//    public FTPClient makeObject(String arg0) throws Exception {
//        System.out.println("[makeObject] --> create ftp connection");
//        FTPClient ftpClient = new FTPClient();
//        String hostname;
//        Integer port = 21;
//        String username;
//        String password;
//        switch (arg0){
//            case "1":
//                hostname = "192.168.56.129";
//                username = "da";
//                password = "da";
//                break;
//            case "2":
//                hostname = "10.10.0.152";
//                username = "ftpuser";
//                password = "xdkj@2018";
//                break;
//            default:
//                throw new IllegalArgumentException("参数错误");
//        }
//        ftpClient.connect(hostname,port);
//        boolean login = ftpClient.login(username, password);
//        if (login){
//            return ftpClient;
//        }else {
//            throw new IllegalStateException("can not connect to ftp server");
//        }
//        //这里从数据库里查询出使用次数最少的配置
//
//    }
//
//    //取消初始化实例返回到空闲对象池
//    @Override
//    public void passivateObject(String arg0, FTPClient arg1) throws Exception {
//        System.out.println("[passivateObject] -->  ftp connection");
//        arg1.disconnect();
//    }
//
//    //验证该实例是否安全 true:正在使用
//    @Override
//    public boolean validateObject(String arg0, FTPClient arg1) {
//        System.out.println("[validateObject] -->  ftp connection");
//        //这里可以判断实例状态是否可用
//        return arg1.isConnected();
//    }
//}