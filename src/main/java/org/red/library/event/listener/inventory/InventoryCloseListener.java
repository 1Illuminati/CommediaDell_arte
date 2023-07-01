package org.red.library.event.listener.inventory;

import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.red.library.event.listener.AbstractListener;
import org.red.library.inventory.CustomGui;

public class InventoryCloseListener extends AbstractListener<InventoryCloseEvent> {
    @EventHandler
    public void onEvent(InventoryCloseEvent event) {
        org.bukkit.inventory.Inventory inventory = event.getInventory();
        if (inventory.getHolder() == null) return;
        if (!(inventory.getHolder() instanceof CustomGui)) return;
        CustomGui customGui = (CustomGui) inventory.getHolder();

        try {
            customGui.onClose(event);
        } catch(UnsupportedOperationException ignore) {}
    }
}