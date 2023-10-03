package org.red.library.event.area.entity;

import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent;
import org.red.library.world.Area;

public class AreaEntityDamageEvent extends AreaEntityEvent<EntityDamageEvent> {

    public AreaEntityDamageEvent(Area area, EntityDamageEvent event) {
        super(area, event);
    }

    public static HandlerList getHandlerList() {
        return handler_map.computeIfAbsent(AreaEntityDamageEvent.class, k -> new HandlerList());
    }
}
