package org.red.library.event.area.entity;

import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.red.library.event.area.AreaEvent;
import org.red.library.world.Area;

public class AreaEntitySpawnEvent extends AreaEvent<EntitySpawnEvent> {
    public AreaEntitySpawnEvent(Area area, EntitySpawnEvent event) {
        super(area, event);
    }

    public static HandlerList getHandlerList() {
        return handler_map.computeIfAbsent(AreaEntitySpawnEvent.class, k -> new HandlerList());
    }
}
