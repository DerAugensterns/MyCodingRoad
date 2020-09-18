package cn.adiong.designpattern;

/**
 * @Author: 阿威
 * @Date: 2020/9/17 10:40
 * @Description： 静态内部类单例模式
 */
public class SingletonStaticInner {

    /**
     * 线程安全 延时加载
     */
    private static class CreateSingleton {
        private static final SingletonStaticInner instance = new SingletonStaticInner();
    }

    private SingletonStaticInner() {
    }

    public SingletonStaticInner getInstance() {
        return CreateSingleton.instance;
    }
}
