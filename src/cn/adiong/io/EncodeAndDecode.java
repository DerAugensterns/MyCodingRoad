package cn.adiong.io;

import java.io.UnsupportedEncodingException;

/**
 * @Author: 阿威
 * @Date: 2020/8/4 11:17
 */
public class EncodeAndDecode {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String content = "不盲从不附和一以理智为依归";
        byte[] data = content.getBytes();

        //3*13=39，默认当前工程编码 utf-8
        System.out.println(data.length);

        data = content.getBytes("GBK");
        //2*13=26
        System.out.println(data.length);

        //正确的字节数和字符集才能正确解码
        String msg = new String(data, 0, data.length, "GBk");
        System.out.println(msg);
    }
}
