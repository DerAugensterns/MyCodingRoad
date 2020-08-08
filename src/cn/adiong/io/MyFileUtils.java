package cn.adiong.io;

import java.io.*;

/**
 * @Author: 阿威
 * @Date: 2020/8/6 9:27
 */
public class MyFileUtils {
    public static void main(String[] args) {

        try {
            InputStream is = new BufferedInputStream(new FileInputStream("src/TestCreate.txt"));
            OutputStream os = new BufferedOutputStream(new FileOutputStream("src/TestCopy"));
            copy(is, os);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        byte[] flush = null;
        try {
            InputStream is = new FileInputStream("src/324.jpg");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            copy(is, baos);
            flush = baos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            InputStream is = new ByteArrayInputStream(flush);
            OutputStream os = new FileOutputStream("src/324-copy.jpg");
            copy(is, os);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    /**
     * 遍历Closable可变参数数组进行关闭
     *
     * @param ios
     */
    public static void close(Closeable... ios) {
        for (Closeable io : ios
        ) {
            try {
                if (io != null) {
                    io.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void copy(InputStream is, OutputStream os) {
        int len = -1;
        byte[] flush = new byte[1024];

        try {
            while ((len = is.read(flush)) != -1) {
                os.write(flush, 0, len);
            }
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(is, os);
        }
    }
}
