package cn.adiong.network.ChatRoom;

import java.io.*;
import java.net.Socket;

/**
 * @Author: 阿威
 * @Date: 2020/8/22 9:35
 * @Description：
 */
public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println("------ChatClient--------");
        Socket client = new Socket("localhost", 9099);
        new Thread(new Sender(client)).start();
        new Thread(new Receiver(client)).start();
    }

    static class Sender implements Runnable {
        private Socket client;
        private DataOutputStream dos;
        private BufferedReader console;
        private boolean isRunning = true;

        public Sender(Socket client) {
            this.client = client;
            console = new BufferedReader(new InputStreamReader(System.in));
            try {
                dos = new DataOutputStream(client.getOutputStream());
            } catch (IOException e) {
                release();
            }

        }

        public void send() {
            String msg = null;
            try {
                msg = console.readLine();
                dos.writeUTF(msg);
                dos.flush();
            } catch (IOException e) {
                release();
            }
        }

        @Override
        public void run() {
            while (isRunning) {
                send();
            }
        }

        private void release() {

            isRunning = false;

            if (null != dos) {
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != client) {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Receiver implements Runnable {

        private Socket client;
        private DataInputStream dis;
        private boolean isRunning = true;

        public Receiver(Socket client) {
            this.client = client;
            try {
                dis = new DataInputStream(client.getInputStream());
            } catch (IOException e) {
                release();
            }
        }

        public void receive() {
            String msg = null;
            try {
                msg = dis.readUTF();
            } catch (IOException e) {
                release();
            }
            System.out.println(msg);
        }

        @Override
        public void run() {
            while (isRunning) {
                receive();
            }
        }

        private void release() {
            isRunning = false;

            if (null != dis) {
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != client) {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
