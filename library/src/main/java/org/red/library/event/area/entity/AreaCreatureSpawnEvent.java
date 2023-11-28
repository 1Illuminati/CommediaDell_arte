package org.red.library.event.area.entity;

import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.red.library.world.Area;

public class AreaCreatureSpawnEvent extends AreaEntityEvent<CreatureSpawnEvent> {
    public AreaCreatureSpawnEvent(Area area, CreatureSpawnEvent event) {
        super(area, event);
    }

    public static HandlerList getHandlerList() {
        return handler_map.computeIfAbsent(AreaCreatureSpawnEvent.class, k -> new HandlerList());
    }
}
