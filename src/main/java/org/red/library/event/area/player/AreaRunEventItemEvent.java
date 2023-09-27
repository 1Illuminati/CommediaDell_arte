package org.red.library.event.area.player;

import org.bukkit.event.HandlerList;
import org.red.library.event.InteractiveRunEvent;
import org.red.library.event.area.AreaEvent;
import org.red.library.world.Area;

public class AreaRunEventItemEvent extends AreaEvent<InteractiveRunEvent> {
    protected AreaRunEventItemEvent(Area area, InteractiveRunEvent event) {
        super(area, event);
    }

    public static HandlerList getHandlerList() {
        return handler_map.computeIfAbsent(AreaRunEventItemEvent.class, k -> new HandlerList());
    }
}
