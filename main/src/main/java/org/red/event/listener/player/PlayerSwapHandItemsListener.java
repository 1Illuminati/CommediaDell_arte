package org.red.event.listener.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.red.library.A_;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.event.area.AreaEvent;
import org.red.library.event.area.player.AreaPlayerSwapHandItemsEvent;
import org.red.library.interactive.item.InteractiveItemAct;
import org.red.library.world.rule.Rule;

public class PlayerSwapHandItemsListener extends AbstractPlayerListener<PlayerSwapHandItemsEvent> {
    @Override
    @EventHandler
    public void onEvent(PlayerSwapHandItemsEvent event) {
        super.runAreaPlayerEvent(event);

        A_Player player = A_.getAPlayer(event.getPlayer());
        A_.canRunInteractiveItemEvent(player.getItemInHand(), InteractiveItemAct.SWAP_HAND.class, player, event);
        if (!player.getAWorld().getRuleValue(Rule.SWAP_HAND, player.getLocation())) event.setCancelled(true);
    }

    @Override
    protected Class<? extends AreaEvent<PlayerSwapHandItemsEvent>> getAreaEventClass() {
        return AreaPlayerSwapHandItemsEvent.class;
    }

    @Override
    protected Class<PlayerSwapHandItemsEvent> getEventClass() {
        return PlayerSwapHandItemsEvent.class;
    }
}
