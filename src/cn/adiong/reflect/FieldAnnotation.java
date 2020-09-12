package cn.adiong.reflect;

import java.lang.annotation.*;

/**
 * @Author: 阿威
 * @Date: 2020/9/12 11:18
 * @Description：
 */
@Target(value={ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FieldAnnotation {
    String argName();

    String argType();

    int argSize();
}
