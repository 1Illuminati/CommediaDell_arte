package org.red.a_.admin;

import org.bukkit.event.player.PlayerInteractEvent;
import org.red.library.A_;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.interactive.InteractiveAnnotation;
import org.red.library.interactive.item.InteractiveItem;
import org.red.library.interactive.item.InteractiveItemAct;
import org.red.library.util.map.DataMap;

public abstract class AdminSameAbility implements InteractiveItem {
    @InteractiveAnnotation(act = InteractiveItemAct.RIGHT_CLICK_BLOCK.class)
    public void rightClickBlock(PlayerInteractEvent event) {
        event.setCancelled(true);
        A_Player player = A_.getAPlayer(event.getPlayer());
        DataMap dataMap = player.getDataMap();

        dataMap.set("admin_second_location", event.getClickedBlock().getLocation());
        player.sendMessage("두번째 지점 설정 완료");
    }

    @InteractiveAnnotation(act = InteractiveItemAct.LEFT_CLICK_BLOCK.class)
    public void leftClickBlock(PlayerInteractEvent event) {
        event.setCancelled(true);
        A_Player player = A_.getAPlayer(event.getPlayer());
        DataMap dataMap = player.getDataMap();

        dataMap.set("admin_first_location", event.getClickedBlock().getLocation());
        player.sendMessage("첫번째 지점 설정 완료");
    }

    @InteractiveAnnotation(act = InteractiveItemAct.RIGHT_CLICK_BLOCK.class, shift = true)
    public void shiftRightClickBlock(PlayerInteractEvent event) {
        this.rightClickBlock(event);
    }

    @InteractiveAnnotation(act = InteractiveItemAct.LEFT_CLICK_BLOCK.class, shift = true)
    public void shiftLeftClickBlock(PlayerInteractEvent event) {
        this.leftClickBlock(event);
    }
}
