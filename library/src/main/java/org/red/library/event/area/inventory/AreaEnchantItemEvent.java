package org.red.library.event.area.inventory;

import org.bukkit.event.HandlerList;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.red.library.world.Area;

public class AreaEnchantItemEvent extends AreaInventoryEvent<EnchantItemEvent> {
    public AreaEnchantItemEvent(Area area, EnchantItemEvent event) {
        super(area, event);
    }

    public static HandlerList getHandlerList() {
        return handler_map.computeIfAbsent(AreaEnchantItemEvent.class, k -> new HandlerList());
    }
}
