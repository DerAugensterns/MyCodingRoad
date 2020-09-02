package cn.adiong.thread;

/**
 * @Author: 阿威
 * @Date: 2020/8/8 15:41
 */
public class Race implements Runnable {

    private String winner;

    @Override
    public void run() {

        for (int step = 0; step < 1000; step++) {

            System.out.println(Thread.currentThread().getName() + "-->" + step);

            //线程休眠  模拟兔子睡觉
            if ((Thread.currentThread().getName().equals("rabbit")) && step % 10 == 0) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (getWinner(step)) {
                break;
            }
        }

    }

    private boolean getWinner(int step) {
        if (winner != null) {
            return true;
        } else {
            if (step == 100) {
                winner = Thread.currentThread().getName();
                System.out.println("Winner is " + winner);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        Race race = new Race();

        //线程共享一份资源（龟兔共用跑道）
        new Thread(race, "tortoise").start();
        new Thread(race, "rabbit").start();

    }
}
