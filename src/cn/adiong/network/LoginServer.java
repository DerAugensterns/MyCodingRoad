package cn.adiong.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: 阿威
 * @Date: 2020/8/18 9:50
 * @Description： 1、指定端口 使用ServerSocket创建服务器
 * 2、阻塞式等待连接 accept（）方法 建立通信道
 * 3、io操作
 * 4、释放资源
 */
public class LoginServer {
    public static void main(String[] args) throws IOException {
        System.out.println("-----Loginserver------");
        ServerSocket server = new ServerSocket(9909);
        Socket client = server.accept();

        //数据分析 验证 返回结果  数据分析要耐心 弄清楚每一步在做什么
        String uname = "";
        String upwd = "";
        DataInputStream dis = new DataInputStream(client.getInputStream());
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        String[] datas = dis.readUTF().split("&");
        for (String info : datas
        ) {
            String[] userInfo = info.split("=");
            if (userInfo[0].equals("uname")) {
                uname = userInfo[1];
            } else if (userInfo[0].equals("upwd")) {
                upwd = userInfo[1];
            }
        }
        if (uname.equals("adiong") && upwd.equals("adiong")) {
            dos.writeUTF("登陆成功");
        } else {
            dos.writeUTF("登陆失败");
        }
        dos.flush();

        //释放资源
        dos.close();
        dis.close();
        server.close();
    }
}
