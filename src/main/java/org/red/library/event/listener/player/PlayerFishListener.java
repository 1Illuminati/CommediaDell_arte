package org.red.library.event.listener.player;

import org.bukkit.event.player.PlayerFishEvent;
import org.red.library.entity.a_.player.A_Player;
import org.red.library.event.area.AreaEvent;
import org.red.library.event.area.player.AreaPlayerFishEvent;
import org.red.library.event.listener.AbstractListener;
import org.red.library.item.event.EventItemAnnotation;
import org.red.library.item.event.EventItemManager;
import org.red.library.world.WorldData;
import org.red.library.world.rule.Rule;

public class PlayerFishListener extends AbstractListener<PlayerFishEvent> {
    @Override
    public void onEvent(PlayerFishEvent event) {
        super.runAreaPlayerEvent(event);

        A_Player player = A_Player.getAPlayer(event.getPlayer());
        EventItemManager.runItemEvent(player, player.getInventory().getItemInMainHand(), EventItemAnnotation.Act.FISHING, event);

        WorldData worldData = WorldData.getWorldData(player.getWorld());

        if (!worldData.getRuleValue(Rule.FISHING, player.getLocation())) event.setCancelled(true);
    }

    @Override
    protected Class<? extends AreaEvent<PlayerFishEvent>> getAreaEventClass() {
        return AreaPlayerFishEvent.class;
    }
}
