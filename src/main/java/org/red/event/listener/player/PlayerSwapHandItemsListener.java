package org.red.event.listener.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.event.area.AreaEvent;
import org.red.library.event.area.player.AreaPlayerSwapHandItemsEvent;
import org.red.event.listener.AbstractListener;
import org.red.library.item.event.EventItemAnnotation;
import org.red.library.item.event.EventItemManager;
import org.red.library.world.WorldData;
import org.red.library.world.rule.Rule;

public class PlayerSwapHandItemsListener extends AbstractListener<PlayerSwapHandItemsEvent> {
    @Override
    @EventHandler
    public void onEvent(PlayerSwapHandItemsEvent event) {
        super.runAreaPlayerEvent(event);

        A_Player player = A_Player.getAPlayer(event.getPlayer());
        EventItemManager.runItemEvent(player, player.getInventory().getItemInMainHand(), EventItemAnnotation.Act.SWAP_HAND, event);

        WorldData worldData = WorldData.getWorldData(player.getWorld());

        if (!worldData.getRuleValue(Rule.SWAP_HAND, player.getLocation())) event.setCancelled(true);
    }

    @Override
    protected Class<? extends AreaEvent<PlayerSwapHandItemsEvent>> getAreaEventClass() {
        return AreaPlayerSwapHandItemsEvent.class;
    }
}