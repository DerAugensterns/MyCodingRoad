package cn.adiong.oop;

import java.util.Objects;

public class TestEquals {
    public static void main(String[] args) {
        User u1=new User(1,"ll");
        User u2=new User(1,"xx");
        System.out.println(u1==u2);
        System.out.println(u1.equals(u2));
    }

}

class User{
    int id;
    String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public User() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id ;
    }



}