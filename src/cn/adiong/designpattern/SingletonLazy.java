package cn.adiong.designpattern;

/**
 * @Author: 阿威
 * @Date: 2020/9/17 10:06
 * @Description： 懒汉式单例模式
 */
public class SingletonLazy {

    /**
     * 懒（延时）加载 lazy load——等真正用到才加载
     */
    private static  SingletonLazy instance;

    /**
     * 私有化构造器——防止外部new
     */
    private SingletonLazy() {

    }

    /**
     * 增加同步，防止创建多个对象，效率低
     *
     * @return
     */
    public static synchronized SingletonLazy getInstance() {
        if (instance == null) {
            instance = new SingletonLazy();
        }
        return instance;
    }
}
