package cn.adiong.commonClass;

import java.io.File;

public class TestFileTree {
    public static void main(String[] args) {
        File f = new File("d:/MyCode");
        int level = 1;
        printFileName(f, level);
    }

    /**
     * 递归打印文件名
     */
    public static void printFileName(File f, int level) {

        for (int i = 0; i < level; i++) {
            System.out.print("-");
        }

        System.out.println(f.getName());

        if (f.isDirectory()) {
            File[] files = f.listFiles();
            for (File temp :                 //注意temp前面的类型要写
                    files
            ) {
                printFileName(temp, level + 1);
            }
        }
    }
}
