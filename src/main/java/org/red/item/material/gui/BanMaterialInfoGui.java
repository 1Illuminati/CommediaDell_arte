package org.red.item.material.gui;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.red.CommediaDell_arte;
import org.red.item.material.BanMaterial;
import org.red.library.A_;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.inventory.CustomGui;
import org.red.library.item.ItemBuilder;
import org.red.library.item.material.MaterialAct;

public class BanMaterialInfoGui extends CustomGui {
    private final BanMaterialGui banMaterialGui;
    private final BanMaterial.BanMaterialInfo info;

    public BanMaterialInfoGui(BanMaterialGui banMaterialGui, BanMaterial.BanMaterialInfo info) {
        super(9);
        this.banMaterialGui = banMaterialGui;
        this.info = info;
        this.setInfoButton(1, MaterialAct.CRAFT, Material.CRAFTING_TABLE);
        this.setInfoButton(4, MaterialAct.BREAK, Material.DIAMOND_PICKAXE);
        this.setInfoButton(7, MaterialAct.PLACE, Material.GRASS_BLOCK);

    }

    private void setInfoButton(int slot, MaterialAct act, Material display) {
        boolean allow = banMaterialGui.getBanMaterial().isActAllowed(info.getMaterial(), act);
        ItemStack craftDisplay = new ItemBuilder(display).setDisplayName("§f" + act.name()).setLore(allow ? "§a허용" : "§c금지").build();
        this.setItem(slot, craftDisplay);
        this.setButton(slot, event -> {
            event.setCancelled(true);
            info.setActAllowed(act, !allow);
            A_Player player = A_.getAPlayer(event.getWhoClicked().getUniqueId());
            player.openInventory(new BanMaterialInfoGui(banMaterialGui, info).getInventory(), true);
        });
    }

    @Override
    public void onClose(InventoryCloseEvent event) {
        A_.getAPlayer((Player) event.getPlayer()).delayOpenInventory(new BanMaterialGui(banMaterialGui.getBanMaterial(), banMaterialGui.getPage()).getInventory());
    }
}

