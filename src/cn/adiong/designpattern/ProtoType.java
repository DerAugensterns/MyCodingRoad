package cn.adiong.designpattern;

import java.util.Date;

/**
 * @Author: 阿威
 * @Date: 2020/9/19 11:55
 * @Description： cloneable为标记接口（必须），
 *                实际的克隆由重写object的clone方法实现
 */
public class ProtoType {
    public static void main(String[] args) throws CloneNotSupportedException {
        Date date = new Date();
        Sheep s1 = new Sheep(date, "喜羊羊");
        Sheep s2 = (Sheep) s1.clone();
        s1.setBirthday(new Date(128476524662L));
        System.out.println(s1.getBirthday());
        System.out.println(s2.getBirthday());
    }
}

class Sheep implements Cloneable {
    private Date birthday;
    private String name;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object obj = super.clone();
        Sheep sheep = (Sheep) obj;
        //进行深复制，每个对象对应一个Date对象
        Date d = (Date) sheep.birthday.clone();

        return obj;
    }

    public Sheep() {
    }

    public Sheep(Date birthday, String name) {
        this.birthday = birthday;
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}