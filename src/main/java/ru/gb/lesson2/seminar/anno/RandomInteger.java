package ru.gb.lesson2.seminar.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
public @interface RandomInteger {

  int minValue() default -100;

  int maxValue() default 100;

}
