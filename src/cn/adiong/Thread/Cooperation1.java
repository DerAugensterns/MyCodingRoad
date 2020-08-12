package cn.adiong.Thread;

/**
 * @Author: 阿威
 * @Date: 2020/8/11 11:23
 * 生产者消费者实现方式一：管程法
 */
public class Cooperation1 {
    public static void main(String[] args) {
        SynContainer synContainer = new SynContainer();
        new Thread(new Producer(synContainer), "工厂").start();
        new Thread(new Consumer(synContainer), "商店").start();
    }
}

/**
 * 生产者
 */
class Producer implements Runnable {
    SynContainer synContainer;
    Steamedebun bun;

    public Producer(SynContainer synContainer) {
        this.synContainer = synContainer;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            synContainer.push(new Steamedebun(i));
            System.out.println(Thread.currentThread().getName() + "生产了第" + i + "个馒头");
        }
    }

}

/**
 * 消费者
 */
class Consumer implements Runnable {
    SynContainer synContainer;
    Steamedebun bun;

    public Consumer(SynContainer synContainer) {
        this.synContainer = synContainer;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            synContainer.pop();
            System.out.println(Thread.currentThread().getName() + "消费了" + i + "个馒头");
        }
    }
}

/**
 * 缓冲区 ——————共享资源 被锁定 synchronized
 */
class SynContainer {
    Steamedebun[] buns = new Steamedebun[10];
    int count;

    /**
     * 存——生产
     */
    public synchronized void push(Steamedebun bun) {
        //容器满了，暂停生产，等待被notify唤醒
        if (count == buns.length) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //开始生产，唤醒消费
        buns[count] = bun;
        count++;
        this.notifyAll();
    }

    /**
     * 取——消费
     */
    public synchronized Steamedebun pop() {

        //容器空了，停止消费等待被唤醒
        if (count == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //开始消费，唤醒生产
        count--;
        this.notifyAll();
        return buns[count];

    }

}

/**
 * 数据 (馒头)
 */
class Steamedebun {
    int id;

    public Steamedebun(int id) {
        this.id = id;
    }
}
