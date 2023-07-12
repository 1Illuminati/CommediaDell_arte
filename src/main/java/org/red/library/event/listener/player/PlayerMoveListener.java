package org.red.library.event.listener.player;

import org.bukkit.event.player.PlayerMoveEvent;
import org.red.library.entity.player.NewPlayer;
import org.red.library.event.area.AreaEvent;
import org.red.library.event.area.player.AreaPlayerMoveEvent;
import org.red.library.event.listener.AbstractListener;
import org.red.library.world.WorldData;
import org.red.library.world.rule.Rule;

public class PlayerMoveListener extends AbstractListener<PlayerMoveEvent> {
    @Override
    public void onEvent(PlayerMoveEvent event) {
        super.runAreaPlayerEvent(event);

        NewPlayer player = NewPlayer.getNewPlayer(event.getPlayer());
        WorldData worldData = WorldData.getWorldData(player.getWorld());

        if (!worldData.getRuleValue(Rule.MOVE, player.getLocation())) event.setCancelled(true);
    }

    @Override
    protected Class<? extends AreaEvent<PlayerMoveEvent>> getAreaEventClass() {
        return AreaPlayerMoveEvent.class;
    }
}
