package org.red.library.event.area.player;

import org.bukkit.Location;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerMoveEvent;
import org.red.library.world.Area;

public class AreaPlayerMoveEvent extends AreaPlayerEvent<PlayerMoveEvent> {
    private final boolean inArea;
    private final boolean outArea;
    public AreaPlayerMoveEvent(Area area, PlayerMoveEvent event) {
        super(area, event);
        Location to = event.getTo();
        Location from = event.getFrom();
        boolean fromCheck = area.contain(from);
        boolean toCheck = area.contain(to);
        this.inArea = !fromCheck && toCheck;
        this.outArea = fromCheck && !toCheck;
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
