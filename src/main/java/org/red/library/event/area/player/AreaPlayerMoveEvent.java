package org.red.library.event.area.player;

import org.bukkit.Location;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerMoveEvent;
import org.red.library.event.area.AreaEvent;
import org.red.library.world.Area;

public class AreaPlayerMoveEvent extends AreaEvent<PlayerMoveEvent> {
    private final boolean inArea;
    private final boolean outArea;
    public AreaPlayerMoveEvent(Area area, PlayerMoveEvent event) {
        super(area, event);
        Location to = event.getTo();
        Location from = event.getFrom();
        boolean formCheck = area.contain(from);
        boolean toCheck = to == null || area.contain(to);
        this.inArea = !formCheck && toCheck;
        this.outArea = formCheck && !toCheck;
    }

    public boolean isInArea() {
        return inArea;
    }

    public boolean isOutArea() {
        return outArea;
    }

    public static HandlerList getHandlerList() {
        return handler_map.computeIfAbsent(AreaPlayerMoveEvent.class, k -> new HandlerList());
    }
}
