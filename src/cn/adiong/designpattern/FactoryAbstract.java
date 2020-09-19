package cn.adiong.designpattern;

/**
 * @Author: 阿威
 * @Date: 2020/9/19 9:48
 * @Description： 抽象工厂模式 更多用于生产产品族，不用于生成单个产品
 */
public class FactoryAbstract {
    public static void main(String[] args) {
        GroupFactory gf = new LuxuryGroupFactory();
        Engineer e = gf.createEngineer();
        Tyre t = gf.createTyre();
        Seat s = gf.createSeat();
        e.run();
        t.run();
        s.run();
    }
}

interface Engineer {
    void run();
}

class LuxuryEngineer implements Engineer {

    @Override
    public void run() {
        System.out.println("高级发动机");
    }
}

class LowEngineer implements Engineer {

    @Override
    public void run() {
        System.out.println("低端发动机");
    }
}

interface Tyre {
    void run();
}

class LuxuryTyre implements Tyre {

    @Override
    public void run() {
        System.out.println("高级轮胎");
    }
}

class LowTyre implements Tyre {

    @Override
    public void run() {
        System.out.println("低级轮胎");
    }
}

interface Seat {
    void run();
}

class LuxurySeat implements Seat {

    @Override
    public void run() {
        System.out.println("高级座椅");
    }
}

class LowSeat implements Seat {

    @Override
    public void run() {
        System.out.println("低级座椅");
    }
}

interface GroupFactory {
    Engineer createEngineer();

    Tyre createTyre();

    Seat createSeat();
}

class LuxuryGroupFactory implements GroupFactory {

    @Override
    public Engineer createEngineer() {
        return new LuxuryEngineer();
    }

    @Override
    public Tyre createTyre() {
        return new LuxuryTyre();
    }

    @Override
    public Seat createSeat() {
        return new LuxurySeat();
    }
}

class LowGroupFactory implements GroupFactory {

    @Override
    public Engineer createEngineer() {
        return new LowEngineer();
    }

    @Override
    public Tyre createTyre() {
        return new LowTyre();
    }

    @Override
    public Seat createSeat() {
        return new LowSeat();
    }
}