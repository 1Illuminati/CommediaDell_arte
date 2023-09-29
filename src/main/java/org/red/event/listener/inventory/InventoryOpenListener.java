package org.red.event.listener.inventory;

import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.red.library.event.area.AreaEvent;
import org.red.event.listener.AbstractListener;
import org.red.library.event.area.inventory.AreaInventoryOpenEvent;
import org.red.library.inventory.CustomGui;

public class InventoryOpenListener extends AbstractListener<InventoryOpenEvent> {
    @Override
    @EventHandler
    public void onEvent(InventoryOpenEvent event) {
        super.runAreaInventoryEvent(event);
        Inventory inventory = event.getInventory();
        if (inventory.getHolder() == null) return;
        if (!(inventory.getHolder() instanceof CustomGui)) return;
        CustomGui customGui = (CustomGui) inventory.getHolder();

        try {
            customGui.onOpen(event);
        } catch(UnsupportedOperationException ignore) {}
    }

    @Override
    protected Class<? extends AreaEvent<InventoryOpenEvent>> getAreaEventClass() {
        return AreaInventoryOpenEvent.class;
    }
}
