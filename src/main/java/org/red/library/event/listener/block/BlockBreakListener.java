package org.red.library.event.listener.block;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.red.library.entity.player.APlayer;
import org.red.library.event.area.AreaEvent;
import org.red.library.event.area.block.AreaBlockBreakEvent;
import org.red.library.event.listener.AbstractListener;
import org.red.library.item.event.EventItemAnnotation;
import org.red.library.item.event.EventItemManager;
import org.red.library.world.WorldData;
import org.red.library.world.rule.Rule;

public class BlockBreakListener extends AbstractListener<BlockBreakEvent> {
    @EventHandler
    public void onEvent(BlockBreakEvent event) {
        runAreaBlockEvent(event);

        APlayer player = APlayer.getNewPlayer(event.getPlayer());
        ItemStack mainHand = player.getInventory().getItemInMainHand();
        Block block = event.getBlock();
        EventItemManager.runItemEvent(player, mainHand, EventItemAnnotation.Act.BREAK, event);

        WorldData worldData = WorldData.getWorldData(player.getWorld());

        if (!worldData.getRuleValue(Rule.BREAK, block.getLocation())) event.setCancelled(true);
    }

    @Override
    protected Class<? extends AreaEvent<BlockBreakEvent>> getAreaEventClass() {
        return AreaBlockBreakEvent.class;
    }
}
