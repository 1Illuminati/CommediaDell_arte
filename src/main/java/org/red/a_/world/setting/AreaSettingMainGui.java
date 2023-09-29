package org.red.a_.world.setting;

import org.bukkit.Material;
import org.red.library.inventory.CustomGui;
import org.red.library.item.ItemBuilder;
import org.red.library.world.Area;

public class AreaSettingMainGui extends CustomGui {
    public AreaSettingMainGui(Area area) {
        super(9);

        this.setItem(1, new ItemBuilder(Material.POTION).setDisplayName("§fArea 버프 설정").setLore("§f범위 내에 플레이어에게 줄 특정 버프들을 설정합니다.").build(), event -> {
            event.setCancelled(true);
            event.getWhoClicked().openInventory(new AreaSettingBuffGui(area).getInventory());
        });
        this.setItem(4, new ItemBuilder(Material.BOOK).setDisplayName("§fArea 룰 설정").setLore("§f범위 내의 지정할 수 있는 특정한 규칙들을 설정합니다.").build(), event -> {
            event.setCancelled(true);
            event.getWhoClicked().openInventory(new AreaSettingRuleGui(area).getInventory());
        });
        this.setItem(7, new ItemBuilder(Material.STRUCTURE_BLOCK).setDisplayName("§fArea 공간 설정").setLore("§fArea의 범위를 설정합니다.").build(), event -> {
            event.setCancelled(true);
        });
    }
}
