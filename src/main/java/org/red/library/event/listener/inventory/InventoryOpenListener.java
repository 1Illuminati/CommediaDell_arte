package org.red.library.event.listener.inventory;

import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.red.library.event.listener.AbstractListener;
import org.red.library.inventory.CustomGui;

public class InventoryOpenListener extends AbstractListener<InventoryOpenEvent> {
    @EventHandler
    public void onEvent(InventoryOpenEvent event) {
        Inventory inventory = event.getInventory();
        if (inventory.getHolder() == null) return;
        if (!(inventory.getHolder() instanceof CustomGui)) return;
        CustomGui customGui = (CustomGui) inventory.getHolder();

        try {
            customGui.onOpen(event);
        } catch(UnsupportedOperationException ignore) {}
    }
}
