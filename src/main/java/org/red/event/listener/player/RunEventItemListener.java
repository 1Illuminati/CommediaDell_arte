package org.red.event.listener.player;

import org.bukkit.event.EventHandler;
import org.red.library.event.InteractiveRunEvent;
import org.red.library.event.area.AreaEvent;
import org.red.library.event.area.player.AreaRunEventItemEvent;
import org.red.event.listener.AbstractListener;

public class RunEventItemListener extends AbstractListener<InteractiveRunEvent> {
    @Override
    @EventHandler
    public void onEvent(InteractiveRunEvent event) {
        super.runAreaPlayerEvent(event);
    }

    @Override
    public Class<? extends AreaEvent<InteractiveRunEvent>> getAreaEventClass() {
        return AreaRunEventItemEvent.class;
    }
}
