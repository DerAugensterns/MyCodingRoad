package cn.adiong.designpattern;

/**
 * @Author: 阿威
 * @Date: 2020/9/17 11:10
 * @Description：
 */
public enum SingletonEnum {

    /**
     * 定义一个枚举元素，天然单例，由JVM提供保障，避免反射和反序列化漏洞
     */
    INSTANCE;

    /**
     * 单例可以有自己的操作
     */
    public void SingletonOpera() {

    }
}
