package org.red.event.listener.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.event.area.AreaEvent;
import org.red.library.event.area.player.AreaPlayerJoinEvent;
import org.red.event.listener.AbstractListener;

public class PlayerJoinListener extends AbstractListener<PlayerJoinEvent> {
    @Override
    @EventHandler
    public void onEvent(PlayerJoinEvent event) {
        super.runAreaPlayerEvent(event);
        A_Player.getAPlayer(event.getPlayer()).sendMessage("§c§l[ CommediaDell_arte ] §fWelcome to the server!");
    }

    @Override
    protected Class<? extends AreaEvent<PlayerJoinEvent>> getAreaEventClass() {
        return AreaPlayerJoinEvent.class;
    }
}
