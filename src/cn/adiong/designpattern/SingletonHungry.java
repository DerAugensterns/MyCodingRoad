package cn.adiong.designpattern;

import java.io.ObjectStreamException;

/**
 * @Author: 阿威
 * @Date: 2020/9/17 9:29
 * @Description： 饿汉式单例模式
 */
public class SingletonHungry {

    /**
     * 类初始化时立即加载，类加载时线程安全；可能用不到，占有资源
     */
    private static SingletonHungry instance = new SingletonHungry();

    /**
     * 私有化构造器——防止外部new
     */
    private SingletonHungry() {
        //防止反射 通过setAccessible（true）调用
        if (null != instance) {
            throw new RuntimeException();
        }
    }

    /**
     * 无需同步，调用效率高
     *
     * @return
     */
    public static SingletonHungry getInstance() {
        return instance;
    }

    /**
     * 反序列化时，直接调用该方法，返回对象————防止反序列化出新的对象
     * 反序列化漏洞：把对象序列化到文件，在通过输入流创建新的对象
     * @return
     * @throws ObjectStreamException
     */
    public Object readResolve() throws ObjectStreamException {
        return instance;
    }
}
