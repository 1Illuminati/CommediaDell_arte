package org.red.library.event.listener.player;

import org.bukkit.event.player.PlayerJoinEvent;
import org.red.library.event.area.AreaEvent;
import org.red.library.event.area.player.AreaPlayerJoinEvent;
import org.red.library.event.listener.AbstractListener;

public class PlayerJoinListener extends AbstractListener<PlayerJoinEvent> {
    @Override
    public void onEvent(PlayerJoinEvent event) {
        super.runAreaPlayerEvent(event);
    }

    @Override
    protected Class<? extends AreaEvent<PlayerJoinEvent>> getAreaEventClass() {
        return AreaPlayerJoinEvent.class;
    }
}
