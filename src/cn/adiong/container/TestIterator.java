package cn.adiong.container;

import java.util.*;

/**
 * 使用Iterator可遍历 List Map Set
 * Iterator需要使用泛型
 */
public class TestIterator {
    public static void main(String[] args) {
        iteratorList();
    }

    //Iterator遍历map比较特殊
    public static void iteratorMap() {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "adiong");
        map.put(5, "xx");
        map.put(2, "dd");
        map.put(4, "gg");
        map.put(3, "jj");
        //获得键值对集合后进行遍历
        Set<Map.Entry<Integer, String>> ss = map.entrySet();
        for (Iterator<Map.Entry<Integer, String>> iter = ss.iterator(); iter.hasNext(); ) {
            Map.Entry<Integer, String> temp = iter.next();
            System.out.println(temp.getKey() + "---" + temp.getValue());
        }
    }
    public static void iteratorMap2() {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "adiong");
        map.put(5, "xx");
        map.put(2, "dd");
        map.put(4, "gg");
        map.put(3, "jj");

        //获得键集合后遍历
        Set<Integer> keyset = map.keySet();
        for (Iterator<Integer> iter = keyset.iterator(); iter.hasNext(); ) {
            Integer temp = iter.next();
            System.out.println(temp + "----" + map.get(temp));
        }

    }

    //遍历Set与List同理
    public static void iteratorList() {
        List<String> list = new ArrayList<>();
        list.add("aa");
        list.add("bb");
        list.add("dd");
        list.add("cc");
        for (Iterator<String> iter = list.iterator(); iter.hasNext(); ) {
            String temp = iter.next();
            System.out.println(temp);
        }
    }
}
