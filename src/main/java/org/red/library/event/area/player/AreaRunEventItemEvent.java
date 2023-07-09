package org.red.library.event.area.player;

import org.bukkit.event.HandlerList;
import org.red.library.event.RunEventItemEvent;
import org.red.library.event.area.AreaEvent;
import org.red.library.world.Area;

public class AreaRunEventItemEvent extends AreaEvent<RunEventItemEvent> {
    protected AreaRunEventItemEvent(Area area, RunEventItemEvent event) {
        super(area, event);
    }

    public static HandlerList getHandlerList() {
        return handler_map.computeIfAbsent(AreaRunEventItemEvent.class, k -> new HandlerList());
    }
}
