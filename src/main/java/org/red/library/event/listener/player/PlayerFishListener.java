package org.red.library.event.listener.player;

import org.bukkit.event.player.PlayerFishEvent;
import org.red.library.entity.player.NewPlayer;
import org.red.library.event.area.AreaEvent;
import org.red.library.event.area.player.AreaPlayerFishEvent;
import org.red.library.event.listener.AbstractListener;
import org.red.library.item.event.EventItemAnnotation;
import org.red.library.item.event.EventItemManager;

public class PlayerFishListener extends AbstractListener<PlayerFishEvent> {
    @Override
    public void onEvent(PlayerFishEvent event) {
        super.runAreaPlayerEvent(event);

        NewPlayer player = NewPlayer.getNewPlayer(event.getPlayer());
        EventItemManager.runItemEvent(player, player.getInventory().getItemInMainHand(), EventItemAnnotation.Act.FISHING, event);
    }

    @Override
    protected Class<? extends AreaEvent<PlayerFishEvent>> getAreaEventClass() {
        return AreaPlayerFishEvent.class;
    }
}
