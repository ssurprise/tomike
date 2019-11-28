package com.skx.tomike.apt_annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface Coder {

    String author();

    String date() default "";
}
