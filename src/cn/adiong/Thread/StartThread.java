package cn.adiong.Thread;

/**
 * @Author: 阿威
 * @Date: 2020/8/8 10:08
 */
public class StartThread extends Thread {

    /**
     * 线程的入口点
     */
    @Override
    public void run() {
        for (int i = 0; i < 30; i++) {
            System.out.println("一边吃饭");
        }
    }

    public static void main(String[] args) {

        StartThread st = new StartThread();
        //启动线程，该方法不保证立刻被CPU调用
        st.start();

        //仅为普通run方法的调用，没有特别意义
        //st.run();

        for (int i = 0; i < 30; i++) {
            System.out.println("一边coding");
        }
    }


}
