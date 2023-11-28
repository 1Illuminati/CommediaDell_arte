package org.red.library.event.area;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.red.library.world.Area;

import java.util.HashMap;
import java.util.Map;

public abstract class AreaEvent<T extends Event> extends Event {

    protected static final Map<Class<? extends AreaEvent>, HandlerList> handler_map = new HashMap<>();
    private final Area area;
    private final T event;
    public AreaEvent(Area area, T event) {
        this.area = area;
        this.event = event;
    }

    @Override
    public HandlerList getHandlers() {
        return handler_map.computeIfAbsent(this.getClass(), k -> new HandlerList());
    }

    public T getEvent() {
        return event;
    }

    public Area getArea() {
        return area;
    }
}
