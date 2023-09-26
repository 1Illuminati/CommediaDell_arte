package org.red.event.listener.entity;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.red.library.A_;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.a_.world.A_World;
import org.red.library.event.area.AreaEvent;
import org.red.library.event.area.entity.AreaEntityDamageByEntityEvent;
import org.red.event.listener.AbstractListener;
import org.red.library.interactive.item.EventItemAnnotation;
import org.red.item.event.EventItemInfo;
import org.red.library.world.rule.Rule;

public class EntityDamageByEntityListener extends AbstractListener<EntityDamageByEntityEvent> {
    @Override
    @EventHandler
    public void onEvent(EntityDamageByEntityEvent event) {
        super.runAreaEntityEvent(event);

        Entity entity = event.getEntity();
        Entity damager = event.getDamager();

        if (entity instanceof Player) {
            A_Player player = A_.getAPlayer((Player) entity);
            EventItemInfo.runItemEvent(player, player.getInventory().getItemInMainHand(), EventItemAnnotation.Act.HIT, event);
            A_World world = A_.getAWorld(entity.getWorld());
            Location[] locs = {entity.getLocation(), damager.getLocation()};

            if (!world.getRuleValue(Rule.ATTACK, locs)) event.setCancelled(true);

            if (damager instanceof Player) {
                if (!world.getRuleValue(Rule.PVP, locs)) event.setCancelled(true);
            }
        }
    }

    @Override
    protected Class<? extends AreaEvent<EntityDamageByEntityEvent>> getAreaEventClass() {
        return AreaEntityDamageByEntityEvent.class;
    }
}
