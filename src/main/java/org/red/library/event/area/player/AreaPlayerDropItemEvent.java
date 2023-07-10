package org.red.library.event.area.player;

import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.red.library.event.area.AreaEvent;
import org.red.library.world.Area;

public class AreaPlayerDropItemEvent extends AreaEvent<PlayerDropItemEvent> {
    public AreaPlayerDropItemEvent(Area area, PlayerDropItemEvent event) {
        super(area, event);
    }

    public static HandlerList getHandlerList() {
        return handler_map.computeIfAbsent(AreaPlayerDropItemEvent.class, k -> new HandlerList());
    }
}
