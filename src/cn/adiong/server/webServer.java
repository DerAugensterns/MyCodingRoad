package cn.adiong.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: 阿威
 * @Date: 2020/9/4 10:22
 * @Description：
 */
public class webServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(9099);
        Socket client = server.accept();
        Response response = new Response(client);
        //只关注内容和状态码即可
        response.println("<html>");
        response.println("<head>");
        response.println("<title>");
        response.println("返回响应成功，浸泡！");
        response.println("</title>");
        response.println("</head>");
        response.println("<body>");
        response.println("<p>");
        response.println("北海啊，要多想！");
        response.println("</p>");
        response.println("</body>");
        response.println("</html>");
        response.push2Browser(200);
    }
}
