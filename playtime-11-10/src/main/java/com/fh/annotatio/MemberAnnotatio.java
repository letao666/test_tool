package com.fh.annotatio;

import java.lang.annotation.*;

@Target(ElementType.PARAMETER)//指明该注解只能用在参数上
@Retention(RetentionPolicy.RUNTIME)//指明将注解保留至运行时
@Documented
public @interface MemberAnnotatio {
}
