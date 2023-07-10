package org.red.library.event.listener.entity;

import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.red.library.event.area.AreaEvent;
import org.red.library.event.listener.AbstractListener;

public class EntityDamageByEntityListener extends AbstractListener<EntityDamageByEntityEvent> {
    @Override
    public void onEvent(EntityDamageByEntityEvent event) {

    }

    @Override
    protected Class<? extends AreaEvent<EntityDamageByEntityEvent>> getAreaEventClass() {
        return null;
    }
}
