package org.red.event.listener.entity;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.red.event.listener.AbstractListener;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.event.area.AreaEvent;
import org.red.library.event.area.entity.AreaEntityMountEvent;
import org.red.library.item.event.EventItemAnnotation;
import org.red.library.item.event.EventItemManager;
import org.red.library.world.WorldData;
import org.red.library.world.rule.Rule;
import org.spigotmc.event.entity.EntityMountEvent;

public class EntityMountListener extends AbstractListener<EntityMountEvent> {
    @Override
    public void onEvent(EntityMountEvent event) {
        super.runAreaEntityEvent(event);

        if (!(event.getMount() instanceof Player)) return;

        A_Player player = A_Player.getAPlayer((Player) event.getMount());
        WorldData worldData = WorldData.getWorldData(player.getWorld());

        if (!worldData.getRuleValue(Rule.RIDING, player.getLocation())) event.setCancelled(true);
    }

    @Override
    protected Class<? extends AreaEvent<EntityMountEvent>> getAreaEventClass() {
        return AreaEntityMountEvent.class;
    }
}
