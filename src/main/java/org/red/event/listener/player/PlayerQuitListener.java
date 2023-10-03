package org.red.event.listener.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;
import org.red.library.A_;
import org.red.library.event.area.AreaEvent;
import org.red.library.event.area.player.AreaPlayerQuitEvent;
import org.red.event.listener.AbstractListener;

public class PlayerQuitListener extends AbstractPlayerListener<PlayerQuitEvent> {
    @Override
    @EventHandler
    public void onEvent(PlayerQuitEvent event) {
        super.runAreaPlayerEvent(event);
        A_.getAPlayer(event.getPlayer()).aDataSave();
    }

    @Override
    protected Class<? extends AreaEvent<PlayerQuitEvent>> getAreaEventClass() {
        return AreaPlayerQuitEvent.class;
    }

    @Override
    protected Class<? extends PlayerQuitEvent> getEventClass() {
        return PlayerQuitEvent.class;
    }
}
