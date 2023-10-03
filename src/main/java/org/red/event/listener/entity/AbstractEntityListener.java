package org.red.event.listener.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityEvent;
import org.red.event.listener.AbstractListener;
import org.red.library.A_;
import org.red.library.a_.world.A_World;

public abstract class AbstractEntityListener<T extends EntityEvent> extends AbstractListener<T> {
    protected void runAreaEntityEvent(T event) {
        Entity entity = event.getEntity();
        A_World world = A_.getAWorld(entity.getWorld());
        runAreaEvent(event, world.getContainAreas(entity.getLocation()));
    }
}
