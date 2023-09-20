package org.red.event.listener.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import org.red.CommediaDell_arte;
import org.red.a_.A_Manager;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.event.area.AreaEvent;
import org.red.library.event.area.player.AreaPlayerJoinEvent;
import org.red.event.listener.AbstractListener;

public class PlayerJoinListener extends AbstractListener<PlayerJoinEvent> {
    @Override
    @EventHandler(priority = EventPriority.LOWEST)
    public void onEvent(PlayerJoinEvent event) {
        A_Manager.INSTANCE.deleteOldAPlayer(event.getPlayer());
        super.runAreaPlayerEvent(event);
        CommediaDell_arte.sendDebugLog("PlayerJoinEvent: " + event.getPlayer().getName() + " joined the server.");
        A_Player.getAPlayer(event.getPlayer()).sendMessage("§c§l[ CommediaDell_arte ] §fWelcome to the server!");
    }

    @Override
    protected Class<? extends AreaEvent<PlayerJoinEvent>> getAreaEventClass() {
        return AreaPlayerJoinEvent.class;
    }
}
