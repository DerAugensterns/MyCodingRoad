package cn.adiong.basic;

import java.util.*;

public class TestRecurion {
    public static void main(String[] args) {
        int sum = factorial(10);
        System.out.println(sum);
    }

    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }

    }
}
