package org.red.event.listener.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import org.red.a_.A_ManagerImpl;
import org.red.library.A_;
import org.red.library.event.area.AreaEvent;
import org.red.library.event.area.player.AreaPlayerJoinEvent;

public class PlayerJoinListener extends AbstractPlayerListener<PlayerJoinEvent> {
    @Override
    @EventHandler(priority = EventPriority.LOWEST)
    public void onEvent(PlayerJoinEvent event) {
        A_.removeAEntity(event.getPlayer());
        super.runAreaPlayerEvent(event);
    }

    @Override
    protected Class<? extends AreaEvent<PlayerJoinEvent>> getAreaEventClass() {
        return AreaPlayerJoinEvent.class;
    }

    @Override
    protected Class<? extends PlayerJoinEvent> getEventClass() {
        return PlayerJoinEvent.class;
    }
}
