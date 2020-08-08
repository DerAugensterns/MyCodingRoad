package cn.adiong.io;

import java.io.*;

/**
 * @Author: 阿威
 * @Date: 2020/8/5 10:26
 */
public class FileCopy {

    public static void main(String[] args) {
        copyDir(new File("src"), new File("dest"));
    }

    public static void copyFile(String srcPath, String destPath) {
        //创建源
        File src = new File(srcPath);
        File dest = new File(destPath);

        //选择流
        InputStream is = null;
        OutputStream os = null;

        //每次读1k=1024字节
        byte[] flush = new byte[1024];
        int len = -1;


        try {
            is = new FileInputStream(src);
            os = new FileOutputStream(dest, true);
            while ((len = is.read(flush)) != -1) {
                os.write(flush, 0, len);
            }
            os.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //两个流分别关闭 先打开的 后关闭
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 通过递归复制文件夹
     *
     * @param src
     * @param dest
     */
    public static void copyDir(File src, File dest) {
        if (src == null || !src.exists()) {
            return;
        } else if (src.isDirectory()) {
            if (!dest.exists()) {
                dest.mkdirs();
            }
            //需要复制的文件名的集合
            String[] fileNames = src.list();
            for (String name : fileNames
            ) {
                //在dest创建与src相同名称的文件
                File srcFile = new File(src, name);
                File destFile = new File(dest, name);
                copyDir(srcFile, destFile);
            }
        } else {
            copyFile(src.getAbsolutePath(), dest.getAbsolutePath());
        }
    }

}
