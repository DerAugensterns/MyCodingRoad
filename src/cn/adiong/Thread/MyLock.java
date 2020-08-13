package cn.adiong.Thread;

/**
 * @Author: 阿威
 * @Date: 2020/8/13 10:49
 * 手动实现不可重入锁和可重入锁
 */
public class MyLock {

    UnReLock ul = new UnReLock();
    ReLock rl = new ReLock();

    public static void main(String[] args) throws InterruptedException {
        MyLock ml = new MyLock();
        //锁main线程
        ml.c();
    }

    public void a() throws InterruptedException {

        //isLocked改变为true
        ul.locked();

        //while死循环 wait
        b();

        ul.unLocked();
    }

    public void b() throws InterruptedException {
        ul.locked();
        //.......代码省略
        ul.unLocked();
    }

    public void c() throws InterruptedException {

        //得到当前线程锁
        rl.locked();
        System.out.println(rl.count);

        //判断锁的是否是同一个线程
        d();

        rl.unLocked();
        System.out.println(rl.count);
    }

    public void d() throws InterruptedException {

        rl.locked();
        System.out.println(rl.count);

        rl.unLocked();
        System.out.println(rl.count);
    }

}

/**
 * 不可重入锁
 */
class UnReLock {

    Boolean isLocked = false;

    public synchronized void locked() throws InterruptedException {
        while (isLocked) {
            this.wait();
        }
        isLocked = true;
    }

    public synchronized void unLocked() {
        isLocked = !isLocked;
        this.notifyAll();
    }

}

/**
 * 可重入锁
 */
class ReLock {
    int count;
    Boolean isClocked = false;
    Thread lockedBy;

    public synchronized void locked() throws InterruptedException {
        //判断锁的是否是同一个线程,不同则等待，同立即获得锁
        while (isClocked && Thread.currentThread() != lockedBy) {
            wait();
        }
        //锁层计数
        count++;
        lockedBy = Thread.currentThread();
        isClocked = true;
    }

    public synchronized void unLocked() {

        if (Thread.currentThread() == lockedBy) {
            count--;
            if (count == 0) {
                isClocked = false;
                this.notify();
                lockedBy = null;
            }
        }
    }
}
