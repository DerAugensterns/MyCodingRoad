package cn.adiong.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @Author: 阿威
 * @Date: 2020/8/16 10:20
 * @Description： 1、使用DatagramSocket 指定端口 创建接收端
 * 2、准备容器 封装成DatagramPacket
 * 3、阻塞式接收包裹 receive
 * 4、分析数据  byte【】 getData（）    getLength（）
 */
public class UDPReceiver {
    public static void main(String[] args) throws IOException {
        DatagramSocket reveiver = new DatagramSocket(9990);
        byte[] container = new byte[1024 * 60];
        DatagramPacket packet = new DatagramPacket(container, 0, container.length);
        reveiver.receive(packet);
        byte[] datas = packet.getData();
        System.out.println(new String(datas));
        reveiver.close();
    }
}
