package org.red.library.event.listener.entity;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.red.library.entity.player.NewPlayer;
import org.red.library.event.area.AreaEvent;
import org.red.library.event.area.entity.AreaEntityDamageByEntityEvent;
import org.red.library.event.listener.AbstractListener;
import org.red.library.item.event.EventItemAnnotation;
import org.red.library.item.event.EventItemManager;
import org.red.library.world.WorldData;
import org.red.library.world.rule.Rule;

import java.util.Arrays;
import java.util.List;

public class EntityDamageByEntityListener extends AbstractListener<EntityDamageByEntityEvent> {
    @Override
    public void onEvent(EntityDamageByEntityEvent event) {
        super.runAreaEntityEvent(event);

        Entity entity = event.getEntity();
        Entity damager = event.getDamager();

        if (entity instanceof Player) {
            NewPlayer player = NewPlayer.getNewPlayer((Player) entity);
            EventItemManager.runItemEvent(player, player.getInventory().getItemInMainHand(), EventItemAnnotation.Act.HIT, event);
            WorldData worldData = WorldData.getWorldData(player.getWorld());
            List<Location> locs = Arrays.asList(entity.getLocation(), damager.getLocation());

            if (!worldData.getRuleValue(Rule.ATTACK, locs)) event.setCancelled(true);

            if (damager instanceof Player) {
                if (!worldData.getRuleValue(Rule.PVP, locs)) event.setCancelled(true);
            }
        }
    }

    @Override
    protected Class<? extends AreaEvent<EntityDamageByEntityEvent>> getAreaEventClass() {
        return AreaEntityDamageByEntityEvent.class;
    }
}
