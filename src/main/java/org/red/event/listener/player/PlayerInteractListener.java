package org.red.event.listener.player;

import org.bukkit.block.Block;
import org.bukkit.block.TileState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.red.CommediaDell_arte;
import org.red.library.A_;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.event.area.AreaEvent;
import org.red.library.event.area.player.AreaPlayerInteractEvent;
import org.red.event.listener.AbstractListener;
import org.red.library.interactive.block.InteractiveTileAct;
import org.red.library.interactive.item.InteractiveItemAct;

public class PlayerInteractListener extends AbstractListener<PlayerInteractEvent> {
    @Override
    @EventHandler
    public void onEvent(PlayerInteractEvent event) {
        super.runAreaPlayerEvent(event);

        if (event.getHand() == EquipmentSlot.OFF_HAND) return;
        A_Player player = A_.getAPlayer(event.getPlayer());
        Action action = event.getAction();
        Class<? extends InteractiveItemAct> itemActClass = null;
        Class<? extends InteractiveTileAct> tileActClass = null;
        switch (action) {
            case RIGHT_CLICK_AIR:
                itemActClass = InteractiveItemAct.RIGHT_CLICK_AIR.class;
            break;
            case RIGHT_CLICK_BLOCK:
                itemActClass = InteractiveItemAct.RIGHT_CLICK_BLOCK.class;
                tileActClass = InteractiveTileAct.RIGHT_CLICK_BLOCK.class;
            break;
            case LEFT_CLICK_AIR:
                itemActClass = InteractiveItemAct.LEFT_CLICK_AIR.class;
            break;
            case LEFT_CLICK_BLOCK:
                itemActClass = InteractiveItemAct.LEFT_CLICK_BLOCK.class;
                tileActClass = InteractiveTileAct.LEFT_CLICK_BLOCK.class;
            break;
            case PHYSICAL:
                itemActClass = InteractiveItemAct.PHYSICAL.class;
            break;
        }

        A_.canRunInteractiveItemEvent(player.getItemInHand(), itemActClass, player, event);

        Block clickedBlock = event.getClickedBlock();
        if (tileActClass != null && clickedBlock != null){
            A_.canRunInteractiveTileEvent(clickedBlock.getState(), tileActClass, player, event);
        }

    }

    @Override
    protected Class<? extends AreaEvent<PlayerInteractEvent>> getAreaEventClass() {
        return AreaPlayerInteractEvent.class;
    }
}
