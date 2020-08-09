package cn.adiong.Thread;

/**
 * @Author: 阿威
 * @Date: 2020/8/9 9:03
 * lambda表达式  简化线程（仅用一次）的使用
 */
public class Lambda {
    public static void main(String[] args) {

        //静态内部类和局部内部类 线程实现

        //匿名内部类方法 (需要借助接口或父类)
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    System.out.println("InnerClass" + i);
                }
            }
        }).start();

        /**
         * lambda表达式 java 8内部推导父类or接口
         * 只有一个参数 可以省略（），参数类型可省略；
         * 只有一句方法 可以省略{} ；
         * 只有一句return 可以省略return
         */


        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                System.out.println("lambda" + i);
            }
        }).start();
    }
}
