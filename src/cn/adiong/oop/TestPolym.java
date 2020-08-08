package cn.adiong.oop;

public class TestPolym {
    public static void main(String[] args) {
        Animal a = new Cat();
        a.shout();
    }

}

class Animal {
    public void shout() {
        System.out.println("wc");
    }

}

class Dog extends Animal {
    public void shout() {
        System.out.println("wang wang");
    }
}

class Cat extends Animal {
    public void shout() {
        System.out.println("miao miao");
    }
}