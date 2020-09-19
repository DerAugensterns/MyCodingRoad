package cn.adiong.designpattern;

/**
 * @Author: 阿威
 * @Date: 2020/9/19 9:25
 * @Description： 工厂方法模式 对应的类有对应的工厂 扩展新的工厂类即可
 */
public class FactoryMethod {
    public static void main(String[] args) {
        Car c1 = new AudiFactory().createCar();
        Car c2 = new ToyotaFactory().createCar();
        c1.run();
        c2.run();
    }
}

interface CarFactory2 {
    /**
     * 生产汽车
     *
     * @return
     */
    Car createCar();
}

class AudiFactory implements CarFactory2 {

    @Override
    public Car createCar() {
        return new Audi();
    }
}

class ToyotaFactory implements CarFactory2 {

    @Override
    public Car createCar() {
        return new Toyota();
    }
}