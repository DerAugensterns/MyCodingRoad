package cn.adiong.container;

import java.util.HashMap;

/**
 * HashSet的底层原理是HashMap
 * 添加新元素为Key（不可重复） value则固定不变
 */
public class MyHashSet {
    HashMap map;

    public static final Object PRESENT=new Object();

    public MyHashSet() {
        map=new HashMap();
    }

    public void add(Object obj){
        //key值不可重复
        map.put(obj,PRESENT);
    }

    public int size(){
        return map.size();
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append("[");
        for (Object key:map.keySet()
             ) {
            sb.append(key+",");
        }
        sb.setCharAt(sb.length()-1,']');
        return sb.toString();
    }

    public static void main(String[] args) {
        MyHashSet set=new MyHashSet();
        set.add("aa");
        set.add("bb");
        set.add("cc");
        set.add("cc");//添加相同的对象进行测试
        System.out.println(set);
    }

}
