package org.red.library.event.area.player;

import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerQuitEvent;
import org.red.library.event.area.AreaEvent;
import org.red.library.world.Area;

public class AreaPlayerQuitEvent extends AreaEvent<PlayerQuitEvent> {
    protected AreaPlayerQuitEvent(Area area, PlayerQuitEvent event) {
        super(area, event);
    }

    public static HandlerList getHandlerList() {
        return handler_map.computeIfAbsent(AreaPlayerQuitEvent.class, k -> new HandlerList());
    }
}
