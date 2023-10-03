package org.red.library.event.area.inventory;

import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.CraftItemEvent;
import org.red.library.world.Area;

public class AreaCraftItemEvent extends AreaInventoryEvent<CraftItemEvent> {
    public AreaCraftItemEvent(Area area, CraftItemEvent event) {
        super(area, event);
    }

    public static HandlerList getHandlerList() {
        return handler_map.computeIfAbsent(AreaCraftItemEvent.class, k -> new HandlerList());
    }
}
