package org.red.library.event.area.entity;

import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.red.library.world.Area;

public class AreaEntityDamageByEntityEvent extends AreaEntityEvent<EntityDamageByEntityEvent> {
    public AreaEntityDamageByEntityEvent(Area area, EntityDamageByEntityEvent event) {
        super(area, event);
    }

    public static HandlerList getHandlerList() {
        return handler_map.computeIfAbsent(AreaEntityDamageByEntityEvent.class, k -> new HandlerList());
    }
}
