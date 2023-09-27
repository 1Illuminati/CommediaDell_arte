package org.red.library.interactive.block;

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.red.library.interactive.InteractiveAct;
import org.red.library.interactive.InteractiveActAnnotation;

public interface InteractiveBlockAct extends InteractiveAct {
    @InteractiveActAnnotation(event = BlockBreakEvent.class)
    class BREAK implements InteractiveBlockAct {}
    @InteractiveActAnnotation(event = PlayerInteractEvent.class)
    class SHIFT_LEFT_CLICK implements InteractiveBlockAct {}
    @InteractiveActAnnotation(event = PlayerInteractEvent.class)
    class SHIFT_RIGHT_CLICK implements InteractiveBlockAct {}
    @InteractiveActAnnotation(event = PlayerInteractEvent.class)
    class RIGHT_CLICK implements InteractiveBlockAct {}
    @InteractiveActAnnotation(event = PlayerInteractEvent.class)
    class LEFT_CLICK implements InteractiveBlockAct {}
}
