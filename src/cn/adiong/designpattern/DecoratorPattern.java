package cn.adiong.designpattern;

/**
 * @Author: 阿威
 * @Date: 2020/9/22 9:45
 * @Description：  装饰者模式
 */
public class DecoratorPattern {
    public static void main(String[] args) {
        Decorator decorator = new HeadDecorator(new FaceDecorator(new ConcreteComponent()));
        decorator.run();

    }
}

/**
 * 抽象构建角色
 */
interface Component {
    void run();
}

/**
 * 具体构建角色（被装饰对象）
 */
class ConcreteComponent implements Component {

    @Override
    public void run() {
        System.out.println("平平无奇");
    }
}

/**
 * 装饰角色
 */
class Decorator implements Component {

    Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void run() {
        component.run();
    }
}

/**
 * 具体装饰
 */
class FaceDecorator extends Decorator {
    public FaceDecorator(Component component) {
        super(component);
    }

    public void faceDecorate() {
        System.out.println("脸部装饰");
    }

    @Override
    public void run() {
        super.run();
        faceDecorate();
    }
}

class LegDecorator extends Decorator {
    public LegDecorator(Component component) {
        super(component);
    }

    public void legDecorator() {
        System.out.println("腿部装饰");
    }

    @Override
    public void run() {
        super.run();
        legDecorator();
    }
}

class HeadDecorator extends Decorator {
    public HeadDecorator(Component component) {
        super(component);
    }

    public void headDecorator() {
        System.out.println("头部装饰");
    }

    @Override
    public void run() {
        super.run();
        headDecorator();
    }
}
