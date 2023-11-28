package org.red.library.event.area.block;

import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockPlaceEvent;
import org.red.library.world.Area;

public class AreaBlockPlaceEvent extends AreaBlockEvent<BlockPlaceEvent> {
    public AreaBlockPlaceEvent(Area area, BlockPlaceEvent event) {
        super(area, event);
    }

    public static HandlerList getHandlerList() {
        return handler_map.computeIfAbsent(AreaBlockPlaceEvent.class, k -> new HandlerList());
    }
}
