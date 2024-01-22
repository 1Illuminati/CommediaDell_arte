package org.red.mcarea.item;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.red.library.item.ItemBuilder;
import org.red.mcarea.MCArea;

public class Items {
    public static final ItemStack defaultSword;
    public static final ItemStack defaultAxe;
    public static final ItemStack defaultBow;
    public static final ItemStack defaultTrident;
    public static final ItemStack defaultCrossBow;
    public static final ItemStack defaultScythe;
    public static final ItemStack defaultShield;
    public static final ItemStack defaultHelmet;
    public static final ItemStack defaultChestplate;
    public static final ItemStack defaultLeggings;
    public static final ItemStack defaultBoots;

    public static final ItemStack upgradeAttack;
    public static final ItemStack upgradeArmor;
    public static final ItemStack upgradeArmorToughness;
    public static final ItemStack upgradeAttackSpeed;
    public static final ItemStack upgradeMaxHealth;
    public static final ItemStack upgradeSpeed;

    static {
        defaultSword = new EquipmentBuilder(Material.WOODEN_SWORD).setAttackDamage(4).setAttackSpeed(-1.6).setDisplayName("§f검 +0")
                .setPersistentDataContainer(new NamespacedKey(MCArea.instance, "weapon"), PersistentDataType.STRING, "sword").build();
        defaultAxe = new EquipmentBuilder(Material.WOODEN_AXE).setAttackDamage(7).setAttackSpeed(-2.8).setDisplayName("§f도끼 +0")
                .setPersistentDataContainer(new NamespacedKey(MCArea.instance, "weapon"), PersistentDataType.STRING, "axe").build();
        defaultTrident = new EquipmentBuilder(Material.TRIDENT).setAttackDamage(9).setDisplayName("§f삼지창 +0")
                .setPersistentDataContainer(new NamespacedKey(MCArea.instance, "weapon"), PersistentDataType.STRING, "trident").build();
        defaultScythe = new EquipmentBuilder(Material.WOODEN_HOE).setAttackDamage(3).setAttackSpeed(-2.0).setDisplayName("§f낫 +0")
                .setPersistentDataContainer(new NamespacedKey(MCArea.instance, "weapon"), PersistentDataType.STRING, "scythe").build();
        defaultBow = new EquipmentBuilder(Material.BOW).setAttackDamage(4).setDisplayName("§f활 +0")
                .setPersistentDataContainer(new NamespacedKey(MCArea.instance, "weapon"), PersistentDataType.STRING, "bow").build();
        defaultCrossBow = new EquipmentBuilder(Material.CROSSBOW).setAttackDamage(6).setDisplayName("§f석궁 +0")
                .setPersistentDataContainer(new NamespacedKey(MCArea.instance, "weapon"), PersistentDataType.STRING, "crossbow").build();

        defaultShield = new EquipmentBuilder(Material.SHIELD).setArmor(2).setDisplayName("§f방패 +0").build();

        defaultHelmet = new EquipmentBuilder(Material.LEATHER_HELMET).setArmor(1).setDisplayName("§f투구 +0")
                .setPersistentDataContainer(new NamespacedKey(MCArea.instance, "equipment"), PersistentDataType.STRING, "helmet").build();
        defaultChestplate = new EquipmentBuilder(Material.LEATHER_CHESTPLATE).setArmor(1).setDisplayName("§f갑옷 +0")
                .setPersistentDataContainer(new NamespacedKey(MCArea.instance, "equipment"), PersistentDataType.STRING, "chestplate").build();
        defaultLeggings = new EquipmentBuilder(Material.LEATHER_LEGGINGS).setArmor(1).setDisplayName("§f각반 +0")
                .setPersistentDataContainer(new NamespacedKey(MCArea.instance, "equipment"), PersistentDataType.STRING, "leggings").build();
        defaultBoots = new EquipmentBuilder(Material.LEATHER_BOOTS).setArmor(1).setDisplayName("§f신발 +0")
                .setPersistentDataContainer(new NamespacedKey(MCArea.instance, "equipment"), PersistentDataType.STRING, "boots").build();

        upgradeAttack = new ItemBuilder(Material.GOLD_INGOT).setDisplayName("§b공격력 강화석")
                .setPersistentDataContainer(new NamespacedKey(MCArea.instance, "upgrade"), PersistentDataType.STRING, "attack")
                .setLore("§f장비의 공격력을 +1 해줍니다.", "§f무기에만 사용이 가능합니다.").build();
        upgradeArmor = new ItemBuilder(Material.GOLD_INGOT).setDisplayName("§b방어 강화석")
                .setPersistentDataContainer(new NamespacedKey(MCArea.instance, "upgrade"), PersistentDataType.STRING, "armor")
                .setLore("§f장비의 방어를 +1 해줍니다.", "§f갑옷에만 사용이 가능합니다.").build();
        upgradeArmorToughness = new ItemBuilder(Material.GOLD_INGOT).setDisplayName("§b방어 강도 강화석")
                .setPersistentDataContainer(new NamespacedKey(MCArea.instance, "upgrade"), PersistentDataType.STRING, "armor_toughness")
                .setLore("§f장비의 방어 강도를 +0.5 해줍니다.", "§f갑옷에만 사용이 가능합니다.").build();
        upgradeAttackSpeed = new ItemBuilder(Material.GOLD_INGOT).setDisplayName("§b공격속도 강화석")
                .setPersistentDataContainer(new NamespacedKey(MCArea.instance, "upgrade"), PersistentDataType.STRING, "attack_speed")
                .setLore("§f장비의 공격 속도를 +0.1 해줍니다.", "§f무기에만 사용이 가능합니다.").build();
        upgradeMaxHealth = new ItemBuilder(Material.GOLD_INGOT).setDisplayName("§b체력 강화석")
                .setPersistentDataContainer(new NamespacedKey(MCArea.instance, "upgrade"), PersistentDataType.STRING, "max_health")
                .setLore("§f장비의 체력를 +2 해줍니다.", "§f갑옷에만 사용이 가능합니다.").build();
        upgradeSpeed = new ItemBuilder(Material.GOLD_INGOT).setDisplayName("§b이동속도 강화석")
                .setPersistentDataContainer(new NamespacedKey(MCArea.instance, "upgrade"), PersistentDataType.STRING, "speed")
                .setLore("§f장비의 이동 속도를 +0.005 해줍니다.", "§f신발에만 사용이 가능합니다.").build();
    }
}
