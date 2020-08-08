package cn.adiong.container;

import java.util.Arrays;

public class TestArrayCopy {
    public static void main(String[] args) {
        String[] bigCompany = {"alibaba", "Tencent", "jindong", "byteDance", "Amaza"};
//        deleteArray(bigCompany, 1);
        String[] s = extendArray(bigCompany);
        System.out.println(Arrays.toString(s));
    }

    public static void deleteArray(String[] s, int index) {
        System.arraycopy(s, index + 1, s, index, s.length - index - 1);
        s[s.length - 1] = null;
        for (int i = 0; i < s.length; i++) {
            System.out.println(s[i]);
        }
    }

    public static String[] extendArray(String[] s) {
        String[] s2 = new String[s.length + 1];
        System.arraycopy(s, 0, s2, 0, s.length);
        return s2;
    }
}
