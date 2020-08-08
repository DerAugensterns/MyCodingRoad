package cn.adiong.container;

import java.util.TreeSet;

public class TestTreeSet {
    public static void main(String[] args) {
        Emp e1 = new Emp(1, "adiong", 50000);
        Emp e2 = new Emp(6, "p", 12000);
        Emp e3 = new Emp(5, "c", 12000);
        Emp e4 = new Emp(2, "xx", 700);

        TreeSet<Emp> set = new TreeSet<>();
        set.add(e1);
        set.add(e2);
        set.add(e3);
        set.add(e4);
        for (Emp temp : set
        ) {
            System.out.println(temp);
        }
    }
}

//排序需要实现Comparable接口，重写ComparableTo方法定义排序规则
class Emp implements Comparable<Emp> {
    int id;
    String ename;
    int salary;

    public Emp() {
    }

    public Emp(int id, String ename, int salary) {
        this.id = id;
        this.ename = ename;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", ename='" + ename + '\'' +
                ", salary=" + salary +
                '}';
    }

    @Override
    public int compareTo(Emp e) {
        if (this.salary > e.salary) {
            return 1;
        } else if (this.salary < e.salary) {
            return -1;
        } else {
            if (this.id > e.id) {
                return 1;
            } else if (this.id < e.id) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}
