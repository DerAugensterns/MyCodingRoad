package cn.adiong.io;

import java.io.*;
import java.net.URL;

/**
 * @Author: 阿威
 * @Date: 2020/8/6 15:30
 */
public class GetBaidu {
    public static void main(String[] args) {

        // try(流的声明 （特别注意是声明） 不同流用;隔开 )....with resource 内部对流进行关闭
        try (
                BufferedReader reader =
                        new BufferedReader(
                                //将字节流转化为字符流
                                new InputStreamReader(
                                        new URL("https://www.baidu.com").openStream(), "UTF-8"));
                BufferedWriter writer =
                        new BufferedWriter(
                                //将字符流转为字节流
                                new OutputStreamWriter(
                                        new FileOutputStream("src/baidu.html"), "UTF-8"));
        ) {
            String msg = null;
            while ((msg = reader.readLine()) != null) {
                writer.write(msg);
                writer.newLine();
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
