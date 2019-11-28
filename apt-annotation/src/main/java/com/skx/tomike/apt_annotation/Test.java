package com.skx.tomike.apt_annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 保留到编译阶段不加载进JVM
@Retention(RetentionPolicy.CLASS)
// 作用对象，FILED
@Target(ElementType.METHOD)
public @interface Test {
    int value();  // 属性名为value 的int变量
}
