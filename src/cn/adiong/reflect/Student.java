package cn.adiong.reflect;

/**
 * @Author: 阿威
 * @Date: 2020/9/12 11:18
 * @Description：
 */
@TypeAnnotation("Table_student")
public class Student {
    
    @FieldAnnotation(argName = "s_id", argType = "int", argSize = 10)
    private int id;
    @FieldAnnotation(argName = "s_name", argType = "varchar", argSize = 10)
    private String name;
    @FieldAnnotation(argName = "s_age", argType = "int", argSize = 10)
    private int age;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
