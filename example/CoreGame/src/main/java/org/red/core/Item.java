package org.red.core;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.red.library.item.ItemBuilder;

public class Item {
    private static final NamespacedKey BLOOD_BOTTLE = new NamespacedKey(CoreGame.plugin, "blood_bottle");
    public static final ItemStack STAT_BOOK;
    public static final ItemStack PLAYER_HEART;
    public static final ItemStack CORE;
    public static final ItemStack FULL_BLOOD_BOTTLE;
    public static final ItemStack HALF_BLOOD_BOTTLE;
    public static final ItemStack EMPTY_BLOOD_BOTTLE;

    public static boolean isCanFillBloodBottle(ItemStack item) {
        return item != null && item.getType() == Material.BONE && item.hasItemMeta()
                && item.getItemMeta().getPersistentDataContainer().has(BLOOD_BOTTLE, PersistentDataType.INTEGER)
                && item.getItemMeta().getPersistentDataContainer().get(BLOOD_BOTTLE, PersistentDataType.INTEGER) < 500;
    }

    public static ItemStack getBloodBottle(int stack) {
        if (stack < 0 || stack > 500) {
            throw new IllegalArgumentException("Blood bottle stack must be between 0 and 500");
        }

        switch (stack) {
            case 0:
                return EMPTY_BLOOD_BOTTLE;
            case 500:
                return FULL_BLOOD_BOTTLE;
            default:
                return new ItemBuilder(HALF_BLOOD_BOTTLE.clone()).setLore("§c붉은색 액체§f가 병속을 가득 채워간다.", "§cBlood §7" + stack + " / 500")
                        .setPersistentDataContainer(BLOOD_BOTTLE, PersistentDataType.INTEGER, stack).build();
        }
    }

    static {
        STAT_BOOK = new ItemBuilder(Material.BONE).setCustomModelData(3).setDisplayName("§6금단의 서")
                .setLore("§f제작자는 말합니다. 커맨드 보다 이 방식이 난 더 편해!").build();
        PLAYER_HEART = new ItemBuilder(Material.BONE).setCustomModelData(4).setDisplayName("§c심장").build();
        CORE = new ItemBuilder(Material.BONE).setCustomModelData(5).setDisplayName("§6§l코어").build();
        FULL_BLOOD_BOTTLE = new ItemBuilder(Material.BONE).setCustomModelData(6).setDisplayName("§4완전히 찬 병")
                .setLore("§4피.. §f오로지 §4피§f로만 이루어진 병이다.", "§cBlood §7500 / 500")
                .setPersistentDataContainer(BLOOD_BOTTLE, PersistentDataType.INTEGER, 500).build();

        HALF_BLOOD_BOTTLE = new ItemBuilder(Material.BONE).setCustomModelData(7).setDisplayName("§c일부가 찬 병")
                .setLore("§c붉은색 액체§f가 병속을 가득 채워간다.", "§cBlood §71 / 500")
                .setPersistentDataContainer(BLOOD_BOTTLE, PersistentDataType.INTEGER, 1).build();

        EMPTY_BLOOD_BOTTLE = new ItemBuilder(Material.BONE).setCustomModelData(8).setDisplayName("§f비어있는 병")
                .setLore("§f아직 아무것도 담기지 않은 무엇이든 담길 수 있는 순수한 병", "§cBlood §70 / 500")
                .setPersistentDataContainer(BLOOD_BOTTLE, PersistentDataType.INTEGER, 0).build();
    }
}
