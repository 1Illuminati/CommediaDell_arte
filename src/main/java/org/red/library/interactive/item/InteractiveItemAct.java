package org.red.library.interactive.item;

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.red.library.interactive.InteractiveAct;
import org.red.library.interactive.InteractiveActAnnotation;

public interface InteractiveItemAct extends InteractiveAct {

    @InteractiveActAnnotation(event = PlayerInteractEvent.class)
    class LEFT_CLICK_AIR implements InteractiveItemAct {}
    @InteractiveActAnnotation(event = PlayerInteractEvent.class)
    class RIGHT_CLICK_AIR implements InteractiveItemAct {}
    @InteractiveActAnnotation(event = PlayerInteractEvent.class)
    class LEFT_CLICK_BLOCK implements InteractiveItemAct {}
    @InteractiveActAnnotation(event = PlayerInteractEvent.class)
    class RIGHT_CLICK_BLOCK implements InteractiveItemAct {}
    @InteractiveActAnnotation(event = PlayerInteractEvent.class)
    class PHYSICAL implements InteractiveItemAct {}
    @InteractiveActAnnotation(event = BlockBreakEvent.class)
    class BREAK implements InteractiveItemAct {}
    @InteractiveActAnnotation(event = PlayerFishEvent.class)
    class FISH implements InteractiveItemAct {}
    @InteractiveActAnnotation(event = EntityDamageByEntityEvent.class)
    class HIT implements InteractiveItemAct {}
    @InteractiveActAnnotation(event = PlayerDropItemEvent.class)
    class DROP implements InteractiveItemAct {}
    @InteractiveActAnnotation(event = PlayerSwapHandItemsEvent.class)
    class SWAP_HAND implements InteractiveItemAct {}
}
