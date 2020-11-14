package com.fh.annotatio;

import java.lang.annotation.*;

/**
 * 自定义注解
 * 用于登录拦截,放开拦截
 * 方法上加上该注解则不被拦截
 *
 */
@Target(ElementType.METHOD)//指明该注解只能用在方法上
@Retention(RetentionPolicy.RUNTIME)//指明将注解保留至运行时
@Documented
public @interface Ignore {
}
