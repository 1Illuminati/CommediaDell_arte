package org.red.library.interactive.block;

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.red.library.interactive.InteractiveActAnnotation;
import org.red.library.interactive.InteractiveAct;

public interface InteractiveTileAct extends InteractiveAct {
    @InteractiveActAnnotation(event = BlockBreakEvent.class)
    class BREAK implements InteractiveTileAct {}
    @InteractiveActAnnotation(event = PlayerInteractEvent.class)
    class LEFT_CLICK_BLOCK implements InteractiveTileAct {}
    @InteractiveActAnnotation(event = PlayerInteractEvent.class)
    class RIGHT_CLICK_BLOCK implements InteractiveTileAct {}
}
