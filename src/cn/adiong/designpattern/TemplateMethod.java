package cn.adiong.designpattern;

/**
 * @Author: 阿威
 * @Date: 2020/9/26 10:48
 * @Description： 模板方法模式：大部分流程固定 少部分具体业务 钩子函数具体实现
 */
public class TemplateMethod {
    public static void main(String[] args) {
        /**
         * 匿名内部类
         */
        BankTemplateMethod btm = new BankTemplateMethod() {
            @Override
            public void transaction() {
                System.out.println("save money");
            }
        };

        btm.process();
    }
}

abstract class BankTemplateMethod {
    public void takeNumber() {
        System.out.println("取号排队");
    }

    public abstract void transaction();

    public void evaluate() {
        System.out.println("事后评分");
    }

    public final void process() {
        takeNumber();
        transaction();
        evaluate();
    }
}
