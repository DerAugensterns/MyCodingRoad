package cn.adiong.designpattern;

/**
 * @Author: 阿威
 * @Date: 2020/9/20 11:18
 * @Description： 组合：对象适配器方式  继承：类适配器方式
 */
public class AdapterPattern {
    public static void main(String[] args) {
        Client client = new Client();
        Adapter adapter = new Adapter(new Adaptee());
        client.doRequsest(adapter);
    }
}

/**
 * 被适配对象
 */
class Adaptee {
    public void run() {
        System.out.println("成功使用适配器");
    }
}

/**
 * 适配器接口及具体实习
 */
interface Target {
    void doRequest();
}
class Adapter implements Target {

    private Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void doRequest() {
        adaptee.run();
    }
}

/**
 * 调用相关适配器实现功能
 */
class Client {
    public void doRequsest(Target target) {
        target.doRequest();
    }

}


