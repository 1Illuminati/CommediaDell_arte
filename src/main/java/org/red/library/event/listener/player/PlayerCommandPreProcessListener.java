package org.red.library.event.listener.player;

import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.red.library.entity.player.NewPlayer;
import org.red.library.event.area.AreaEvent;
import org.red.library.event.area.player.AreaPlayerCommandPreprocessEvent;
import org.red.library.event.listener.AbstractListener;
import org.red.library.world.WorldData;
import org.red.library.world.rule.Rule;

public class PlayerCommandPreProcessListener extends AbstractListener<PlayerCommandPreprocessEvent> {
    @Override
    public void onEvent(PlayerCommandPreprocessEvent event) {
        super.runAreaPlayerEvent(event);

        NewPlayer player = NewPlayer.getNewPlayer(event.getPlayer());
        WorldData worldData = WorldData.getWorldData(player.getWorld());

        if (!worldData.getRuleValue(Rule.COMMAND)) event.setCancelled(true);
    }

    @Override
    protected Class<? extends AreaEvent<PlayerCommandPreprocessEvent>> getAreaEventClass() {
        return AreaPlayerCommandPreprocessEvent.class;
    }
}
