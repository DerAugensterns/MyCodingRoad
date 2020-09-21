package cn.adiong.designpattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: 阿威
 * @Date: 2020/9/21 10:06
 * @Description： 动态代理
 */
public class ProxyDynamic {
    public static void main(String[] args) {
        RealStar realStar = new RealStar();
        StarHandle starHandle = new StarHandle(realStar);
        //动态生成代理对象 调勇handler的invoke方法
        Star proxy = (Star) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                new Class[]{Star.class}, starHandle);
        proxy.bookTicket();
        proxy.sing();
    }
}

class StarHandle implements InvocationHandler {

    private RealStar realStar;

    public StarHandle(RealStar realStar) {
        this.realStar = realStar;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //用于返回函数返回值
        Object object = null;
        if (method.getName().equals("sing")) {
            object = method.invoke(realStar, args);
        } else {
            System.out.println("调用代理流程控制");
        }
        return object;
    }
}
