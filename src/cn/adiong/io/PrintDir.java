package cn.adiong.io;

import java.io.File;

/**
 * @Author: 阿威
 * @Date: 2020/8/4 9:45
 * <p>
 * 通过递归打印文件及其下名称
 */
public class PrintDir {

    public static long sumByte;

    public static void main(String[] args) {
        String path = "D:/MyCode/Study";
        File f = new File(path);
        printFileName(f,0);
        System.out.println(countFile(f));
    }

    public static void printFileName(File f, int deep) {
        //通过deep计数，让目录有层次感
        for (int i = 0; i < deep; i++) {
            System.out.print("-");
        }
        System.out.println(f.getName());
        if (f == null || !f.exists()) {
            return;
        } else if (f.isDirectory()) {
            File[] files = f.listFiles();
            for (File temp : files
            ) {
                printFileName(temp, deep + 1);
            }
        }
    }

    public static long countFile(File f) {

        if (f != null && f.exists()) {
            if (f.isFile()) {
                sumByte += f.length();
            } else {
                for (File temp : f.listFiles()) {
                    countFile(temp);
                }
            }
        }
        return sumByte;
    }
}
