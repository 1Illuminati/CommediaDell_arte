package org.red.library.event.listener.player;

import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.red.library.entity.a_.player.A_Player;
import org.red.library.event.area.AreaEvent;
import org.red.library.event.area.player.AreaPlayerInteractEvent;
import org.red.library.event.listener.AbstractListener;
import org.red.library.item.event.EventItemAnnotation;
import org.red.library.item.event.EventItemManager;

public class PlayerInteractListener extends AbstractListener<PlayerInteractEvent> {
    @Override
    public void onEvent(PlayerInteractEvent event) {
        super.runAreaPlayerEvent(event);

        if (event.getHand() == EquipmentSlot.OFF_HAND) return;
        A_Player player = APlayer.getAPlayer(event.getPlayer());
        Action action = event.getAction();

        EventItemAnnotation.Act act = EventItemAnnotation.Act.valueOf((player.isSneaking() ? "SHIFT_" : "") + action.name());
        EventItemManager.runItemEvent(player, player.getInventory().getItemInMainHand(), act, event);
    }

    @Override
    protected Class<? extends AreaEvent<PlayerInteractEvent>> getAreaEventClass() {
        return AreaPlayerInteractEvent.class;
    }
}
