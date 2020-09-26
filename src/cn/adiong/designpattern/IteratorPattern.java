package cn.adiong.designpattern;

import java.util.List;
import java.util.ArrayList;

/**
 * @Author: 阿威
 * @Date: 2020/9/24 10:55
 * @Description： 迭代器模式————迭代器底层实现（迭代器内部类）
 */
public class IteratorPattern {
    public static void main(String[] args) {
        MyAggregate myAggregate = new MyAggregate();
        myAggregate.add("张三");
        myAggregate.add("赵四");
        myAggregate.add("王五");
        myAggregate.add("李六");
        MyIterator myIterator = myAggregate.createMyIterator();
        while (myIterator.hasNext()) {
            System.out.println(myIterator.getCurrent());
            myIterator.next();
        }
    }
}

class MyAggregate {
    private List<Object> list = new ArrayList<>();

    public void add(Object obj) {
        list.add(obj);
    }

    public void remove(Object obj) {
        list.remove(obj);
    }

    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }

    public MyIterator createMyIterator() {
        return new ConcreteIterator();
    }

    /**
     * 使用内部类定义迭代器，能直接访问外部对象
     */
    private class ConcreteIterator implements MyIterator {

        /**
         * 定义游标用于遍历访问
         */
        private int cursor;

        @Override
        public void first() {
            cursor = 0;
        }

        @Override
        public void next() {
            if (cursor < list.size()) {
                cursor++;
            }
        }

        @Override
        public Boolean hasNext() {
            return cursor < list.size() ? true : false;
        }

        @Override
        public Object getCurrent() {
            return list.get(cursor);
        }
    }

}

interface MyIterator {
    /**
     * 返回第一个对象
     */
    void first();

    /**
     * 游标跳转到下一个对象
     */
    void next();

    /**
     * 判断是否存在下一个对象
     *
     * @return
     */
    Boolean hasNext();

    /**
     * 返回当前对象值
     *
     * @return
     */
    Object getCurrent();
}
