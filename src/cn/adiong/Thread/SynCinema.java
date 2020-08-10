package cn.adiong.Thread;

import java.util.ArrayList;

/**
 * @Author: 阿威
 * @Date: 2020/8/10 11:22
 */
public class SynCinema implements Runnable {

    Cinema cinema;
    ArrayList<Integer> seats;

    public SynCinema(Cinema cinema, ArrayList<Integer> seats) {
        this.cinema = cinema;
        this.seats = seats;
    }

    @Override
    public void run() {
        buyTickets(seats);
    }

    private void buyTickets(ArrayList<Integer> seats) {
        synchronized (cinema) {
            if (cinema.available.containsAll(seats)) {
                System.out.println("出票成功" + Thread.currentThread().getName() + "位置为：" + seats);
                //删除已订购的位置
                cinema.available.removeAll(seats);
            } else {
                System.out.println("出票失败" + Thread.currentThread().getName() + "-<位置不够");
            }
        }
    }

    public static void main(String[] args) {

        ArrayList<Integer> total = new ArrayList<>();
        total.add(1);
        total.add(2);
        total.add(3);
        total.add(7);
        total.add(8);
        total.add(9);

        Cinema cinema = new Cinema(total, "万达");


        ArrayList<Integer> buyer1 = new ArrayList<>();
        buyer1.add(1);
        buyer1.add(3);
        buyer1.add(8);

        ArrayList<Integer> buyer2 = new ArrayList<>();
        buyer2.add(9);
        buyer2.add(7);
        buyer2.add(1);

        new Thread(new SynCinema(cinema, buyer1), "pp").start();
        new Thread(new SynCinema(cinema, buyer2), "xx").start();

    }
}

class Cinema {

    /**
     * 容器模拟电影院位置
     */

    ArrayList<Integer> available;
    String name;

    public Cinema(ArrayList<Integer> available, String name) {
        this.available = available;
        this.name = name;
    }
}