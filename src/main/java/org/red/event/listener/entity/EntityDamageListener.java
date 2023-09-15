package org.red.event.listener.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.red.library.entity.a_.player.A_Player;
import org.red.library.event.area.AreaEvent;
import org.red.library.event.area.entity.AreaEntityDamageEvent;
import org.red.event.listener.AbstractListener;
import org.red.library.world.WorldData;
import org.red.library.world.rule.Rule;

public class EntityDamageListener extends AbstractListener<EntityDamageEvent> {
    @Override
    @EventHandler
    public void onEvent(EntityDamageEvent event) {
        super.runAreaEntityEvent(event);

        Entity entity  = event.getEntity();
        EntityDamageEvent.DamageCause cause = event.getCause();
        WorldData worldData = WorldData.getWorldData(entity.getWorld());

        if (entity instanceof Player) {
            A_Player player = A_Player.getAPlayer((Player) entity);

            if (cause != EntityDamageEvent.DamageCause.FALL) return;

            if (!worldData.getRuleValue(Rule.FALL_DAMAGE)) event.setCancelled(true);
        }
    }

    @Override
    protected Class<? extends AreaEvent<EntityDamageEvent>> getAreaEventClass() {
        return AreaEntityDamageEvent.class;
    }
}
