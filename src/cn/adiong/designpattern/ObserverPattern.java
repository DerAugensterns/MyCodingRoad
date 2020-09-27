package cn.adiong.designpattern;

import java.util.List;
import java.util.ArrayList;

/**
 * @Author: 阿威
 * @Date: 2020/9/27 10:33
 * @Description：
 */
public class ObserverPattern {
    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();
        ObserverA obs1 = new ObserverA();
        ObserverA obs2 = new ObserverA();
        ObserverA obs3 = new ObserverA();
        subject.register(obs1);
        subject.register(obs2);
        subject.register(obs3);

        subject.setState(3000);
        System.out.println("******************");
        System.out.println(obs1.getMyState());
        System.out.println(obs2.getMyState());
        System.out.println(obs3.getMyState());
        subject.setState(888);
        System.out.println("******************");
        System.out.println(obs1.getMyState());
        System.out.println(obs2.getMyState());
        System.out.println(obs3.getMyState());
    }
}

interface Observer {
    void update(Subject subject);
}

class Subject {
    /**
     * 观察者容器
     */
    protected List<Observer> list = new ArrayList<>();

    public void register(Observer obs) {
        list.add(obs);
    }

    public void remove(Observer obs) {
        list.remove(obs);
    }

    public void notifyAllObserver() {
        for (Observer obs : list
        ) {
            obs.update(this);
        }
    }
}

class ConcreteSubject extends Subject {
    private int state;

    public void setState(int state) {
        this.state = state;
        notifyAllObserver();
    }

    public int getState() {
        return state;
    }
}

class ObserverA implements Observer {

    private int myState;

    @Override
    public void update(Subject subject) {
        myState = ((ConcreteSubject) subject).getState();
    }

    public int getMyState() {
        return myState;
    }

    public void setMyState(int myState) {
        this.myState = myState;
    }
}