package org.red.library.event.listener;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.player.PlayerEvent;
import org.red.library.event.area.AreaEvent;
import org.red.library.world.Area;
import org.red.library.world.WorldData;
import org.red.library.world.rule.Rule;

import java.util.List;

public abstract class AbstractListener<T extends Event> implements Listener {
    public abstract void onEvent(T event);

    protected abstract Class<? extends AreaEvent<T>> getAreaEventClass();

    protected <V> V getWorldRuleValue(Rule<V> rule, World world) {
        WorldData worldData = WorldData.getWorldData(world);
        return worldData.getRuleValue(rule);
    }
    protected void runAreaPlayerEvent(T event) {
        if (!(event instanceof PlayerEvent)) throw new IllegalArgumentException("Event must be PlayerEvent");

        PlayerEvent playerEvent = (PlayerEvent) event;
        Player player = playerEvent.getPlayer();
        WorldData worldData = WorldData.getWorldData(player.getWorld());
        runAreaEvent(event, worldData.getContainArea(player.getLocation()));
    }

    protected void runAreaEntityEvent(T event) {
        if (!(event instanceof EntityEvent)) throw new IllegalArgumentException("Event must be EntityEvent");

        EntityEvent entityEvent = (EntityEvent) event;
        Entity entity = entityEvent.getEntity();
        WorldData worldData = WorldData.getWorldData(entity.getWorld());
        runAreaEvent(event, worldData.getContainArea(entity.getLocation()));
    }

    protected void runAreaBlockEvent(T event) {
        if (!(event instanceof BlockEvent)) throw new IllegalArgumentException("Event must be BlockEvent");

        BlockEvent blockEvent = (BlockEvent) event;
        Block block = blockEvent.getBlock();
        WorldData worldData = WorldData.getWorldData(block.getWorld());
        runAreaEvent(event, worldData.getContainArea(block.getLocation()));
    }

    protected void runAreaEvent(T event, List<Area> areas) {
        for (Area area : areas) {
            AreaEvent<T> areaEvent;

            try {
                areaEvent = getAreaEventClass().getConstructor(Area.class, event.getClass()).newInstance(area, event);
                Bukkit.getPluginManager().callEvent(areaEvent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
