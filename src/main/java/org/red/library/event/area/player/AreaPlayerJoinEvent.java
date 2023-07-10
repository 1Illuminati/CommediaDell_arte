package org.red.library.event.area.player;

import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerJoinEvent;
import org.red.library.event.area.AreaEvent;
import org.red.library.world.Area;

public class AreaPlayerJoinEvent extends AreaEvent<PlayerJoinEvent> {
    protected AreaPlayerJoinEvent(Area area, PlayerJoinEvent event) {
        super(area, event);
    }

    public static HandlerList getHandlerList() {
        return handler_map.computeIfAbsent(AreaPlayerJoinEvent.class, k -> new HandlerList());
    }
}
