package cn.adiong.container;

/**
 * 手动实现HashMap
 * @author pp
 */
public class MyHashMap<K, V> {

    private Nodee[] table;//位桶数组,bucket array
    private int size; //存放键值对的数目


    public static void main(String[] args) {
        MyHashMap<Integer, String> map = new MyHashMap<>();
        map.put(10, "aa");
        // map.put(20, "bb");
        map.put(30, "xx");

        //测试相同key
        //map.put(10, "sssss");

        //测试hash值相同的对象 eg:20 36 52
        map.put(20, "gg");
        map.put(36, "mm");
        map.put(52, "gender");


        System.out.println(map);

        System.out.println(map.get(20));


    }

    public MyHashMap() {
        table = new Nodee[16]; //长度一般定义为2的整数次幂
    }

    //通过哈希码计算哈希值
    public int myHash(int hash, int length) {
        //System.out.println(hash & (length - 1));//直接位运算，效率高，长度需为2的整数次幂
        //System.out.println(hash % (length - 1));//取模运算，效率低

        return hash & (length - 1);
    }

    public void put(K key, V value) {
        Nodee<K, V> newNode = new Nodee<>(key, value);
        newNode.hash = myHash(key.hashCode(), table.length);//根据key的哈希码得到哈希值

        Nodee temp = table[newNode.hash];
        Nodee lastNode = null;//用于记录temp迭代
        boolean flag = false;//判断key是否重复
        if (temp == null) {
            //位桶数组首个为空
            table[newNode.hash] = newNode;
            size++;
        } else {
            while (temp != null) {
                //key重复，value覆盖
                if (temp.key.equals(newNode.key)) {
                    temp.value = newNode.value;
                    flag = true;
                    break;//防止死循环
                } else {//key不重复，尾插
                    lastNode = temp;
                    temp = temp.next;
                }
            }
            if (!flag) {
                lastNode.next = newNode;
                size++;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        //遍历位桶数组
        for (int i = 0; i < table.length; i++) {
            Nodee temp = table[i];
            //遍历链表
            while (temp != null) {
                sb.append(temp.key + "：" + temp.value + ",");
                temp = temp.next;
            }
        }
        sb.setCharAt(sb.length() - 1, '}');
        return sb.toString();
    }

    public V get(K key) {
        int hash = myHash(key.hashCode(), table.length);
        Nodee temp = table[hash];
        while (temp != null) {
            if (temp.key.equals(key)) {
                return (V) temp.value;
            } else {
                temp = temp.next;
            }
        }
        return null;
    }
}

class Nodee<K, V> {
    int hash;  //哈希值
    K key;
    V value;
    Nodee next;

    public Nodee() {
    }

    public Nodee(K key, V value) {
        this.key = key;
        this.value = value;
    }
}