package cn.adiong.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Author: 阿威
 * @Date: 2020/8/14 9:21
 * @Description： 模拟浏览器进行爬虫
 */
public class WebSpider {
    public static void main(String[] args) throws IOException {
        //有防爬虫
        URL url = new URL("https://www.dianping.com");
        //模拟浏览器代理,骗过对方服务器
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent", " Mozilla/5.0 (iPad; CPU OS 11_0 like Mac OS X) AppleWebKit/604.1.34 (KHTML, like Gecko) Version/11.0 Mobile/15A5341f Safari/604.1");

        InputStream is = url.openStream();

        //此处为字符流
        BufferedReader bf = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

        String msg = null;
        while ((msg = bf.readLine()) != null) {
            System.out.println(msg);
        }
    }
}
