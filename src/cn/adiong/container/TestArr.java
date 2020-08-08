package cn.adiong.container;

public class TestArr {
    public static void main(String[] args) {
        User Users[]={
                new User(1,"xx"),
                new User(2,"pp")
        };

        for (User u : Users
        ) {
            System.out.println(u.getName());
        }
    }
}

class User {
    private int id;
    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
