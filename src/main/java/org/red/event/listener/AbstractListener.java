package org.red.event.listener;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.event.player.PlayerEvent;
import org.red.library.A_;
import org.red.library.a_.world.A_World;
import org.red.library.event.area.AreaEvent;
import org.red.library.world.Area;

import java.util.Set;

public abstract class AbstractListener<T extends Event> implements Listener {
    @EventHandler
    public abstract void onEvent(T event);

    protected abstract Class<? extends AreaEvent<T>> getAreaEventClass();

    protected void runAreaPlayerEvent(T event) {
        if (!(event instanceof PlayerEvent)) throw new IllegalArgumentException("Event must be PlayerEvent");

        PlayerEvent playerEvent = (PlayerEvent) event;
        Player player = playerEvent.getPlayer();
        A_World world = A_.getAWorld(player.getWorld());
        runAreaEvent(event, world.getContainAreas(player.getLocation()));
    }

    protected void runAreaEntityEvent(T event) {
        if (!(event instanceof EntityEvent)) throw new IllegalArgumentException("Event must be EntityEvent");

        EntityEvent entityEvent = (EntityEvent) event;
        Entity entity = entityEvent.getEntity();
        A_World world = A_.getAWorld(entity.getWorld());
        runAreaEvent(event, world.getContainAreas(entity.getLocation()));
    }

    protected void runAreaBlockEvent(T event) {
        if (!(event instanceof BlockEvent)) throw new IllegalArgumentException("Event must be BlockEvent");

        BlockEvent blockEvent = (BlockEvent) event;
        Block block = blockEvent.getBlock();
        A_World world = A_.getAWorld(block.getWorld());
        runAreaEvent(event, world.getContainAreas(block.getLocation()));
    }

    protected void runAreaInventoryEvent(T event) {
        if (!(event instanceof InventoryEvent)) throw new IllegalArgumentException("Event must be BlockEvent");

        InventoryEvent inventoryEvent = (InventoryEvent) event;
        HumanEntity humanEntity = inventoryEvent.getView().getPlayer();
        A_World world = A_.getAWorld(humanEntity.getWorld());
        runAreaEvent(event, world.getContainAreas(humanEntity.getLocation()));
    }

    protected void runAreaEvent(T event, Set<Area> areas) {
        for (Area area : areas) {
            AreaEvent<T> areaEvent;

            try {
                areaEvent = getAreaEventClass().getConstructor(Area.class, event.getClass()).newInstance(area, event);
                if (!area.inAreaEvent(areaEvent)) Bukkit.getPluginManager().callEvent(areaEvent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
