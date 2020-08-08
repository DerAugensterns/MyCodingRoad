package cn.adiong.container;


/**
 * arraylist的实现原理：数组扩容 数组拷贝 添加新元素
 *
 * @param <E>
 */
public class MyArrayList<E> {
    private Object[] elementData;

    //表示数组中有多少个元素
    private int size;
    private static final int DEFAULT_CAPACITY = 10;


    public MyArrayList(int capacity) {
        if (capacity < 0) {
            throw new RuntimeException("数组容量不符合：" + capacity);
        }
        if (capacity == 0) {
            elementData = new Object[DEFAULT_CAPACITY];
        } else {
            elementData = new Object[capacity];
        }
    }

    public void add(E element) {
        //什么时候进行扩容？
        if (size == elementData.length) {
            //怎么进行扩容？

            //注意加括号 运算优先级问题 10+(10/2)=15
            Object[] newArray = new Object[elementData.length + (elementData.length >> 1)];
            System.arraycopy(elementData, 0, newArray, 0, elementData.length);
            elementData = newArray;
        }
        elementData[size++] = element;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0 ? true : false;
    }

    public void remove(E element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(get(i))) {//容器中大都使用equals方法进行比较
                remove(i);
            }
        }
    }

    /**
     * 删除的原理是数组的拷贝替换
     * @param index
     */
    public void remove(int index) {
        checkRange(index);
        int moveNumber = elementData.length - 1 - index;
        if (moveNumber > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, moveNumber);
        }
        elementData[--size] = null;
    }

    //下标检查
    private void checkRange(int index) {
        if (index < 0) {
            throw new RuntimeException("索引不合法：" + index);
        } else if (index > size - 1) {
            throw new RuntimeException("索引越界：" + index);
        }
    }

    public E get(int index) {
        checkRange(index);
        return (E) elementData[index];
    }

    public void set(E element, int index) {
        checkRange(index);
        elementData[index] = element;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Object temp : elementData
        ) {
            sb.append(temp + ",");
        }
        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }

    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<>(10);
        for (int i = 0; i < 15; i++) {
            list.add("lin" + i);
        }

        list.remove("lin1");
        list.remove(4);
        System.out.println(list.size());
        System.out.println(list.isEmpty());
        System.out.println(list);
    }
}
