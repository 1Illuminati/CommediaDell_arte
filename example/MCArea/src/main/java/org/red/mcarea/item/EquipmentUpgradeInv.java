package org.red.mcarea.item;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.red.library.A_;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.inventory.CustomGui;
import org.red.library.item.ItemBuilder;
import org.red.mcarea.MCArea;

public class EquipmentUpgradeInv extends CustomGui {
    private static final ItemStack DECO_GRASS = new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).setDisplayName("").build();
    private static final ItemStack UPGRADE_BUTTON = new ItemBuilder(Material.ANVIL).setDisplayName("§b강화하기").build();

    /**
     * 13 - 강화할 아이템
     * 16 - 강화 재료
     * 22 - 강화하기 버튼
     */
    public EquipmentUpgradeInv() {
        super("장비 강화", 27);
        this.setSameItem(0, 12, DECO_GRASS);
        this.setSameItem(14, 15, DECO_GRASS);
        this.setSameItem(17, 21, DECO_GRASS);
        this.setSameItem(23, 26, DECO_GRASS);

        this.setItem(22, UPGRADE_BUTTON, event ->{
            event.setCancelled(true);
            A_Player aPlayer = A_.getAPlayer(event.getWhoClicked().getUniqueId());
            ItemStack upgradeEquipment = this.getInventory().getItem(13);
            ItemStack upgradeMaterial = this.getInventory().getItem(16);

            if (upgradeEquipment == null || upgradeMaterial == null) {
                aPlayer.sendMessage("§c강화할 장비와 강화 재료를 모두 넣어주세요.");
                aPlayer.closeInventory();
                return;
            }

            PersistentDataContainer upgradeEquipmentPDC = upgradeEquipment.getItemMeta().getPersistentDataContainer();
            PersistentDataContainer upgradeMaterialPDC = upgradeMaterial.getItemMeta().getPersistentDataContainer();

            String upgradeType;
            boolean isWeapon = false;
            boolean isArmor = false;
            String equipmentType;
            if (upgradeMaterialPDC.has(new NamespacedKey(MCArea.instance, "upgrade"), PersistentDataType.STRING)) {
                upgradeMaterialPDC.get(new NamespacedKey(MCArea.instance, "upgrade"), PersistentDataType.STRING);
            } else {
                aPlayer.sendMessage("§c강화 재료가 아닙니다.");
                aPlayer.closeInventory();
                return;
            }

            if (upgradeEquipmentPDC.has(new NamespacedKey(MCArea.instance, "armor"), PersistentDataType.STRING)) {
                equipmentType = upgradeEquipmentPDC.get(new NamespacedKey(MCArea.instance, "armor"), PersistentDataType.STRING);
                isArmor = true;
            } else if (upgradeEquipmentPDC.has(new NamespacedKey(MCArea.instance, "weapon"), PersistentDataType.STRING)) {
                equipmentType = upgradeEquipmentPDC.get(new NamespacedKey(MCArea.instance, "weapon"), PersistentDataType.STRING);
                isWeapon = true;
            } else {
                aPlayer.sendMessage("§c강화할 수 없는 장비입니다.");
                aPlayer.closeInventory();
                return;
            }



        });
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        event.setCancelled(true);
    }
}
