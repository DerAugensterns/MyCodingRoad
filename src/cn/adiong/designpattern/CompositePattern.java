package cn.adiong.designpattern;

import java.util.ArrayList;

/**
 * @Author: 阿威
 * @Date: 2020/9/21 16:02
 * @Description： 树形结构采用组合模式  存在天然的递归（因为方法名一致）
 */
public class CompositePattern {
    public static void main(String[] args) {
        AbstractFile f1, f2, f3, f4, f5;
        Folder folder = new Folder("造人计划");
        Folder subfolder = new Folder("小计划");
        f1 = new VideoFile("松下纱荣子.avi");
        f2 = new ImageFile("吉泽明步.jpg");
        f3 = new TextFile("水野朝阳.txt");
        f4 = new VideoFile("julia.avi");
        f5 = new ImageFile("冬月.jpg");
        folder.add(f1);
        folder.add(f2);
        folder.add(f3);
        subfolder.add(f4);
        subfolder.add(f5);
        folder.add(subfolder);
        folder.killVirus();
    }
}

interface AbstractFile {
    void killVirus();
}

class Folder implements AbstractFile {

    private String name;

    /**
     * 用于存放子节点
     */
    ArrayList<AbstractFile> children = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    public void add(AbstractFile file) {
        children.add(file);
    }

    public void remove(AbstractFile file) {
        children.remove(file);
    }

    public AbstractFile getFile(int index) {
        return children.get(index);
    }

    @Override
    public void killVirus() {
        System.out.println("文件" + name + "正在查杀");
        for (AbstractFile file : children
        ) {
            file.killVirus();
        }
    }
}

class VideoFile implements AbstractFile {
    private String name;

    public VideoFile(String name) {
        this.name = name;
    }

    @Override
    public void killVirus() {
        System.out.println("视频文件" + name + "查杀");
    }
}

class TextFile implements AbstractFile {
    private String name;

    public TextFile(String name) {
        this.name = name;
    }

    @Override
    public void killVirus() {
        System.out.println("文本文件" + name + "查杀");
    }
}

class ImageFile implements AbstractFile {
    private String name;

    public ImageFile(String name) {
        this.name = name;
    }

    @Override
    public void killVirus() {
        System.out.println("图片文件" + name + "查杀");
    }
}
