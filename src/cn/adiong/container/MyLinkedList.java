package cn.adiong.container;

import java.util.LinkedList;

public class MyLinkedList<E> {
    private Node first;
    private Node last;
    private int size;

    public void checkRange(int index) {
        if (index < 0 || index > size - 1) {
            throw new RuntimeException("索引异常：" + index);
        }
    }

    public void add(E element) {
        Node n = new Node(element);
        if (first == null) {
            first = n;
            last = n;
        } else {
            last.next = n; //先后继再前驱
            n.previous = last;
            n.next = null;
            last = n; //用于进行下一次插入
        }
        size++;
    }

    public E get(int index) {
        Node temp = getNode(index);
        return temp == null ? null : (E) temp.element;
    }

    private Node getNode(int index) {
        checkRange(index);
        Node temp = null;
        if (index < (size >> 1)) {
            temp = first;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
        } else {
            temp = last;
            for (int i = size - 1; i > index; i--) {
                temp = temp.previous;
            }
        }
        return temp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node temp = first;
        while (temp != null) {
            sb.append(temp.element + ",");
            temp = temp.next;
        }
        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }

    public void remove(int index) {
        Node temp = getNode(index);
        Node up = temp.previous;
        Node down = temp.next;

        checkRange(index);

        if (temp != null) {
            if (up != null) {
                up.next = down;
            }

            if (down != null) {
                down.previous = up;
            }

            //删除队首
            if (index == 0) {
                first = down;
            }

            //删除队尾
            if (index == size - 1) {
                last = up;
            }
        }
        size--;
    }

    public void insert(E element, int index) {
        Node n = new Node(element);
        if (index >= 0 && index < size) {
            Node temp = getNode(index);
            Node up = temp.previous;
            if (temp != null) {
                if (up != null) {
                    // 先后继再前驱
                    n.next = temp;
                    n.previous = up;
                    up.next = n;
                    temp.previous = n;
                } else {
                    n.next = temp;
                    temp.previous = n;
                    first = n;
                }
            }
        } else if (index == size) {
            add(element);
        } else {
            checkRange(index);
        }
        size++;
    }

    public static void main(String[] args) {
        MyLinkedList<String> list = new MyLinkedList<>();
        LinkedList list01;
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        System.out.println(list);
        list.insert("e", 5);
        System.out.println(list);
    }
}

class Node {
    Node previous;
    Node next;
    Object element;

    public Node(Object element) {
        this.element = element;
    }
}
