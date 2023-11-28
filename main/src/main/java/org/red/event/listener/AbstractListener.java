package org.red.event.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.red.library.event.area.AreaEvent;
import org.red.library.world.Area;

import java.util.Set;

public abstract class AbstractListener<T extends Event> implements Listener {
    @EventHandler
    public abstract void onEvent(T event);

    protected abstract Class<? extends AreaEvent<T>> getAreaEventClass();

    protected abstract Class<T> getEventClass();

    protected void runAreaEvent(T event, Set<Area> areas) {
        for (Area area : areas) {
            AreaEvent<T> areaEvent;

            try {
                areaEvent = getAreaEventClass().getConstructor(Area.class, getEventClass()).newInstance(area, event);
                if (!area.inAreaEvent(areaEvent)) Bukkit.getPluginManager().callEvent(areaEvent);
            } catch (Exception e) {
                //e.printStackTrace();
            }
        }
    }
}
