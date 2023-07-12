package org.red.library.event.listener.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.red.library.entity.player.NewPlayer;
import org.red.library.event.area.AreaEvent;
import org.red.library.event.area.player.AreaAsyncPlayerChatEvent;
import org.red.library.event.listener.AbstractListener;
import org.red.library.world.WorldData;
import org.red.library.world.rule.Rule;

public class AsyncPlayerChatListener extends AbstractListener<AsyncPlayerChatEvent> {
    @Override
    public void onEvent(AsyncPlayerChatEvent event) {
        super.runAreaPlayerEvent(event);

        NewPlayer player = NewPlayer.getNewPlayer(event.getPlayer());
        WorldData worldData = WorldData.getWorldData(player.getWorld());

        if (!worldData.getRuleValue(Rule.CHAT, player.getLocation())) event.setCancelled(true);
    }

    @Override
    protected Class<? extends AreaEvent<AsyncPlayerChatEvent>> getAreaEventClass() {
        return AreaAsyncPlayerChatEvent.class;
    }
}
