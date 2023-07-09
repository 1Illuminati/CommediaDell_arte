package org.red.library.event.listener.player;

import org.red.library.entity.player.NewPlayer;
import org.red.library.event.RunEventItemEvent;
import org.red.library.event.area.AreaEvent;
import org.red.library.event.area.player.AreaRunEventItemEvent;
import org.red.library.event.listener.AbstractListener;
import org.red.library.world.Area;
import org.red.library.world.WorldData;

public class RunEventItemListener extends AbstractListener<RunEventItemEvent> {
    @Override
    public void onEvent(RunEventItemEvent event) {
        NewPlayer player = event.getNewPlayer();
        WorldData worldData = WorldData.get(player.getWorld());

        for (Area area : worldData.getContainArea(player.getLocation())) {

        }
    }

    @Override
    public Class<? extends AreaEvent<RunEventItemEvent>> getAreaEventClass() {
        return AreaRunEventItemEvent.class;
    }
}
