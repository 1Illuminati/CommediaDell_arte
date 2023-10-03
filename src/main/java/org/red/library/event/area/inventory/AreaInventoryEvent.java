package org.red.library.event.area.inventory;

import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.inventory.Inventory;
import org.red.library.event.area.AreaEvent;
import org.red.library.world.Area;

public abstract class AreaInventoryEvent<T extends InventoryEvent> extends AreaEvent<T> {
    private final Inventory inventory;
    public AreaInventoryEvent(Area area, T event) {
        super(area, event);
        this.inventory = event.getInventory();
    }

    public Inventory getInventory() {
        return inventory;
    }
}
