package cn.adiong.io;

import java.io.*;

/**
 * @Author: 阿威
 * @Date: 2020/8/5 16:17
 * <p>
 * 1、将图片转为字节数组
 * 2、将字节数组转为图片
 */
public class FileRelive {
    public static void main(String[] args) {
        arr2Pic(pic2Arr("src/324.jpg"), "src/324-byte.jpg");
    }

    public static byte[] pic2Arr(String path) {
        File src = new File(path);
        InputStream is = null;
        //用到baos独立方法toByteArray()
        ByteArrayOutputStream baos = null;
        byte[] flush = new byte[1024 * 10];
        int len = -1;
        try {
            is = new FileInputStream(src);
            baos = new ByteArrayOutputStream();
            while ((len = is.read(flush)) != -1) {
                baos.write(flush, 0, len);
            }
            //清空小桶，避免滞留数据
            baos.flush();
            //返回字节数组作为转回方法的参数
            return baos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void arr2Pic(byte[] src, String path) {
        File dest = new File(path);
        System.out.println(dest.getAbsolutePath());
        InputStream is = null;
        OutputStream os = null;
        byte[] flush = new byte[1024];
        int len = -1;
        try {
            is = new ByteArrayInputStream(src);
            os = new FileOutputStream(dest);
            while ((len = is.read(flush)) != -1) {
                os.write(flush, 0, len);
            }
            os.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
