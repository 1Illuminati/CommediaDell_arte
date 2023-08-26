package org.red.library.item.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventItemAnnotation {
    Act act();

    Shift shift() default Shift.NOT_PRESSED;

    enum Shift {
        PRESSED,
        NOT_PRESSED,
        BOTH
    }

    enum Act {
        LEFT_CLICK_AIR,
        RIGHT_CLICK_AIR,
        LEFT_CLICK_BLOCK,
        RIGHT_CLICK_BLOCK,
        PHYSICAL,
        DROP,
        SWAP_HAND,
        HIT,
        BREAK,
        FISHING,
    }
}
