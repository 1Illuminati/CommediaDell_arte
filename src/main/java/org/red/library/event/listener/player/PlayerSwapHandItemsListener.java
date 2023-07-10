package org.red.library.event.listener.player;

import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.red.library.entity.player.NewPlayer;
import org.red.library.event.area.AreaEvent;
import org.red.library.event.area.player.AreaPlayerSwapHandItemsEvent;
import org.red.library.event.listener.AbstractListener;
import org.red.library.item.event.EventItemAnnotation;
import org.red.library.item.event.EventItemManager;
import org.red.library.world.WorldData;
import org.red.library.world.rule.Rule;

public class PlayerSwapHandItemsListener extends AbstractListener<PlayerSwapHandItemsEvent> {
    @Override
    public void onEvent(PlayerSwapHandItemsEvent event) {
        super.runAreaPlayerEvent(event);

        NewPlayer player = NewPlayer.getNewPlayer(event.getPlayer());
        EventItemManager.runItemEvent(player, player.getInventory().getItemInMainHand(), player.isSneaking() ?
                EventItemAnnotation.Act.SHIFT_SWAP_HAND : EventItemAnnotation.Act.SWAP_HAND, event);

        WorldData worldData = WorldData.getWorldData(player.getWorld());

        if (worldData.getRuleValue(Rule.SWAP_HAND)) event.setCancelled(true);
    }

    @Override
    protected Class<? extends AreaEvent<PlayerSwapHandItemsEvent>> getAreaEventClass() {
        return AreaPlayerSwapHandItemsEvent.class;
    }
}
