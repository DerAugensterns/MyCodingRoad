package cn.adiong.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Author: 阿威
 * @Date: 2020/9/14 9:35
 * @Description：
 */
public class Reflection {
    public static void main(String[] args) {
        String path = "cn.adiong.reflect.Student";
        try {
            /**
             * Declared获取所有声明了的变量、方法
             */
            Class clazz = Class.forName(path);

            //反射操作变量
            Field[] fields = clazz.getDeclaredFields();
            for (Field f : fields
            ) {
                System.out.println(f.getName());
            }

            //反射操作方法(构造器,注意此处参数的顺序和类型)
            Constructor constructor = clazz.getConstructor(int.class, String.class, int.class);
            Student student = (Student) constructor.newInstance(17, "阿威", 17);
            Method method = clazz.getDeclaredMethod("setName", String.class);
            //不执行安全检查，提高效率，可访问private
            method.setAccessible(true);
            method.invoke(student, "xixi");
            System.out.println(student.getName());

            //反射操作泛型


            //反射操作注解————参见AnnotationParse.class

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
