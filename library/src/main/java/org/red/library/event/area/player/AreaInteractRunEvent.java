package org.red.library.event.area.player;

import org.bukkit.event.HandlerList;
import org.red.library.world.Area;
import org.red.library.event.InteractiveRunEvent;

public class AreaInteractRunEvent extends AreaPlayerEvent<InteractiveRunEvent> {
    protected AreaInteractRunEvent(Area area, InteractiveRunEvent event) {
        super(area, event);
    }

    public static HandlerList getHandlerList() {
        return handler_map.computeIfAbsent(AreaInteractRunEvent.class, k -> new HandlerList());
    }
}
