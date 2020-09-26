package cn.adiong.designpattern;

/**
 * @Author: 阿威
 * @Date: 2020/9/26 10:23
 * @Description：
 */
public class StrategyPattern {
    public static void main(String[] args) {
        Context context = new Context(new OldCustomerFew());
        context.getPrice(998);
    }
}

interface Strategy {
    double getPrice(double standardPrice);
}

class NewCustomerFew implements Strategy {

    @Override
    public double getPrice(double standardPrice) {
        System.out.println("不打折");
        return standardPrice;
    }
}

class NewCustomerMany implements Strategy {

    @Override
    public double getPrice(double standardPrice) {
        System.out.println("打九折");
        return standardPrice * 0.9;
    }
}

class OldCustomerFew implements Strategy {

    @Override
    public double getPrice(double standardPrice) {
        System.out.println("打八五折");
        return standardPrice * 0.85;
    }
}

class OldCustomerMany implements Strategy {

    @Override
    public double getPrice(double standardPrice) {
        System.out.println("打八折");
        return standardPrice * 0.8;
    }
}

/**
 * 上下文对象 ： 负责和具体的策略类交互
 * 实现算法与调用的分离
 */
class Context {
    private Strategy strategy;

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void getPrice(double standardPrice) {
        System.out.println(strategy.getPrice(standardPrice));
    }
}