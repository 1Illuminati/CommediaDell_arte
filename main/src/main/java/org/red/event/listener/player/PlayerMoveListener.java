package org.red.event.listener.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.red.library.A_;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.event.area.AreaEvent;
import org.red.library.event.area.player.AreaPlayerMoveEvent;
import org.red.library.world.rule.Rule;

public class PlayerMoveListener extends AbstractPlayerListener<PlayerMoveEvent> {
    @Override
    @EventHandler
    public void onEvent(PlayerMoveEvent event) {
        super.runAreaPlayerEvent(event);

        A_Player player = A_.getAPlayer(event.getPlayer());
        if (!player.getAWorld().getRuleValue(Rule.MOVE, player.getLocation())) event.setCancelled(true);
    }

    @Override
    protected Class<? extends AreaEvent<PlayerMoveEvent>> getAreaEventClass() {
        return AreaPlayerMoveEvent.class;
    }

    @Override
    protected Class<PlayerMoveEvent> getEventClass() {
        return PlayerMoveEvent.class;
    }
}
