package cn.adiong.designpattern;

/**
 * @Author: 阿威
 * @Date: 2020/9/19 10:43
 * @Description：
 */
public class BuilderPattern {
    public static void main(String[] args) {
        AirshipDirector director = new MyAirshipDirector(new MyAirshipBuilder());
        Airship airship = director.directAirship();
        airship.run();
    }
}

class Engine {
    private String name;

    public Engine(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class EscapeTower {
    private String name;

    public EscapeTower(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class OrbitalModule {
    private String name;

    public OrbitalModule(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Airship {
    private Engine e;
    private EscapeTower et;
    private OrbitalModule om;

    public void run() {
        System.out.println("启动" + e.getName() + ",脱离" + et.getName() + ",运行" + om.getName());
    }

    public Airship() {
    }

    public Airship(Engine e, EscapeTower et, OrbitalModule om) {
        this.e = e;
        this.et = et;
        this.om = om;
    }

    public Engine getE() {
        return e;
    }

    public void setE(Engine e) {
        this.e = e;
    }

    public EscapeTower getEt() {
        return et;
    }

    public void setEt(EscapeTower et) {
        this.et = et;
    }

    public OrbitalModule getOm() {
        return om;
    }

    public void setOm(OrbitalModule om) {
        this.om = om;
    }
}

/**
 * 建造者和装配者接口（可以有多种建造和装配方法）
 * 建造者和工厂模式可配合使用
 */

interface AirshipBuilder {
    Engine createEngine();

    EscapeTower createEscapeTower();

    OrbitalModule createOrbitalModule();
}

interface AirshipDirector {
    Airship directAirship();
}

class MyAirshipBuilder implements AirshipBuilder {

    @Override
    public Engine createEngine() {
        return new Engine("坏的发动机");
    }

    @Override
    public EscapeTower createEscapeTower() {
        return new EscapeTower("喜欢逃逸塔");
    }

    @Override
    public OrbitalModule createOrbitalModule() {
        return new OrbitalModule("不转轨道舱");
    }
}

class MyAirshipDirector implements AirshipDirector {
    AirshipBuilder builder;

    public MyAirshipDirector(AirshipBuilder builder) {
        this.builder = builder;
    }

    @Override
    public Airship directAirship() {
        Engine e = builder.createEngine();
        EscapeTower es = builder.createEscapeTower();
        OrbitalModule om = builder.createOrbitalModule();
        Airship airship = new Airship(e, es, om);
        return airship;
    }
}

