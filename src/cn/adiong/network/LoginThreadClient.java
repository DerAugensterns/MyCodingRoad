package cn.adiong.network;

import java.io.*;
import java.net.Socket;

/**
 * @Author: 阿威
 * @Date: 2020/8/20 9:57
 * @Description：
 */
public class LoginThreadClient {

    public static void main(String[] args) throws IOException {
        System.out.println("---------LoginThreadClient--------");
        Socket client = new Socket("localhost", 9099);
        new Sender(client).send();
        new Receiver(client).receive();
    }

    static class Sender implements Runnable {
        private Socket client;
        private DataOutputStream dos;
        private BufferedReader console;
        private String msg;

        public String init() {
            System.out.println("请输入用户名：");
            String uname = null;
            try {
                uname = console.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("请输入密码：");
            String upwd = null;
            try {
                upwd = console.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "uname=" + uname + "&" + "upwd=" + upwd;
        }

        public Sender(Socket client) {
            this.client = client;
            this.console = new BufferedReader(new InputStreamReader(System.in));
            msg = init();
            try {
                dos = new DataOutputStream(client.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void send() {
            try {
                dos.writeUTF(msg);
                dos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {

        }
    }

    static class Receiver implements Runnable {
        private Socket client;
        private DataInputStream dis;

        public Receiver(Socket client) {
            this.client = client;
            try {
                dis = new DataInputStream(client.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void receive() {
            String result = null;
            try {
                result = dis.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(result);
        }

        @Override
        public void run() {

        }
    }
}



