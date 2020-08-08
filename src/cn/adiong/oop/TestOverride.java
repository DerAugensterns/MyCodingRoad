package cn.adiong.oop;

public class TestOverride {

    Object obj;
    public  String toString(){
       return "I Love You";
    }

    public static void main(String[] args) {
        TestOverride tr = new  TestOverride();
        System.out.println(tr.toString());
    }
}
