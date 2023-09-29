package org.red.event.listener.entity;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.red.event.listener.AbstractListener;
import org.red.library.A_;
import org.red.library.a_.world.A_World;
import org.red.library.event.area.AreaEvent;
import org.red.library.event.area.entity.AreaCreatureSpawnEvent;
import org.red.library.world.rule.Rule;

public class CreatureSpawnListener extends AbstractListener<CreatureSpawnEvent> {
    @Override
    public void onEvent(CreatureSpawnEvent event) {
        super.runAreaEntityEvent(event);
        if (event.getEntityType() == EntityType.PLAYER) return;
        Location location = event.getLocation();
        A_World world = A_.getAWorld(location.getWorld());
        if (!world.getRuleValue(Rule.SPAWN_MOB, location)) event.setCancelled(true);
    }

    @Override
    protected Class<? extends AreaEvent<CreatureSpawnEvent>> getAreaEventClass() {
        return AreaCreatureSpawnEvent.class;
    }
}
