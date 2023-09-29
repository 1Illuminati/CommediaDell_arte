package org.red.a_.world.setting;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemFlag;
import org.red.library.A_;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.item.ItemBuilder;
import org.red.library.world.Area;
import org.red.library.world.rule.Rule;
import org.red.world.rule.RuleSettingGui;

public class AreaSettingRuleGui extends RuleSettingGui {
    private final Area area;
    public AreaSettingRuleGui(Area area) {
        super(area);
        this.area = area;

        int i = 18;
        this.setRulePropertyButton(i++, Area.RulePriority.HIGHEST, Material.WOODEN_AXE);
        this.setRulePropertyButton(i++, Area.RulePriority.HIGH, Material.STONE_AXE);
        this.setRulePropertyButton(i++, Area.RulePriority.NORMAL, Material.GOLDEN_AXE);
        this.setRulePropertyButton(i++, Area.RulePriority.LOW, Material.IRON_AXE);
        this.setRulePropertyButton(i++, Area.RulePriority.LOWEST, Material.DIAMOND_AXE);
    }

    private void setRulePropertyButton(int slot, Area.RulePriority priority, Material displayMaterial) {
        ItemBuilder builder = new ItemBuilder(displayMaterial).setDisplayName("§f" + priority.name());

        if (area.getRulePriority().equals(priority))
            builder.addItemFlags(ItemFlag.HIDE_ENCHANTS).addUnsafeEnchantment(Enchantment.SILK_TOUCH, 1);

        this.setItem(slot, builder.build(), event -> {
            A_Player player = A_.getAPlayer((Player) event.getWhoClicked());
            area.setRulePriority(priority);
            player.openInventory(new AreaSettingRuleGui(area), true);
        });
    }

    @Override
    public void onClose(InventoryCloseEvent event) {
        A_Player player = A_.getAPlayer((Player) event.getPlayer());
        player.delayOpenInventory(new AreaSettingMainGui(area));
    }

    @Override
    protected void settingBooleanRuleButton(int slot, Rule<Boolean> rule, Material dispalyMaterial, String description) {
        this.setItem(slot, new ItemBuilder(dispalyMaterial).setDisplayName("§f" + rule.getKey()).setLore(area.getRuleValue(rule) ? "§a허용" : "§c금지", description).build(), event -> {
            event.setCancelled(true);
            area.setRuleValue(rule, !area.getRuleValue(rule));
            A_.getAPlayer((Player) event.getWhoClicked()).openInventory(new AreaSettingRuleGui(area), true);
        });
    }
}
