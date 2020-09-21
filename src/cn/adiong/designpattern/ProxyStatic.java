package cn.adiong.designpattern;

/**
 * @Author: 阿威
 * @Date: 2020/9/21 9:45
 * @Description： 静态代理模式，
 * 1、流程控制代码放到代理对象中，
 * 2、业务代码在真实对象
 */
public class ProxyStatic {
    public static void main(String[] args) {
        Star star = new RealStar();
        FakeStar fakeStar = new FakeStar(star);
        fakeStar.confer();
        fakeStar.bookTicket();
        fakeStar.sing();
        fakeStar.giveMoney();
    }
}

interface Star {
    /**
     * 会面
     */
    void confer();

    /**
     * 订票
     */
    void bookTicket();

    /**
     * 唱歌
     */
    void sing();

    /**
     * 给钱
     */
    void giveMoney();
}

class FakeStar implements Star {

    private Star star;

    public FakeStar(Star star) {
        this.star = star;
    }

    @Override
    public void confer() {
        System.out.println("confer");
    }

    @Override
    public void bookTicket() {
        System.out.println("bookTicket");
    }

    @Override
    public void sing() {
        star.sing();
    }

    @Override
    public void giveMoney() {
        System.out.println("giveMoney");
    }
}

class RealStar implements Star {

    @Override
    public void confer() {

    }

    @Override
    public void bookTicket() {

    }

    @Override
    public void sing() {
        System.out.println("giao giao giao");
    }

    @Override
    public void giveMoney() {

    }
}
