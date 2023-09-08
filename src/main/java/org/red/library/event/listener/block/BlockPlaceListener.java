package org.red.library.event.listener.block;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockPlaceEvent;
import org.red.library.entity.a_.impl.A_PlayerImpl;
import org.red.library.entity.a_.player.A_Player;
import org.red.library.event.area.AreaEvent;
import org.red.library.event.area.block.AreaBlockPlaceEvent;
import org.red.library.event.listener.AbstractListener;
import org.red.library.world.WorldData;
import org.red.library.world.rule.Rule;

public class BlockPlaceListener extends AbstractListener<BlockPlaceEvent> {
    @Override
    @EventHandler
    public void onEvent(BlockPlaceEvent event) {
        super.runAreaBlockEvent(event);

        A_Player player = A_Player.getAPlayer(event.getPlayer());
        Block block = event.getBlock();
        WorldData worldData = WorldData.getWorldData(player.getWorld());

        if (!worldData.getRuleValue(Rule.PLACE, block.getLocation())) event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void monitorEvent(BlockPlaceEvent event) {
        A_PlayerImpl player = (A_PlayerImpl) A_Player.getAPlayer(event.getPlayer());
        player.setLastBreakBlock(event.getBlock().getState());
    }

    @Override
    protected Class<? extends AreaEvent<BlockPlaceEvent>> getAreaEventClass() {
        return AreaBlockPlaceEvent.class;
    }
}
