package org.red.library.event.area.inventory;

import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.red.library.event.area.AreaEvent;
import org.red.library.world.Area;

public class AreaInventoryOpenEvent extends AreaEvent<InventoryOpenEvent> {
    public AreaInventoryOpenEvent(Area area, InventoryOpenEvent event) {
        super(area, event);
    }

    public static HandlerList getHandlerList() {
        return handler_map.computeIfAbsent(AreaInventoryOpenEvent.class, k -> new HandlerList());
    }
}
