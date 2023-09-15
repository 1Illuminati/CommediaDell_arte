package org.red.event.listener.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.red.library.entity.a_.player.A_Player;
import org.red.library.event.area.AreaEvent;
import org.red.library.event.area.player.AreaAsyncPlayerChatEvent;
import org.red.event.listener.AbstractListener;
import org.red.library.world.WorldData;
import org.red.library.world.rule.Rule;

public class AsyncPlayerChatListener extends AbstractListener<AsyncPlayerChatEvent> {
    @Override
    @EventHandler
    public void onEvent(AsyncPlayerChatEvent event) {
        super.runAreaPlayerEvent(event);

        A_Player player = A_Player.getAPlayer(event.getPlayer());
        WorldData worldData = WorldData.getWorldData(player.getWorld());

        if (!worldData.getRuleValue(Rule.CHAT, player.getLocation())) event.setCancelled(true);
    }

    @Override
    protected Class<? extends AreaEvent<AsyncPlayerChatEvent>> getAreaEventClass() {
        return AreaAsyncPlayerChatEvent.class;
    }
}
