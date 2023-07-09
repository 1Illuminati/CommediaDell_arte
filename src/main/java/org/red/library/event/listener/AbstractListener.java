package org.red.library.event.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.red.library.event.area.AreaEvent;
import org.red.library.world.Area;

import java.util.List;

public abstract class AbstractListener<T extends Event> implements Listener {
    public abstract void onEvent(T event);

    public abstract Class<? extends AreaEvent<T>> getAreaEventClass();

    public void runAreaEvent(T event, List<Area> areas) {
        for (Area area : areas) {
            AreaEvent<T> areaEvent;

            try {
                areaEvent = getAreaEventClass().getConstructor(Area.class, event.getClass()).newInstance(area, event);
                Bukkit.getPluginManager().callEvent(areaEvent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
