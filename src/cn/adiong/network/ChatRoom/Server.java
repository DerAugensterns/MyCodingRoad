package cn.adiong.network.ChatRoom;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author: 阿威
 * @Date: 2020/8/22 9:36
 * @Description：
 */
public class Server {

    /**
     * 链接容器管理
     */
    private static CopyOnWriteArrayList<Channel> channels = new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws IOException {
        System.out.println("------ChatServer--------");
        ServerSocket server = new ServerSocket(9099);
        while (true) {
            Socket client = server.accept();
            System.out.println("一个通信建立链接");
            Channel channel = new Channel(client);
            channels.add(channel);
            new Thread(channel).start();
        }
    }

    static class Channel implements Runnable {
        private DataInputStream dis;
        private DataOutputStream dos;
        private Socket client;
        private Boolean isRunning = true;

        //一个客户代表一个channel(通信道)
        public Channel(Socket client) {
            this.client = client;
            try {
                dis = new DataInputStream(client.getInputStream());
                dos = new DataOutputStream(client.getOutputStream());
            } catch (IOException e) {
                release();
            }
        }

        public void send(String msg) {
            try {
                dos.writeUTF(msg);
                dos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * 转发信息
         * @param msg
         */
        public void sendOther(String msg) {
            for (Channel channel : channels
            ) {
                if (this == channel) {
                    continue;
                } else {
                    //调用别的其他管道
                    channel.send(msg);
                }
            }
        }

        public String receive() {
            String msg = null;
            try {
                msg = dis.readUTF();
            } catch (IOException e) {
                release();
            }
            return msg;
        }

        public void release() {

            isRunning = false;
            if (null != dis) {
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

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

        @Override
        public void run() {
            while (isRunning) {
                String msg = receive();
                if (msg != null) {
                    sendOther(msg);
                }
            }
        }
    }
}
