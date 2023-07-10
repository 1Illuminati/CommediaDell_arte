package org.red.library.event.area.player;

import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerFishEvent;
import org.red.library.event.area.AreaEvent;
import org.red.library.world.Area;

public class AreaPlayerFishEvent extends AreaEvent<PlayerFishEvent> {

    public AreaPlayerFishEvent(Area area, PlayerFishEvent event) {
        super(area, event);
    }

    public static HandlerList getHandlerList() {
        return handler_map.computeIfAbsent(AreaPlayerFishEvent.class, k -> new HandlerList());
    }
}
