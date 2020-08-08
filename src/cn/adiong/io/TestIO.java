package cn.adiong.io;

import java.io.*;

/**
 * @Author: 阿威
 * @Date: 2020/8/4 15:28
 * 1、创建源  2、选择流  3、操作（编码 or 解码）  4、释放资源（如有必要）
 */
public class TestIO {

    public static void main(String[] args) {
        testByteArrayOutputStream();
    }

    public static void testInputStream1() {
        //1、创建源
        File src = new File("src/Test.txt");
        //2、选择流
        InputStream is = null;

        try {
            is = new FileInputStream(src);
            int temp;//用于返回实际数据
            //单个遍历读取 -1为结束标志
            while ((temp = is.read()) != -1) {
                System.out.print((char) temp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //通知OS关闭流
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void testInputStream2() {
        //1、创建源
        File src = new File("src/TestCreate.txt");
        //2、选择流
        InputStream is = null;
        byte[] flush = new byte[1024];

        //返回接收的长度
        int len = -1;

        try {
            is = new FileInputStream(src);
            while ((len = is.read(flush)) != -1) {
                //解码 字节——>字符
                String s = new String(flush, 0, len);
                System.out.print(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    public static void testOutputStream() {
        //1、创建源
        File dest = new File("src/TestCreate.txt");
        //2、选择流
        OutputStream os = null;
        String msg = "如遇横逆之境 则不屈不挠不畏强御只问是非不计利害";
        byte[] data = msg.getBytes();
        try {
            //append标识 追加
            os = new FileOutputStream(dest, true);
            os.write(data, 0, data.length);
            os.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void testFileReader() {
        File src = new File("src/TestCreate.txt");
        int len = -1;
        Reader reader = null;
        //注意此处为1024个字符 不是1KB
        char[] flush = new char[1024];
        try {
            reader = new FileReader(src);
            while ((len = reader.read(flush)) != -1) {
                String s = new String(flush, 0, len);
                System.out.println(s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void testFileWriter() {
        String msg = "天行健君子以自强不息地势坤君子以厚德载物";
        File dest = new File("src/Test.txt");
        Writer writer = null;
        try {
            writer = new FileWriter(dest);
            writer.append(msg);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void testByteArrayInputStream() {

        //源为电脑或网络上的一块内存
        byte[] src = "功不唐捐终如海".getBytes();

        InputStream is = null;
        int len = -1;

        try {
            is = new ByteArrayInputStream(src);
            byte[] flush = new byte[1024];
            while ((len = is.read(flush)) != -1) {
                String s = new String(flush, 0, len);
                System.out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }//ByteArray的流由GC释放，可以不用close();


    }

    public static void testByteArrayOutputStream() {

        //不需要目的（通入要输出的内容确定目的大小）
        byte[] dest = null;

        //存在独立方法，不适用多态创建
        ByteArrayOutputStream baos = null;

        try {
            byte[] flush = "一往无前虎山行 拨开云雾见光明 梦里花开牡丹亭 幻想成真歌舞升平".getBytes();
            //创建不关联源
            baos = new ByteArrayOutputStream();
            baos.write(flush, 0, flush.length);
            baos.flush();
            dest = baos.toByteArray();
            String s = new String(dest, 0, dest.length);
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

