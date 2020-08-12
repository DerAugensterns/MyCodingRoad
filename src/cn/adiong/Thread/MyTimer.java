package cn.adiong.Thread;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author: 阿威
 * @Date: 2020/8/12 10:18
 * 借助Timer或TimerTask（抽象类）
 */
public class MyTimer {
    public static void main(String[] args) {
        new Timer().schedule(new MyTask(), 1000);

        new Timer().schedule(new MyTask(), 1000, 2);

        new Timer().schedule(new MyTask(), new Date(), 200);
    }
}

class MyTask extends TimerTask {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("老骥伏枥，志在千里；壮士暮年，雄心不已！");
        }
    }
}
