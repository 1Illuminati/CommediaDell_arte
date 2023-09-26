package org.red.a_.admin.red;

import org.bukkit.NamespacedKey;
import org.red.CommediaDell_arte;
import org.red.library.interactive.item.InteractiveItem;
import org.red.library.interactive.item.EventItemAnnotation;

public final class RedKillerDagger implements InteractiveItem {
    @Override
    public NamespacedKey getKey() {
        return new NamespacedKey(CommediaDell_arte.getPlugin(), "RedKillerDagger");
    }

    @EventItemAnnotation(act = EventItemAnnotation.Act.SWAP_HAND)
    public void swapHand() {

    }
}
