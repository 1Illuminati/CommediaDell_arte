package org.red.event.listener.entity;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.red.library.A_;
import org.red.library.a_.world.A_World;
import org.red.library.event.area.AreaEvent;
import org.red.library.event.area.entity.AreaEntitySpawnEvent;
import org.red.library.world.rule.Rule;

public class EntitySpawnListener extends AbstractEntityListener<EntitySpawnEvent> {
    @Override
    @EventHandler
    public void onEvent(EntitySpawnEvent event) {
        super.runAreaEntityEvent(event);
        if (event.getEntityType() == EntityType.PLAYER) return;
        Location location = event.getLocation();
        A_World world = A_.getAWorld(location.getWorld());
        if (!world.getRuleValue(Rule.SPAWN_ENTITY, location)) event.setCancelled(true);
    }

    @Override
    protected Class<? extends AreaEvent<EntitySpawnEvent>> getAreaEventClass() {
        return AreaEntitySpawnEvent.class;
    }

    @Override
    protected Class<EntitySpawnEvent> getEventClass() {
        return EntitySpawnEvent.class;
    }
}
