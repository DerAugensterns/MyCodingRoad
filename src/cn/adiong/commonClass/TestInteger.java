package cn.adiong.commonClass;

/**
 * 测试包装类
 */

public class TestInteger {
    public static void main(String[] args) {
        Integer x = new Integer(30);
        Integer y = Integer.valueOf(300);

        int a = x.intValue();
        double b = y.doubleValue();

        Integer x1 = new Integer("999");
        Integer y1 = Integer.parseInt("998");

        String aa = x1.toString();

        System.out.println(Integer.MAX_VALUE);

    }
}
