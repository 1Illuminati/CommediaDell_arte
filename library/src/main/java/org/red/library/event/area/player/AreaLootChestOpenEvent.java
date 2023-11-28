package org.red.library.event.area.player;

import org.bukkit.event.HandlerList;
import org.red.library.world.Area;
import org.red.library.event.LootChestOpenEvent;

public class AreaLootChestOpenEvent extends AreaPlayerEvent<LootChestOpenEvent> {
    public AreaLootChestOpenEvent(Area area, LootChestOpenEvent event) {
        super(area, event);
    }

    public static HandlerList getHandlerList() {
        return handler_map.computeIfAbsent(AreaLootChestOpenEvent.class, k -> new HandlerList());
    }
}
