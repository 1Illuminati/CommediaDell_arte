package org.red.event.listener.block;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.red.a_.entity.A_PlayerImpl;
import org.red.library.A_;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.a_.world.A_World;
import org.red.library.event.area.AreaEvent;
import org.red.library.event.area.block.AreaBlockBreakEvent;
import org.red.event.listener.AbstractListener;
import org.red.library.item.event.EventItemAnnotation;
import org.red.item.EventItemManager;
import org.red.library.world.rule.Rule;

import java.util.Arrays;

public class BlockBreakListener extends AbstractListener<BlockBreakEvent> {
    @Override
    @EventHandler
    public void onEvent(BlockBreakEvent event) {
        runAreaBlockEvent(event);

        A_Player player = A_.getAPlayer(event.getPlayer());
        ItemStack mainHand = player.getInventory().getItemInMainHand();
        Block block = event.getBlock();
        EventItemManager.runItemEvent(player, mainHand, EventItemAnnotation.Act.BREAK, event);
        A_World world = player.getAWorld();

        if (!world.getRuleValue(Rule.BREAK, block.getLocation(), player.getLocation())) event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void monitorEvent(BlockBreakEvent event) {
        A_PlayerImpl player = (A_PlayerImpl) A_.getAPlayer(event.getPlayer());
        player.setLastBreakBlock(event.getBlock().getState());
    }

    @Override
    protected Class<? extends AreaEvent<BlockBreakEvent>> getAreaEventClass() {
        return AreaBlockBreakEvent.class;
    }
}
