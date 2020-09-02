package cn.adiong.thread;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @Author: 阿威
 * @Date: 2020/8/8 10:25
 * 为避免单继承局限性，优先使用runnable接口
 */
public class ThreadDownloader implements Runnable {

    private String url;
    private String dest;

    public ThreadDownloader(String url, String dest) {
        this.url = url;
        this.dest = dest;
    }

    public void download(String url, String dest) {
        try {
            FileUtils.copyURLToFile(new URL(url), new File(dest));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        download(url, dest);
    }

    public static void main(String[] args) {
        ThreadDownloader td1 = new ThreadDownloader("", "");
        ThreadDownloader td2 = new ThreadDownloader("", "");
        ThreadDownloader td3 = new ThreadDownloader("", "");

        //对象只使用一次可以匿名
        new Thread(td1).start();
        new Thread(td2).start();
        new Thread(td3).start();
    }
}
