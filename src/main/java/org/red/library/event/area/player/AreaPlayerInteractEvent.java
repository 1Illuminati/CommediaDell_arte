package org.red.library.event.area.player;

import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerInteractEvent;
import org.red.library.event.area.AreaEvent;
import org.red.library.world.Area;

public class AreaPlayerInteractEvent extends AreaEvent<PlayerInteractEvent> {
    public AreaPlayerInteractEvent(Area area, PlayerInteractEvent event) {
        super(area, event);
    }

    public static HandlerList getHandlerList() {
        return handler_map.computeIfAbsent(AreaPlayerInteractEvent.class, k -> new HandlerList());
    }
}
