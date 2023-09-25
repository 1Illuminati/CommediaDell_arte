package org.red.library.event.area.inventory;

import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.red.library.event.area.AreaEvent;
import org.red.library.world.Area;

public class AreaInventoryCloseEvent extends AreaEvent<InventoryCloseEvent> {
    public AreaInventoryCloseEvent(Area area, InventoryCloseEvent event) {
        super(area, event);
    }

    public static HandlerList getHandlerList() {
        return handler_map.computeIfAbsent(AreaInventoryCloseEvent.class, k -> new HandlerList());
    }
}
