package cn.adiong.designpattern;

/**
 * @Author: 阿威
 * @Date: 2020/9/24 10:13
 * @Description： 责任链模式 OCP
 */
public class ChainOfResponsibility {
    public static void main(String[] args) {
        Leader director = new Director("张三");
        Leader manager = new Manager("李四");
        Leader boss = new Boss("王五");
        //创建责任链顺序(可以使用容器（数组）创造天然顺序的责任链)
        director.setNextLeader(manager);
        manager.setNextLeader(boss);

        LeaveRequest request = new LeaveRequest("陈二", 15, "去北京");
        director.doRequst(request);
    }
}

class LeaveRequest {
    private String name;
    private int leaveDay;
    private String season;

    public LeaveRequest() {

    }

    public LeaveRequest(String name, int leaveDay, String season) {
        this.name = name;
        this.leaveDay = leaveDay;
        this.season = season;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLeaveDay() {
        return leaveDay;
    }

    public void setLeaveDay(int leaveDay) {
        this.leaveDay = leaveDay;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }
}

abstract class Leader {
    protected String name;
    /**
     * 存储下一个责任者
     */
    protected Leader nextLeader;

    public Leader(String name) {
        this.name = name;
    }

    public void setNextLeader(Leader nextLeader) {
        this.nextLeader = nextLeader;
    }

    public abstract void doRequst(LeaveRequest request);
}

class Director extends Leader {

    public Director(String name) {
        super(name);
    }

    @Override
    public void doRequst(LeaveRequest request) {
        if (request.getLeaveDay() < 3) {
            System.out.print(this.name + "批准了" + " ");
            System.out.println(request.getName() + "的" + request.getLeaveDay() + "天假期");
        } else {
            this.nextLeader.doRequst(request);
        }
    }
}

class Manager extends Leader {

    public Manager(String name) {
        super(name);
    }

    @Override
    public void doRequst(LeaveRequest request) {
        if (request.getLeaveDay() >= 3 && request.getLeaveDay() < 10) {
            System.out.print(this.name + "批准了" + " ");
            System.out.println(request.getName() + "的" + request.getLeaveDay() + "天假期");
        } else {
            this.nextLeader.doRequst(request);
        }
    }
}

class Boss extends Leader {

    public Boss(String name) {
        super(name);
    }

    @Override
    public void doRequst(LeaveRequest request) {
        if (request.getLeaveDay() < 30) {
            System.out.print(this.name + "批准了" + " ");
            System.out.println(request.getName() + "的" + request.getLeaveDay() + "天假期");
        } else {
            System.out.println("莫非" + request.getName() + "想辞职");
        }
    }
}


