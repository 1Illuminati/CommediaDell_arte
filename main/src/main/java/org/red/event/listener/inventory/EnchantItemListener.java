package org.red.event.listener.inventory;

import org.bukkit.event.EventHandler;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.red.library.A_;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.event.area.AreaEvent;
import org.red.library.event.area.inventory.AreaEnchantItemEvent;
import org.red.library.world.rule.Rule;

public class EnchantItemListener extends AbstractInventoryListener<EnchantItemEvent> {
    @Override
    @EventHandler
    public void onEvent(EnchantItemEvent event) {
        super.runAreaInventoryEvent(event);
        A_Player player = A_.getAPlayer(event.getEnchanter());
        if (!player.getAWorld().getRuleValue(Rule.ENCHANT, player.getLocation())) event.setCancelled(true);
    }

    @Override
    protected Class<? extends AreaEvent<EnchantItemEvent>> getAreaEventClass() {
        return AreaEnchantItemEvent.class;
    }

    @Override
    protected Class<EnchantItemEvent> getEventClass() {
        return EnchantItemEvent.class;
    }
}
