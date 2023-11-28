package org.red.item.randombox;

import org.bukkit.NamespacedKey;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;
import org.red.CommediaDell_arte;
import org.red.library.interactive.InteractiveAnnotation;
import org.red.library.interactive.item.InteractiveItem;
import org.red.library.interactive.item.InteractiveItemAct;

public class InteractiveRandomBox implements InteractiveItem {
    @NotNull
    @Override
    public NamespacedKey getKey() {
        return new NamespacedKey(CommediaDell_arte.getPlugin(), "randomBox");
    }

    @InteractiveAnnotation(act = InteractiveItemAct.RIGHT_CLICK_AIR.class, shift = true)
    public void shiftRightClickAir(PlayerInteractEvent event) {

    }

    @InteractiveAnnotation(act = InteractiveItemAct.RIGHT_CLICK_BLOCK.class, shift = true)
    public void shiftRightClickBlock(PlayerInteractEvent event) {
        this.shiftRightClickAir(event);
    }

    @InteractiveAnnotation(act = InteractiveItemAct.RIGHT_CLICK_AIR.class)
    public void rightClickAir(PlayerInteractEvent event) {

    }

    @InteractiveAnnotation(act = InteractiveItemAct.RIGHT_CLICK_BLOCK.class)
    public void rightClickBlock(PlayerInteractEvent event) {
        this.rightClickAir(event);
    }
}
