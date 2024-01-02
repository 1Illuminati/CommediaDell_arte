package org.red.mcarea.item;

import lombok.Builder;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;
import org.red.library.item.ItemBuilder;

@Builder
public class EquipmentBuilder extends ItemBuilder {

    public EquipmentBuilder(ItemStack itemStack) {
        super(itemStack);
    }

    public EquipmentBuilder(Material material) {
        super(new ItemStack(material));
    }

    /**
     * 공격력 설정
     * @param attackDamage 공격력
     * @return this
     */
    public EquipmentBuilder setAttackDamage(double attackDamage) {
        this.addAttribute(Attribute.GENERIC_ATTACK_DAMAGE, attackDamage, AttributeModifier.Operation.ADD_NUMBER);
        return this;
    }

    /**
     * 방어력 설정
     * @param armor 방어력
     * @return this
     */
    public EquipmentBuilder setArmor(double armor) {
        this.addAttribute(Attribute.GENERIC_ARMOR, armor, AttributeModifier.Operation.ADD_NUMBER);
        return this;
    }

    /**
     * 추가 체력 설정
     * @param maxHealth 추가 체력
     * @return this
     */
    public EquipmentBuilder setMaxHealth(double maxHealth) {
        this.addAttribute(Attribute.GENERIC_MAX_HEALTH, maxHealth, AttributeModifier.Operation.ADD_NUMBER);
        return this;
    }

    /**
     * 이동 속도 설정
     * @param speed 이동 속도
     * @return this
     */
    public EquipmentBuilder setSpeed(double speed) {
        this.addAttribute(Attribute.GENERIC_MOVEMENT_SPEED, speed, AttributeModifier.Operation.ADD_NUMBER);
        return this;
    }

    /**
     * 방어 강도 설정
     * @param armorToughness 방어 강도 수치
     * @return this
     */
    public EquipmentBuilder setArmorToughness(double armorToughness) {
        this.addAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS, armorToughness, AttributeModifier.Operation.ADD_NUMBER);
        return this;
    }

    /**
     * 공격 속도 설정
     * @param attackSpeed 공격 속도
     * @return this
     */
    public EquipmentBuilder setAttackSpeed(double attackSpeed) {
        this.addAttribute(Attribute.GENERIC_ATTACK_SPEED, attackSpeed, AttributeModifier.Operation.ADD_NUMBER);
        return this;
    }
}
