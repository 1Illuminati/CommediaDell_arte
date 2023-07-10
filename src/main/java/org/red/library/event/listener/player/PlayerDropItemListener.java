package org.red.library.event.listener.player;

import org.bukkit.event.player.PlayerDropItemEvent;
import org.red.library.entity.player.NewPlayer;
import org.red.library.event.area.AreaEvent;
import org.red.library.event.area.player.AreaPlayerDropItemEvent;
import org.red.library.event.listener.AbstractListener;
import org.red.library.item.event.EventItemAnnotation;
import org.red.library.item.event.EventItemManager;

public class PlayerDropItemListener extends AbstractListener<PlayerDropItemEvent> {
    @Override
    public void onEvent(PlayerDropItemEvent event) {
        super.runAreaPlayerEvent(event);

        NewPlayer player = NewPlayer.getNewPlayer(event.getPlayer());
        EventItemManager.runItemEvent(player, player.getInventory().getItemInMainHand(), player.isSneaking() ?
                EventItemAnnotation.Act.SHIFT_DROP : EventItemAnnotation.Act.DROP, event);
    }

    @Override
    protected Class<? extends AreaEvent<PlayerDropItemEvent>> getAreaEventClass() {
        return AreaPlayerDropItemEvent.class;
    }
}
