package cn.adiong.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: 阿威
 * @Date: 2020/8/20 9:56
 * @Description：
 */
public class LoginThreadServer {

    public static void main(String[] args) throws IOException {
        System.out.println("-------LoginThreadServer-----");
        ServerSocket server = new ServerSocket(9099);
        //多线程控制
        boolean isRunning = true;

        while (isRunning) {
            new Thread(new channel(server.accept())).start();
        }
        server.close();
    }

    /**
     * 一个channel就是一个通信道（链接）
     */
    static class channel implements Runnable {

        Socket client;
        DataInputStream dis;
        DataOutputStream dos;
        String uname;
        String upwd;

        public channel(Socket client) {
            this.client = client;
            try {
                this.dis = new DataInputStream(client.getInputStream());
                this.dos = new DataOutputStream(client.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            String[] datas = new String[0];
            try {
                datas = dis.readUTF().split("&");
            } catch (IOException e) {
                e.printStackTrace();
            }
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
                send(" Login Success");
            } else {
                send("Login Failure");
            }
            try {
                dos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            release();
        }


        /**
         * 发送验证结果
         *
         * @param msg
         */
        public void send(String msg) {
            try {
                dos.writeUTF(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        /**
         * 释放资源
         */
        public void release() {
            //流对象初始化为null
            if (dis != null) {
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (dos != null) {
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}


