package org.red.library.event.listener.player;

import org.bukkit.event.player.PlayerDropItemEvent;
import org.red.library.entity.player.APlayer;
import org.red.library.event.area.AreaEvent;
import org.red.library.event.area.player.AreaPlayerDropItemEvent;
import org.red.library.event.listener.AbstractListener;
import org.red.library.item.event.EventItemAnnotation;
import org.red.library.item.event.EventItemManager;
import org.red.library.world.WorldData;
import org.red.library.world.rule.Rule;

public class PlayerDropItemListener extends AbstractListener<PlayerDropItemEvent> {
    @Override
    public void onEvent(PlayerDropItemEvent event) {
        super.runAreaPlayerEvent(event);

        APlayer player = APlayer.getNewPlayer(event.getPlayer());
        EventItemManager.runItemEvent(player, player.getInventory().getItemInMainHand(), player.isSneaking() ?
                EventItemAnnotation.Act.SHIFT_DROP : EventItemAnnotation.Act.DROP, event);

        WorldData worldData = WorldData.getWorldData(player.getWorld());

        if (!worldData.getRuleValue(Rule.DROP, player.getLocation())) event.setCancelled(true);
    }

    @Override
    protected Class<? extends AreaEvent<PlayerDropItemEvent>> getAreaEventClass() {
        return AreaPlayerDropItemEvent.class;
    }
}
