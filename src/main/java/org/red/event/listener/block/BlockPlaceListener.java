package org.red.event.listener.block;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockPlaceEvent;
import org.red.a_.entity.A_PlayerImpl;
import org.red.library.A_;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.a_.world.A_World;
import org.red.library.event.area.AreaEvent;
import org.red.library.event.area.block.AreaBlockPlaceEvent;
import org.red.event.listener.AbstractListener;
import org.red.library.item.material.MaterialAct;
import org.red.library.world.rule.Rule;

public class BlockPlaceListener extends AbstractListener<BlockPlaceEvent> {
    @Override
    @EventHandler
    public void onEvent(BlockPlaceEvent event) {
        super.runAreaBlockEvent(event);

        A_Player player = A_.getAPlayer(event.getPlayer());
        A_World world = player.getAWorld();
        Block block = event.getBlock();

        if (!world.isActAllowed(block.getType(), MaterialAct.PLACE)) event.setCancelled(true);
        if (!world.getRuleValue(Rule.PLACE, player.getLocation(), block.getLocation())) event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void monitorEvent(BlockPlaceEvent event) {
        A_PlayerImpl player = (A_PlayerImpl) A_.getAPlayer(event.getPlayer());
        player.setLastBreakBlock(event.getBlock().getState());
    }

    @Override
    protected Class<? extends AreaEvent<BlockPlaceEvent>> getAreaEventClass() {
        return AreaBlockPlaceEvent.class;
    }
}
