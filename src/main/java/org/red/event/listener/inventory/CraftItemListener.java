package org.red.event.listener.inventory;

import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.CraftItemEvent;
import org.red.event.listener.AbstractListener;
import org.red.library.A_;
import org.red.library.a_.world.A_World;
import org.red.library.event.area.AreaEvent;
import org.red.library.event.area.inventory.AreaCraftItemEvent;
import org.red.library.item.material.MaterialAct;

public class CraftItemListener extends AbstractListener<CraftItemEvent> {
    @Override
    @EventHandler
    public void onEvent(CraftItemEvent event) {
        super.runAreaInventoryEvent(event);

        A_World world = A_.getAWorld(event.getWhoClicked().getWorld());
        if (!world.isActAllowed(event.getRecipe().getResult().getType(), MaterialAct.CRAFT)) event.setCancelled(true);
    }

    @Override
    protected Class<? extends AreaEvent<CraftItemEvent>> getAreaEventClass() {
        return AreaCraftItemEvent.class;
    }
}
