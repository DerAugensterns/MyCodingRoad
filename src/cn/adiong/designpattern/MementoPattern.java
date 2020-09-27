package cn.adiong.designpattern;

/**
 * @Author: 阿威
 * @Date: 2020/9/27 11:27
 * @Description：
 */
public class MementoPattern {
    public static void main(String[] args) {
        User user = new User(5, "pp", 30000);
        System.out.println("初始数据输出:" + user.toString());
        CareTaker careTaker = new CareTaker();
        careTaker.setUserMemento(user.saveCopy());
        user.setId(6);
        user.setName("xx");
        user.setSalary(3000000);
        System.out.println("修改后输出：" + user.toString());
        user.recover(careTaker.getUserMemento());
        System.out.println("恢复备份后输出：" + user.toString());
    }
}

class User {
    private int id;
    private String name;
    private double salary;

    /**
     * 备份并返回备份对象
     */
    public UserMemento saveCopy() {
        return new UserMemento(this);
    }

    /**
     * 恢复备份
     */
    public void recover(UserMemento userMemento) {
        this.id = userMemento.getId();
        this.name = userMemento.getName();
        this.salary = userMemento.getSalary();
    }

    public User() {
    }

    public User(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}

class UserMemento {
    private int id;
    private String name;
    private double salary;

    public UserMemento(User user) {
        id = user.getId();
        name = user.getName();
        salary = user.getSalary();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }


}

/**
 * 用于保存管理多个备忘录
 */
class CareTaker {
    private UserMemento userMemento;
    //用栈作为备忘录容器（LIFO）
    //private Stack<UserMemento> stack = new Stack<>();

    public UserMemento getUserMemento() {
        return userMemento;
    }

    public void setUserMemento(UserMemento userMemento) {
        this.userMemento = userMemento;
    }

}