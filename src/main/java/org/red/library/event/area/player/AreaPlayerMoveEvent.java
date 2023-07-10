package org.red.library.event.area.player;

import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerMoveEvent;
import org.red.library.event.area.AreaEvent;
import org.red.library.world.Area;

public class AreaPlayerMoveEvent extends AreaEvent<PlayerMoveEvent> {
    public AreaPlayerMoveEvent(Area area, PlayerMoveEvent event) {
        super(area, event);
    }

    public static HandlerList getHandlerList() {
        return handler_map.computeIfAbsent(AreaPlayerMoveEvent.class, k -> new HandlerList());
    }
}
