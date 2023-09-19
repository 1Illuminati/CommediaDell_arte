package org.red.event.listener.player;

import org.bukkit.event.EventHandler;
import org.red.library.event.RunEventItemEvent;
import org.red.library.event.area.AreaEvent;
import org.red.library.event.area.player.AreaRunEventItemEvent;
import org.red.event.listener.AbstractListener;

public class RunEventItemListener extends AbstractListener<RunEventItemEvent> {
    @Override
    @EventHandler
    public void onEvent(RunEventItemEvent event) {
        super.runAreaPlayerEvent(event);
    }

    @Override
    public Class<? extends AreaEvent<RunEventItemEvent>> getAreaEventClass() {
        return AreaRunEventItemEvent.class;
    }
}