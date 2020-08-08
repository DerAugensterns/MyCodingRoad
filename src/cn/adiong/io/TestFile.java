package cn.adiong.io;

import java.io.File;
import java.io.IOException;

/**
 * @Author: 阿威
 * @Date: 2020/8/3 16:32
 */
public class TestFile {
    public static void main(String[] args) throws IOException {
        //相对路径：相对于当前工程路径 Project栏左上角那个
        // System.out.println(System.getProperty("user.dir"));

        //File仅仅是个用于操作对象，并不表示文件存在与否
        File f1 = new File("src/Test.txt");
        File f2 = new File("src");
        File f3 = new File("src/TestCreate.txt");
        File f4 = new File("Create");

        //只能在文件不存在时创建文件，不能创建文件夹
        f3.delete();
        boolean flag = f3.createNewFile();
        //flag = f4.createNewFile();
        System.out.println(flag);


        System.out.println(f1.exists());
        System.out.println(f2.exists());
        System.out.println(f2.isDirectory());

        //文件夹或者无文件则length（） 返回0
        System.out.println("文件长度：" + f1.length());

    }
}
