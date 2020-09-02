package cn.adiong.thread;

import java.util.concurrent.*;

/**
 * @Author: 阿威
 * @Date: 2020/8/8 16:19
 */
public class CallableThread implements Callable<Integer> {
    private String winner;

    @Override
    public Integer call() throws Exception {
        for (int step = 0; step < 1000; step++) {

            System.out.println(Thread.currentThread().getName() + "-->" + step);

            //线程休眠  模拟兔子睡觉
            if ((Thread.currentThread().getName().equals("pool-1-thread-1")) && step % 10 == 0) {
                Thread.sleep(1);
            }

            if (getWinner(step)) {
                return step;
            }
        }
        return null;
    }

    private Boolean getWinner(int step) {
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

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CallableThread race = new CallableThread();


        //创建执行服务
        ExecutorService es = Executors.newFixedThreadPool(2);

        Future<Integer> rabbit = es.submit(race);
        Future<Integer> tortoise = es.submit(race);

        Integer r1 = rabbit.get();
        Integer r2 = tortoise.get();

        System.out.println(r1);
        System.out.println(r2);

        es.shutdownNow();
    }
}

