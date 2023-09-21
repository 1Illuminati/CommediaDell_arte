package org.red.event.listener.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.red.library.A_;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.event.area.AreaEvent;
import org.red.library.event.area.player.AreaPlayerCommandPreprocessEvent;
import org.red.event.listener.AbstractListener;
import org.red.library.world.rule.Rule;

public class PlayerCommandPreProcessListener extends AbstractListener<PlayerCommandPreprocessEvent> {
    @Override
    @EventHandler
    public void onEvent(PlayerCommandPreprocessEvent event) {
        super.runAreaPlayerEvent(event);

        A_Player player = A_.getAPlayer(event.getPlayer());
        if (!player.getAWorld().getRuleValue(Rule.COMMAND, player.getLocation())) event.setCancelled(true);
    }

    @Override
    protected Class<? extends AreaEvent<PlayerCommandPreprocessEvent>> getAreaEventClass() {
        return AreaPlayerCommandPreprocessEvent.class;
    }
}
