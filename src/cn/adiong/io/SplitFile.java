package cn.adiong.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Vector;

/**
 * @Author: 阿威
 * @Date: 2020/8/7 9:21
 * <p>
 * 将文件分割成相同大小方便传输（最后一块可能不一样）
 */
public class SplitFile {

    private File src;
    private File dest;

    /**
     * dest的文件名集合
     */
    private ArrayList<String> fileNames;

    /**
     * 每块大小
     */
    private int blockSize;

    /**
     * 文件分成多少块
     */
    private int size;

    public SplitFile(File src, File dest, int blockSize) {
        this.src = src;
        this.dest = dest;
        this.blockSize = blockSize;
        init();
    }

    private void init() {
        long len = this.src.length();
        this.size = (int) Math.ceil(len * 1.0 / blockSize);
        this.fileNames = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            this.fileNames.add(dest.getPath() + "/" + i + "-" + src.getName());
        }
    }

    public void split() throws IOException {
        long len = this.src.length();
        int beginPos = 0;
        int actualSize = (int) (blockSize > len ? len : blockSize);

        for (int i = 0; i < size; i++) {
            //按块分割
            beginPos = blockSize * i;
            //最后一块
            if (i == size - 1) {
                actualSize = (int) len;
            } else {
                actualSize = blockSize;
                len -= actualSize;
            }
            splitDetail(i, beginPos, actualSize);
        }

    }

    private void splitDetail(int i, int beginPos, int actualSize) throws IOException {
        RandomAccessFile rafsrc = new RandomAccessFile(this.src, "r");
        RandomAccessFile rafdest = new RandomAccessFile(this.fileNames.get(i), "rw");
        rafsrc.seek(beginPos);
        byte[] flush = new byte[1024];
        int len = -1;
        //len为读取的长度
        while ((len = rafsrc.read(flush)) != -1) {
            if (actualSize > len) {
                rafdest.write(flush, 0, len);
                actualSize -= len;
            } else {
                rafdest.write(flush, 0, actualSize);
                break;
            }
        }
        rafdest.close();
        rafsrc.close();
    }

    /**
     * @param path
     * @throws IOException 多个文件合并   循环遍历输入流输入完成即释放   输出流外部定义外部释放
     */
    public void merge(String path) throws IOException {
        OutputStream os = new BufferedOutputStream(new FileOutputStream(path, true));

        Vector<InputStream> vi = new Vector<>();

        SequenceInputStream sis = null;

        for (int i = 0; i < fileNames.size(); i++) {
            //vector作为输入流的容器（多个流同步？）
            vi.add(new BufferedInputStream(new FileInputStream(fileNames.get(i))));
        }
        //有序输入流的集合
        sis = new SequenceInputStream(vi.elements());
        int len = -1;
        byte[] flush = new byte[1024];
        while ((len = sis.read(flush)) != -1) {
            os.write(flush, 0, len);
        }
        os.flush();
        sis.close();
        os.close();
    }

    public static void main(String[] args) throws IOException {
        File src = new File("src/cn/adiong/io/FileCopy.java");
        File dest = new File("dest");
        SplitFile sf = new SplitFile(src, dest, 1024);
        sf.split();
        sf.merge("src/FC.java");
    }

}
