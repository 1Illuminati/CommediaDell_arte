package org.red.library.event.area.player;

import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.red.library.world.Area;

public class AreaPlayerSwapHandItemsEvent extends AreaPlayerEvent<PlayerSwapHandItemsEvent> {
    public AreaPlayerSwapHandItemsEvent(Area area, PlayerSwapHandItemsEvent event) {
        super(area, event);
    }

    public static HandlerList getHandlerList() {
        return handler_map.computeIfAbsent(AreaPlayerSwapHandItemsEvent.class, k -> new HandlerList());
    }
}
