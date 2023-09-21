package org.red.event.listener.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.red.library.A_;
import org.red.library.a_.world.A_World;
import org.red.library.event.area.AreaEvent;
import org.red.library.event.area.entity.AreaEntityDamageEvent;
import org.red.event.listener.AbstractListener;
import org.red.library.world.rule.Rule;

public class EntityDamageListener extends AbstractListener<EntityDamageEvent> {
    @Override
    @EventHandler
    public void onEvent(EntityDamageEvent event) {
        super.runAreaEntityEvent(event);

        Entity entity  = event.getEntity();
        EntityDamageEvent.DamageCause cause = event.getCause();
        A_World world = A_.getAWorld(entity.getWorld());

        if (entity instanceof Player) {
            if (cause != EntityDamageEvent.DamageCause.FALL) return;

            if (!world.getRuleValue(Rule.FALL_DAMAGE)) event.setCancelled(true);
        }
    }

    @Override
    protected Class<? extends AreaEvent<EntityDamageEvent>> getAreaEventClass() {
        return AreaEntityDamageEvent.class;
    }
}
