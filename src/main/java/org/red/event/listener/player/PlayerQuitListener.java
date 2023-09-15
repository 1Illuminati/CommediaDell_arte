package org.red.event.listener.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.event.area.AreaEvent;
import org.red.library.event.area.player.AreaPlayerQuitEvent;
import org.red.event.listener.AbstractListener;

public class PlayerQuitListener extends AbstractListener<PlayerQuitEvent> {
    @Override
    @EventHandler
    public void onEvent(PlayerQuitEvent event) {
        super.runAreaPlayerEvent(event);
        A_Player.getAPlayer(event.getPlayer()).aDataSave();
    }

    @Override
    protected Class<? extends AreaEvent<PlayerQuitEvent>> getAreaEventClass() {
        return AreaPlayerQuitEvent.class;
    }
}
