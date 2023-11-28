package org.red.library.event.area.entity;

import org.bukkit.event.HandlerList;
import org.red.library.world.Area;
import org.spigotmc.event.entity.EntityMountEvent;

public class AreaEntityMountEvent extends AreaEntityEvent<EntityMountEvent> {
    public AreaEntityMountEvent(Area area, EntityMountEvent event) {
        super(area, event);
    }

    public static HandlerList getHandlerList() {
        return handler_map.computeIfAbsent(AreaEntityMountEvent.class, k -> new HandlerList());
    }
}
