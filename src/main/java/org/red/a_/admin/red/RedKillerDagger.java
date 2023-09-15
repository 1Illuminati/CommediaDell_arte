package org.red.a_.admin.red;

import org.bukkit.NamespacedKey;
import org.red.CommediaDell_arte;
import org.red.library.item.event.EventItem;
import org.red.library.item.event.EventItemAnnotation;

public final class RedKillerDagger implements EventItem {
    @Override
    public NamespacedKey getKey() {
        return new NamespacedKey(CommediaDell_arte.getPlugin(), "RedKillerDagger");
    }

    @EventItemAnnotation(act = EventItemAnnotation.Act.SWAP_HAND, shift = EventItemAnnotation.Shift.BOTH)
    public void swapHand() {

    }
}
