package cn.adiong.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;

/**
 * @Author: 阿威
 * @Date: 2020/9/4 9:36
 * @Description：
 */
public class Response {

    private BufferedWriter bf;
    private final String BLANK = " ";
    private final String CRLF = "\r\n";
    /**
     * 协议头信息
     */
    private StringBuilder headInfo;
    /**
     * 正文
     */
    private StringBuilder content;
    /**
     * 正文字节数
     */
    private int len;

    private Response() {
        headInfo = new StringBuilder();
        content = new StringBuilder();
    }

    public Response(Socket client) {
        this();
        try {
            bf = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            headInfo = null;
        }
    }

    public Response(OutputStream os) {
        this();
    }

    /**
     * 构建响应头信息
     *
     * @param status
     */
    private void createHeadInfo(int status) {
        headInfo.append("HTTP/1.1").append(BLANK);
        headInfo.append(status).append(BLANK);
        switch (status) {
            case 200:
                headInfo.append("OK").append(CRLF);
                break;
            case 404:
                headInfo.append("NOT FOUND").append(CRLF);
                break;
            case 505:
                headInfo.append("SERVER ERROR").append(CRLF);
                break;
            default:
                break;
        }
        headInfo.append("Date:").append(new Date()).append(CRLF);
        headInfo.append("Server:").append("adiong Server/0.01;charset=GBK").append(CRLF);
        headInfo.append("Content-type:text/html").append(CRLF);
        headInfo.append("Content-length:").append(len).append(CRLF);
        headInfo.append(CRLF);
    }

    /**
     * 流模式动态添加内容
     *
     * @param info
     * @return
     */
    public Response println(String info) {
        content.append(info).append(CRLF);
        //注意此处返回的字节长度
        len += (info + CRLF).getBytes().length;
        return this;
    }

    /**
     * 推送响应头信息
     *
     * @param status
     */
    public void push2Browser(int status) {
        if (headInfo == null) {
            status = 505;
        }
        createHeadInfo(status);
        System.out.println(headInfo);
        System.out.println(content);
        try {
            bf.write(headInfo.toString());
            bf.write(content.toString());
            bf.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
