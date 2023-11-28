package org.red.a_.admin.red;

import org.bukkit.NamespacedKey;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;
import org.red.CommediaDell_arte;
import org.red.library.interactive.InteractiveAnnotation;
import org.red.library.interactive.item.InteractiveItem;
import org.red.library.interactive.item.InteractiveItemAct;

public final class RedKillerDagger implements InteractiveItem {
    @Override
    @NotNull
    public NamespacedKey getKey() {
        return new NamespacedKey(CommediaDell_arte.getPlugin(), "RedKillerDagger");
    }

    @InteractiveAnnotation(act = InteractiveItemAct.RIGHT_CLICK_BLOCK.class)
    public void rightClickAir(PlayerInteractEvent event) {

    }
}
