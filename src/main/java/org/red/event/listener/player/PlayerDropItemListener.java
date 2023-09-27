package org.red.event.listener.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.red.library.A_;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.event.area.AreaEvent;
import org.red.library.event.area.player.AreaPlayerDropItemEvent;
import org.red.event.listener.AbstractListener;
import org.red.library.interactive.item.InteractiveItemAct;
import org.red.library.world.rule.Rule;

public class PlayerDropItemListener extends AbstractListener<PlayerDropItemEvent> {
    @Override
    @EventHandler
    public void onEvent(PlayerDropItemEvent event) {
        super.runAreaPlayerEvent(event);

        A_Player player = A_.getAPlayer(event.getPlayer());
        A_.canRunInteractiveItemEvent(event.getItemDrop().getItemStack(), InteractiveItemAct.DROP.class, player, event);
        if (!player.getAWorld().getRuleValue(Rule.DROP, player.getLocation())) event.setCancelled(true);
    }

    @Override
    protected Class<? extends AreaEvent<PlayerDropItemEvent>> getAreaEventClass() {
        return AreaPlayerDropItemEvent.class;
    }
}
