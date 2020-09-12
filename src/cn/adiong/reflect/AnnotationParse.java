package cn.adiong.reflect;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @Author: 阿威
 * @Date: 2020/9/12 11:36
 * @Description：
 */
public class AnnotationParse {
    public static void main(String[] args) {
        try {
            Class clazz = Class.forName("cn.adiong.reflect.Student");

            //获得所有type注解
            Annotation[] annotations = clazz.getAnnotations();
            for (Annotation a : annotations
            ) {
                System.out.println(a);
            }

            //获得指定type注解
            TypeAnnotation ta = (TypeAnnotation) clazz.getAnnotation(TypeAnnotation.class);
            System.out.println(ta.value());

            //获取指定Field
            Field field = clazz.getDeclaredField("id");
            //通过field获取annotation
            FieldAnnotation fa = field.getAnnotation(FieldAnnotation.class);
            System.out.println(fa.argName() + "--" + fa.argType() + "--" + fa.argSize());

        } catch (ClassNotFoundException | NoSuchFieldException e) {
            e.printStackTrace();
        }

    }
}
