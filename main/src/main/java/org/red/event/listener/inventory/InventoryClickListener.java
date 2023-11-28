package org.red.event.listener.inventory;

import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.red.library.event.area.AreaEvent;
import org.red.library.event.area.inventory.AreaInventoryClickEvent;
import org.red.library.inventory.CustomGui;

public class InventoryClickListener extends AbstractInventoryListener<InventoryClickEvent> {
    @Override
    @EventHandler
    public void onEvent(InventoryClickEvent event) {
        super.runAreaInventoryEvent(event);
        Inventory inventory = event.getView().getTopInventory();
        if (inventory.getHolder() == null) return;
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
        return AreaInventoryClickEvent.class;
    }

    @Override
    protected Class<InventoryClickEvent> getEventClass() {
        return InventoryClickEvent.class;
    }
}