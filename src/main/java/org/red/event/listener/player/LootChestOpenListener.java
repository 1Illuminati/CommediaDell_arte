package org.red.event.listener.player;

import org.bukkit.event.EventHandler;
import org.red.event.listener.AbstractListener;
import org.red.library.event.LootChestOpenEvent;
import org.red.library.event.area.AreaEvent;
import org.red.library.event.area.player.AreaLootChestOpenEvent;

public class LootChestOpenListener extends AbstractPlayerListener<LootChestOpenEvent> {
    @Override
    @EventHandler
    public void onEvent(LootChestOpenEvent event) {
        super.runAreaPlayerEvent(event);
    }

    @Override
    protected Class<? extends AreaEvent<LootChestOpenEvent>> getAreaEventClass() {
        return AreaLootChestOpenEvent.class;
    }

    @Override
    protected Class<LootChestOpenEvent> getEventClass() {
        return LootChestOpenEvent.class;
    }
}
