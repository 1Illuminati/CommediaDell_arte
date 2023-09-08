package org.red.library.event.listener.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.red.library.entity.a_.impl.A_PlayerImpl;
import org.red.library.entity.a_.player.A_Player;
import org.red.library.event.area.AreaEvent;
import org.red.library.event.listener.AbstractListener;
import org.red.library.inventory.CustomGui;

public class InventoryCloseListener extends AbstractListener<InventoryCloseEvent> {
    @Override
    @EventHandler
    public void onEvent(InventoryCloseEvent event) {
        org.bukkit.inventory.Inventory inventory = event.getInventory();
        if (inventory.getHolder() == null) return;
        if (!(inventory.getHolder() instanceof CustomGui)) return;

        A_PlayerImpl player = (A_PlayerImpl) A_Player.getAPlayer((Player) event.getPlayer());
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
        return null;
    }
}