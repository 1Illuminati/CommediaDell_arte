package org.red.library.event.area;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.red.library.world.Area;

import java.util.HashMap;
import java.util.Map;

public abstract class AreaEvent<T extends Event> extends Event {
    private static final Map<Class<? extends Event>, HandlerList> HANDLERS_MAP = new HashMap<>();
    private final Area area;
    private final T event;
    protected AreaEvent(Area area, T event) {
        this.area = area;
        this.event = event;
    }

    public T getEvent() {
        return event;
    }

    public Area getArea() {
        return area;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS_MAP.computeIfAbsent(event.getClass(), c -> new HandlerList());
    }
}
