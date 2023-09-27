package org.red.event.listener.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.red.library.A_;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.event.area.AreaEvent;
import org.red.library.event.area.player.AreaPlayerSwapHandItemsEvent;
import org.red.event.listener.AbstractListener;
import org.red.library.interactive.item.InteractiveItemAnnotation;
import org.red.interactive.item.EventItemInfo;
import org.red.library.world.rule.Rule;

public class PlayerSwapHandItemsListener extends AbstractListener<PlayerSwapHandItemsEvent> {
    @Override
    @EventHandler
    public void onEvent(PlayerSwapHandItemsEvent event) {
        super.runAreaPlayerEvent(event);

        A_Player player = A_.getAPlayer(event.getPlayer());
        EventItemInfo.runItemEvent(player, player.getInventory().getItemInMainHand(), InteractiveItemAnnotation.Act.SWAP_HAND, event);
        if (!player.getAWorld().getRuleValue(Rule.SWAP_HAND, player.getLocation())) event.setCancelled(true);
    }

    @Override
    protected Class<? extends AreaEvent<PlayerSwapHandItemsEvent>> getAreaEventClass() {
        return AreaPlayerSwapHandItemsEvent.class;
    }
}
