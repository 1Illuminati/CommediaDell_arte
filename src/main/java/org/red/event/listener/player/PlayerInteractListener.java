package org.red.event.listener.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.red.library.entity.a_.player.A_Player;
import org.red.library.event.area.AreaEvent;
import org.red.library.event.area.player.AreaPlayerInteractEvent;
import org.red.event.listener.AbstractListener;
import org.red.library.item.event.EventItemAnnotation;
import org.red.library.item.event.EventItemManager;

public class PlayerInteractListener extends AbstractListener<PlayerInteractEvent> {
    @Override
    @EventHandler
    public void onEvent(PlayerInteractEvent event) {
        super.runAreaPlayerEvent(event);

        if (event.getHand() == EquipmentSlot.OFF_HAND) return;
        A_Player player = A_Player.getAPlayer(event.getPlayer());
        Action action = event.getAction();

        EventItemAnnotation.Act act = EventItemAnnotation.Act.valueOf(action.name());
        EventItemManager.runItemEvent(player, player.getInventory().getItemInMainHand(), act, event);
    }

    @Override
    protected Class<? extends AreaEvent<PlayerInteractEvent>> getAreaEventClass() {
        return AreaPlayerInteractEvent.class;
    }
}
