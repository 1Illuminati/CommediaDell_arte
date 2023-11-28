package org.red.event.listener.inventory;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryEvent;
import org.red.event.listener.AbstractListener;
import org.red.library.A_;
import org.red.library.a_.world.A_World;

public abstract class AbstractInventoryListener<T extends InventoryEvent> extends AbstractListener<T> {
    protected void runAreaInventoryEvent(T event) {
        HumanEntity humanEntity = event.getView().getPlayer();
        A_World world = A_.getAWorld(humanEntity.getWorld());
        runAreaEvent(event, world.getContainAreas(humanEntity.getLocation()));
    }
}
