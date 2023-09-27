package org.red.library.interactive.block;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InteractiveTileAnnotation {

    Act act();

    boolean shift() default false;

    enum Act {
        LEFT_CLICK,
        RIGHT_CLICK,
        PHYSICAL,
        BREAK,
    }
}
