package cn.adiong.designpattern;

/**
 * @Author: 阿威
 * @Date: 2020/9/18 11:16
 * @Description： 静态工厂模式 需要修改代码 违反开闭原则
 */
public class FactorySimple {
    public static void main(String[] args) {
        Car c1 = CarFactory.createCar("本田");
        Car c2 = CarFactory.createCar("奥迪");
        c1.run();
        c2.run();
    }
}

interface Car {
    void run();
}

class Toyota implements Car {

    @Override
    public void run() {
        System.out.println("Toyota is  running");
    }
}

class Audi implements Car {

    @Override
    public void run() {
        System.out.println("Audi is  running");
    }
}

class CarFactory {
    public static Car createCar(String carName) {
        if (carName.equals("本田")) {
            return new Toyota();
        } else if (carName.equals("奥迪")) {
            return new Audi();
        } else {
            return null;
        }
    }
}