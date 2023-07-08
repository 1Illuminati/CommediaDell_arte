package org.red.library.item.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventItemAnnotation {
    Act act();

    enum Act {
        LEFT_CLICK_AIR,
        SHIFT_LEFT_CLICK_AIR,
        RIGHT_CLICK_AIR,
        SHIFT_RIGHT_CLICK_AIR,
        LEFT_CLICK_BLOCK,
        SHIFT_LEFT_CLICK_BLOCK,
        RIGHT_CLICK_BLOCK,
        SHIFT_RIGHT_CLICK_BLOCK,
        DROP,
        SHIFT_DROP,
        SWAP_HAND,
        SHIFT_SWAP_HAND,
        HIT,
        BREAK,
        FISHING,
    }
}
