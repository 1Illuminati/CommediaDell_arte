package org.red.a_.admin.red;

import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;
import org.red.CommediaDell_arte;
import org.red.library.interactive.item.InteractiveItem;
import org.red.library.interactive.item.InteractiveItemAnnotation;

public final class RedKillerDagger implements InteractiveItem {
    @Override
    @NotNull
    public NamespacedKey getKey() {
        return new NamespacedKey(CommediaDell_arte.getPlugin(), "RedKillerDagger");
    }

    @InteractiveItemAnnotation(act = InteractiveItemAnnotation.Act.SWAP_HAND)
    public void swapHand() {

    }
}
