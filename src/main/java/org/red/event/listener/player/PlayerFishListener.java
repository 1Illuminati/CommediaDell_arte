package org.red.event.listener.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerFishEvent;
import org.red.library.A_;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.event.area.AreaEvent;
import org.red.library.event.area.player.AreaPlayerFishEvent;
import org.red.event.listener.AbstractListener;
import org.red.library.interactive.item.InteractiveItemAct;
import org.red.library.world.rule.Rule;

public class PlayerFishListener extends AbstractListener<PlayerFishEvent> {
    @Override
    @EventHandler
    public void onEvent(PlayerFishEvent event) {
        super.runAreaPlayerEvent(event);

        A_Player player = A_.getAPlayer(event.getPlayer());
        A_.canRunInteractiveItemEvent(player.getItemInHand(), InteractiveItemAct.FISH.class, player, event);
        if (!player.getAWorld().getRuleValue(Rule.FISHING, player.getLocation())) event.setCancelled(true);
    }

    @Override
    protected Class<? extends AreaEvent<PlayerFishEvent>> getAreaEventClass() {
        return AreaPlayerFishEvent.class;
    }
}
