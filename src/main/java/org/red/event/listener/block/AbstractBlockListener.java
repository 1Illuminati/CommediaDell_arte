package org.red.event.listener.block;

import org.bukkit.block.Block;
import org.bukkit.event.block.BlockEvent;
import org.red.event.listener.AbstractListener;
import org.red.library.A_;
import org.red.library.a_.world.A_World;

public abstract class AbstractBlockListener<T extends BlockEvent> extends AbstractListener<T> {
    protected void runAreaBlockEvent(T event) {
        Block block = event.getBlock();
        A_World world = A_.getAWorld(block.getWorld());
        runAreaEvent(event, world.getContainAreas(block.getLocation()));
    }
}
