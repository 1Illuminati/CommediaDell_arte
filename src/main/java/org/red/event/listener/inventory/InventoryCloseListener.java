package org.red.event.listener.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.red.a_.entity.A_PlayerImpl;
import org.red.library.A_;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.event.area.AreaEvent;
import org.red.event.listener.AbstractListener;
import org.red.library.event.area.inventory.AreaInventoryCloseEvent;
import org.red.library.inventory.CustomGui;

public class InventoryCloseListener extends AbstractInventoryListener<InventoryCloseEvent> {
    @Override
    @EventHandler
    public void onEvent(InventoryCloseEvent event) {
        super.runAreaInventoryEvent(event);
        org.bukkit.inventory.Inventory inventory = event.getInventory();
        if (inventory.getHolder() == null) return;
        if (!(inventory.getHolder() instanceof CustomGui)) return;

        A_PlayerImpl player = (A_PlayerImpl) A_.getAPlayer((Player) event.getPlayer());
        if (player.isPlayerIgnoreCloseInvEvent()) {
            player.setPlayerIgnoreCloseInvEvent(false);
            return;
        }

        CustomGui customGui = (CustomGui) inventory.getHolder();

        try {
            customGui.onClose(event);
        } catch(UnsupportedOperationException ignore) {}
    }

    @Override
    protected Class<? extends AreaEvent<InventoryCloseEvent>> getAreaEventClass() {
        return AreaInventoryCloseEvent.class;
    }

    @Override
    protected Class<InventoryCloseEvent> getEventClass() {
        return InventoryCloseEvent.class;
    }
}