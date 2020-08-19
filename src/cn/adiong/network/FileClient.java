package cn.adiong.network;

import java.io.*;
import java.net.Socket;

/**
 * @Author: 阿威
 * @Date: 2020/8/19 10:48
 * @Description：
 */
public class FileClient {
    public static void main(String[] args) throws IOException {
        System.out.println("-----Fileclient------");

        //创建链接
        Socket client = new Socket("localhost", 9909);

        //文件上传
        InputStream is = new BufferedInputStream(new FileInputStream("src/324.jpg"));
        OutputStream os = new BufferedOutputStream(client.getOutputStream());
        int len = -1;
        byte[] flush = new byte[1024 * 10];
        while ((len = is.read(flush)) != -1) {
            os.write(flush, 0, len);
        }
        os.flush();

        //释放资源
        os.close();
        is.close();
        client.close();
    }
}
