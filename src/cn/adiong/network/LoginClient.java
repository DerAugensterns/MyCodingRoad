package cn.adiong.network;

import java.io.*;
import java.net.Socket;

/**
 * @Author: 阿威
 * @Date: 2020/8/18 9:51
 * @Description： 1、指定服务器ip和端口 使用Socket创建客户端
 * 2、io操作
 * 3、释放资源
 */
public class LoginClient {
    public static void main(String[] args) throws IOException {
        System.out.println("-----Loginclient------");
        Socket client = new Socket("localhost", 9909);

        //想服务器发送登录信息 考虑先封装再发送
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("请输入用户名：");
        String uname = console.readLine();
        System.out.println("请输入密码：");
        String upwd = console.readLine();
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        dos.writeUTF("uname=" + uname + "&" + "upwd=" + upwd);
        dos.flush();

        //返回验证结果
        DataInputStream dis = new DataInputStream(client.getInputStream());
        String result = dis.readUTF();
        System.out.println(result);

        //释放资源
        dos.close();
        dis.close();
        client.close();

    }
}
