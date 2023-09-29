package org.red.a_.world.setting;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffectType;
import org.red.library.A_;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.inventory.CustomGui;
import org.red.library.item.ItemBuilder;
import org.red.library.world.Area;

public class AreaSettingBuffGui extends CustomGui {
    private final Area area;
    public AreaSettingBuffGui(Area area) {
        super(54);
        this.area = area;

        int i = 0;
        for (PotionEffectType type : PotionEffectType.values()) {
            if (type == null) continue;
            ItemStack itemStack = new ItemStack(Material.POTION);
            PotionMeta meta = (PotionMeta) itemStack.getItemMeta();
            meta.setColor(Color.AQUA);
            itemStack.setItemMeta(meta);

            this.settingBuffButton(i++, type, itemStack);
        }

    }

    @Override
    public void onClose(InventoryCloseEvent event) {
        A_Player player = A_.getAPlayer((Player) event.getPlayer());
        player.delayOpenInventory(new AreaSettingMainGui(area));
    }

    private void settingBuffButton(int slot, PotionEffectType type, ItemStack displayItem) {
        int level = area.getPotionEffectMap().getOrDefault(type, -1);
        this.setItem(slot, new ItemBuilder(displayItem).setDisplayName("§f" + type.getName()).setLore("§f현재 버프 레벨: " + (level <= -1 ? "없음" : level),
                "§f좌클릭시 버프 레벨 +1", "§f우클릭시 버프 레벨 -1").build(), event -> {
            int buffLevel = area.getPotionEffectMap().getOrDefault(type, -1);
            event.setCancelled(true);
            ClickType clickType = event.getClick();

            switch (clickType) {
                case LEFT:
                case SHIFT_LEFT:
                    buffLevel++;
                break;
                case RIGHT:
                case SHIFT_RIGHT:
                    buffLevel--;
                break;
                default:
                return;
            }

            if (buffLevel <= -1) area.getPotionEffectMap().remove(type);
            else area.getPotionEffectMap().put(type, buffLevel);

            A_Player player = A_.getAPlayer(event.getWhoClicked().getUniqueId());
            player.openInventory(new AreaSettingBuffGui(area), true);
        });
    }
}
