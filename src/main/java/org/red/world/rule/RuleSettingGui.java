package org.red.world.rule;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.red.a_.world.setting.AreaSettingRuleGui;
import org.red.library.A_;
import org.red.library.inventory.CustomGui;
import org.red.library.item.ItemBuilder;
import org.red.library.world.rule.HasRule;
import org.red.library.world.rule.Rule;

public class RuleSettingGui extends CustomGui {
    private final HasRule hasRule;
    public RuleSettingGui(HasRule hasRule) {
        super("Rule Setting", 27);
        this.hasRule = hasRule;
        int i = 0;
        this.settingBooleanRuleButton(i++, Rule.FALL_DAMAGE, Material.FEATHER, "§f낙하 데미지를 받을 수 있는지 설정합니다.");
        this.settingBooleanRuleButton(i++, Rule.RIDING, Material.SADDLE, "§f탈 수 있는 Entity를 탈 수 있는지 설정합니다.");
        this.settingBooleanRuleButton(i++, Rule.PVP, Material.IRON_SWORD, "§fPVP를 할 수 있는지 설정합니다.");
        this.settingBooleanRuleButton(i++, Rule.BREAK, Material.DIAMOND_PICKAXE, "§f블럭을 부술 수 있는지 설정합니다.");
        this.settingBooleanRuleButton(i++, Rule.PLACE, Material.GRASS_BLOCK, "§f블럭을 설치할 수 있는지 설정합니다.");
        this.settingBooleanRuleButton(i++, Rule.INTERACT, Material.STONE_BUTTON, "§f블럭과 상호작용할 수 있는지 설정합니다.");
        this.settingBooleanRuleButton(i++, Rule.COMMAND, Material.COMMAND_BLOCK, "§f명령어를 사용할 수 있는지 설정합니다.");
        this.settingBooleanRuleButton(i++, Rule.CHAT, Material.BOOK, "§f채팅을 사용할 수 있는지 설정합니다.");
        this.settingBooleanRuleButton(i++, Rule.FISHING, Material.FISHING_ROD, "§f낚시를 할 수 있는지 설정합니다.");
        this.settingBooleanRuleButton(i++, Rule.SWAP_HAND, Material.OAK_SIGN, "§f손을 바꿀 수 있는지 설정합니다.");
        this.settingBooleanRuleButton(i++, Rule.ATTACK, Material.IRON_SWORD, "§f공격할 수 있는지 설정합니다.");
        this.settingBooleanRuleButton(i++, Rule.SPAWN_MOB, Material.ZOMBIE_SPAWN_EGG, "§f몹을 소환할 수 있는지 설정합니다.");
        this.settingBooleanRuleButton(i++, Rule.SPAWN_ENTITY, Material.ARMOR_STAND, "§f엔티티를 소환할 수 있는지 설정합니다.");
        this.settingBooleanRuleButton(i++, Rule.DROP, Material.DIAMOND, "§f아이템을 버릴 수 있는지 설정합니다.");
        this.settingBooleanRuleButton(i++, Rule.MOVE, Material.MINECART, "§f이동할 수 있는지 설정합니다.");
        this.settingBooleanRuleButton(i++, Rule.BREED, Material.WHEAT, "§f번식할 수 있는지 설정합니다.");
    }

    protected void settingBooleanRuleButton(int slot, Rule<Boolean> rule, Material dispalyMaterial, String description) {
        this.setItem(slot, new ItemBuilder(dispalyMaterial).setDisplayName("§f" + rule.getKey()).setLore(hasRule.getRuleValue(rule) ? "§a허용" : "§c금지", description).build(), event -> {
            event.setCancelled(true);
            hasRule.setRuleValue(rule, !hasRule.getRuleValue(rule));
            A_.getAPlayer((Player) event.getWhoClicked()).openInventory(new RuleSettingGui(hasRule), true);
        });
    }
}
