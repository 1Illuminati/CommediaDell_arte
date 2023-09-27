package org.red.library.interactive;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface InteractiveAnnotation {
    Class<? extends InteractiveAct> act();
    boolean shift() default false;
}
