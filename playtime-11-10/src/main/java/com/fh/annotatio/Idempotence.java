package com.fh.annotatio;

import java.lang.annotation.*;

/**
 * 自定义注解
 * 方法上加上该注解则对该方法进行幂等性处理
 *
 */
@Target(ElementType.METHOD)//指明该注解只能用在方法上
@Retention(RetentionPolicy.RUNTIME)//指明将注解保留至运行时
@Documented
public @interface Idempotence {
}
