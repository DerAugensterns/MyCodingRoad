package cn.adiong.container;

import java.util.Arrays;

public class TestBubbleSort {
    public static void main(String[] args) {
        int[] values = {3, 4, 1, 5, 8, 9, 6, 7};
        int temp;

        for (int i = 0; i < values.length; i++) {
            boolean flag=true;
            for (int j = 0; j < values.length - 1 - i; j++) {// length-1 防止最后一个做比较时 下标越界；-i 排好的不再进行比较
                if (values[j] > values[j + 1]) {
                    temp = values[j];
                    values[j] = values[j + 1];
                    values[j + 1] = temp;
                    flag = false;//若发生交换，说明还未排序成功
                }
                System.out.println(Arrays.toString(values));
            }
            if(flag){
                System.out.println("Sort Success");
                break;
            }
            System.out.println("############################");
        }
    }
}
