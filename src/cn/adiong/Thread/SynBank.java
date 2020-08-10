package cn.adiong.Thread;

/**
 * @Author: 阿威
 * @Date: 2020/8/10 9:49
 */
public class SynBank {
    public static void main(String[] args) {
        Account account = new Account("结婚钱", 100);
        new Thread(new drawingMoney(account, 30), "you").start();
        new Thread(new drawingMoney(account, 80), "wife").start();
    }
}

class Account {
    String name;
    double money;

    public Account(String name, double money) {
        this.name = name;
        this.money = money;
    }
}

class drawingMoney implements Runnable {

    Account account;
    double drawMoeny;
    double pocketMoney;

    public drawingMoney(Account account, double drawMoeny) {
        this.account = account;
        this.drawMoeny = drawMoeny;
    }

    @Override
    public void run() {
        draw();
    }

    private void draw() {

        //性能调优
        if (account.money <= 0) {
            return;
        }

        //锁定共享对象（账户里的钱）
        synchronized (account) {
            if (account.money - drawMoeny < 0) {
                return;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            account.money -= drawMoeny;
            pocketMoney += drawMoeny;
            System.out.println(Thread.currentThread().getName() + "口袋的钱" + pocketMoney);
            System.out.println("账户余额" + account.money);
        }
    }
}