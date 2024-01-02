package org.red.mcarea.item;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.inventory.Button;
import org.red.library.inventory.CustomGui;
import org.red.library.item.ItemBuilder;

public class EquipmentUpgradeInv extends CustomGui {
    private static final ItemStack DECO_GRASS = new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).setDisplayName("").build();
    private static final ItemStack UPGRADE_BUTTON = new ItemBuilder(Material.ANVIL).setDisplayName("§b강화하기").build();
    public EquipmentUpgradeInv() {
        super("장비 강화", 27);
        this.setSameItem(0, 10, DECO_GRASS);
        this.setSameItem(11, 12, DECO_GRASS);
        this.setSameItem(14, 15, DECO_GRASS);
        this.setSameItem(17, 20, DECO_GRASS);
        this.setSameItem(22, 26, DECO_GRASS);

        this.setItem(21, UPGRADE_BUTTON, event ->{
            event.setCancelled(true);
        });
    }
}
