package org.red.library.event.listener.inventory;

import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.red.library.event.area.AreaEvent;
import org.red.library.event.listener.AbstractListener;
import org.red.library.inventory.CustomGui;

public class InventoryClickListener extends AbstractListener<InventoryClickEvent> {
    @Override
    @EventHandler
    public void onEvent(InventoryClickEvent event) {
        Inventory inventory = event.getClickedInventory();
        if (inventory == null || inventory.getHolder() == null) return;
        if (!(inventory.getHolder() instanceof CustomGui)) return;
        CustomGui customGui = (CustomGui) inventory.getHolder();

        int clickedSlot = event.getRawSlot();
        if (customGui.hasButton(clickedSlot))
            customGui.getButton(clickedSlot).onClick(event);

        try {
            customGui.onClick(event);
        } catch (UnsupportedOperationException ignore) {}
    }

    @Override
    protected Class<? extends AreaEvent<InventoryClickEvent>> getAreaEventClass() {
        return null;
    }
}