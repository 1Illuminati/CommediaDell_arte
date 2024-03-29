package org.red.event.listener.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.red.a_.entity.A_PlayerImpl;
import org.red.library.A_;
import org.red.library.event.area.AreaEvent;
import org.red.library.event.area.player.AreaPlayerCommandPreprocessEvent;
import org.red.library.world.rule.Rule;

public class PlayerCommandPreProcessListener extends AbstractPlayerListener<PlayerCommandPreprocessEvent> {
    @Override
    @EventHandler
    public void onEvent(PlayerCommandPreprocessEvent event) {
        super.runAreaPlayerEvent(event);

        A_PlayerImpl player = (A_PlayerImpl) A_.getAPlayer(event.getPlayer());

        if (player.getAAdmin() != null) return;
        if (!player.getAWorld().getRuleValue(Rule.COMMAND, player.getLocation())) event.setCancelled(true);
    }

    @Override
    protected Class<? extends AreaEvent<PlayerCommandPreprocessEvent>> getAreaEventClass() {
        return AreaPlayerCommandPreprocessEvent.class;
    }

    @Override
    protected Class<PlayerCommandPreprocessEvent> getEventClass() {
        return PlayerCommandPreprocessEvent.class;
    }
}
