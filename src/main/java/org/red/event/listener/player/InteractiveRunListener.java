package org.red.event.listener.player;

import org.bukkit.event.EventHandler;
import org.red.library.event.InteractiveRunEvent;
import org.red.library.event.area.AreaEvent;
import org.red.library.event.area.player.AreaInteractRunEvent;
import org.red.event.listener.AbstractListener;

public class InteractiveRunListener extends AbstractPlayerListener<InteractiveRunEvent> {
    @Override
    @EventHandler
    public void onEvent(InteractiveRunEvent event) {
        super.runAreaPlayerEvent(event);
    }

    @Override
    public Class<? extends AreaEvent<InteractiveRunEvent>> getAreaEventClass() {
        return AreaInteractRunEvent.class;
    }

    @Override
    protected Class<? extends InteractiveRunEvent> getEventClass() {
        return InteractiveRunEvent.class;
    }
}
