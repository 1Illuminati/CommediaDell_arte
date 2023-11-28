package org.red.library.event.area.inventory;

import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.red.library.world.Area;

public class AreaInventoryClickEvent extends AreaInventoryEvent<InventoryClickEvent> {
    public AreaInventoryClickEvent(Area area, InventoryClickEvent event) {
        super(area, event);
    }

    public static HandlerList getHandlerList() {
        return handler_map.computeIfAbsent(AreaInventoryClickEvent.class, k -> new HandlerList());
    }
}
