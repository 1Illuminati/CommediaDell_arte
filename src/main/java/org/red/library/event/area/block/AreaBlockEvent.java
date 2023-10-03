package org.red.library.event.area.block;

import org.bukkit.block.Block;
import org.bukkit.event.block.BlockEvent;
import org.red.library.event.area.AreaEvent;
import org.red.library.world.Area;

public class AreaBlockEvent<T extends BlockEvent> extends AreaEvent<T> {
    private final Block block;
    public AreaBlockEvent(Area area, T event) {
        super(area, event);
        this.block = event.getBlock();
    }

    public Block getBlock() {
        return block;
    }
}
