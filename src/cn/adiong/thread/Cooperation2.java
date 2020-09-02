package cn.adiong.thread;

/**
 * @Author: 阿威
 * @Date: 2020/8/11 16:42
 * 生产者消费者实现方式一：信号灯法
 */
public class Cooperation2 {
    public static void main(String[] args) {
        Tv tv = new Tv();
        new Thread(new Player(tv), "演员").start();
        new Thread(new Watcher(tv), "观众").start();
    }
}

/**
 * 生产者——演员
 */
class Player implements Runnable {

    Tv tv;

    public Player(Tv tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                tv.play("此刻唯有死战");
            } else {
                tv.play("安能言降");
            }
        }
    }
}

/**
 * 消费者——观众
 */
class Watcher implements Runnable {

    Tv tv;

    public Watcher(Tv tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            tv.watch();
        }
    }
}

/**
 * 共享资源——电视
 */
class Tv {
    String voice;

    /**
     * 通过标志位控制线程等待or唤醒 （记得要切换标志灯）
     */
    Boolean flag = true;

    public synchronized void play(String voice) {
        if (!flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.voice = voice;
        System.out.println(Thread.currentThread().getName() + "表演了" + voice);
        this.notifyAll();
        this.flag = !flag;
    }

    public synchronized void watch() {
        if (flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "观看了" + voice);
        this.notifyAll();
        this.flag = !flag;
    }
}