package cn.adiong.network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: 阿威
 * @Date: 2020/8/19 10:48
 * @Description：
 */
public class FileServer {
    public static void main(String[] args) throws IOException {
        System.out.println("---------Fileserver----------");

        //创建服务器
        ServerSocket server = new ServerSocket(9909);
        //阻塞接收客户端
        Socket client = server.accept();

        //文件接收存储
        InputStream is = new BufferedInputStream(client.getInputStream());
        OutputStream os = new BufferedOutputStream(new FileOutputStream("src/tcp.jpg"));
        int len = -1;
        byte[] flush = new byte[1024 * 10];
        while ((len = is.read(flush)) != -1) {
            os.write(flush, 0, len);
        }
        os.flush();

        //释放资源
        os.close();
        is.close();
        server.close();

    }
}
