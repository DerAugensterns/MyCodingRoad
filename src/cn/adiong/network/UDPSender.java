package cn.adiong.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * @Author: 阿威
 * @Date: 2020/8/16 10:20
 * @Description： 1、使用DatagramSocket 指定端口 创建发送端
 * 2、准备数据 转化成字节数组
 * 3、封装成DatagramPacket包裹 +  目的地(地址加端口)
 * 4、send方法发送
 * 5、释放资源
 */
public class UDPSender {
    public static void main(String[] args) throws IOException {
        DatagramSocket sender = new DatagramSocket(9099);
        String msg = "宜将剩勇追穷寇，不可沽名学霸王";
        byte[] flush = msg.getBytes();
        DatagramPacket packet = new DatagramPacket(flush, 0, flush.length, new InetSocketAddress("localhost", 9990));
        sender.send(packet);
        sender.close();
    }
}
