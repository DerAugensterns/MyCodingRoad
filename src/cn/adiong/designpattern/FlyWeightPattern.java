package cn.adiong.designpattern;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 阿威
 * @Date: 2020/9/22 10:23
 * @Description：
 */
public class FlyWeightPattern {
    public static void main(String[] args) {
        ChessFlyWeight c1 = ChessFlyWeightFactory.getChess("黑色");
        ChessFlyWeight c2 = ChessFlyWeightFactory.getChess("黑色");
        System.out.println(c1);
        System.out.println(c2);
        c1.display(new Coordinate(1, 20));
        c2.display(new Coordinate(20, 20));

    }
}

interface ChessFlyWeight {
    void setColor();

    String getColor();

    void display(Coordinate coordinate);
}

class ConcreteChess implements ChessFlyWeight {

    private String color;

    public ConcreteChess(String color) {
        this.color = color;
    }

    @Override
    public void setColor() {

    }

    @Override
    public String getColor() {
        return null;
    }

    @Override
    public void display(Coordinate coordinate) {
        System.out.println(coordinate.getX() + "---" + coordinate.getY());
    }
}

class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

class ChessFlyWeightFactory {
    private static Map<String, ChessFlyWeight> map = new HashMap<>();

    public static ChessFlyWeight getChess(String color) {
        if (map.get(color) != null) {
            return map.get(color);
        } else {
            ChessFlyWeight cfw = new ConcreteChess(color);
            map.put(color, cfw);
            return cfw;
        }
    }
}
