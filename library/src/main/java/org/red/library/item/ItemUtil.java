package org.red.library.item;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.MaterialData;

import java.lang.reflect.Field;
import java.util.*;

public final class ItemUtil {
    private static AbstractItemManager itemManager;
    private ItemUtil() {
        throw new IllegalArgumentException("Utility Class");
    }

    public static void setItemManager(AbstractItemManager itemManager) {
        ItemUtil.itemManager = itemManager;
    }

    public static ItemStack getSkullByUrl(String url) {
        return itemManager.getSkullByUrl(url);
    }

    public static ItemStack getSkull(OfflinePlayer player) {
        return itemManager.getSkull(player);
    }

    public static boolean checkNetheriteTool(Material material) {
        return itemManager.checkNetheriteTool(material);
    }

    public static boolean checkIronTool(Material material) {
        return itemManager.checkIronTool(material);
    }

    public static boolean checkGoldenTool(Material material) {
        return itemManager.checkGoldenTool(material);
    }

    public static boolean checkDiamondTool(Material material) {
        return itemManager.checkDiamondTool(material);
    }

    public static boolean checkStoneTool(Material material) {
        return itemManager.checkStoneTool(material);
    }

    public static boolean checkWoodenTool(Material material) {
        return itemManager.checkWoodenTool(material);
    }

    public static boolean checkAxe(Material material) {
        return itemManager.checkAxe(material);
    }

    public static boolean checkHoe(Material material) {
        return itemManager.checkHoe(material);
    }

    public static boolean checkShovel(Material material) {
        return itemManager.checkShovel(material);
    }

    public static boolean checkPickaxe(Material material) {
        return itemManager.checkPickaxe(material);
    }

    public static boolean checkSword(Material material) {
        return itemManager.checkSword(material);
    }

    public static boolean checkArmor(Material material) {
        return itemManager.checkArmor(material);
    }

    public static boolean checkNetheriteArmor(Material material) {
        return itemManager.checkNetheriteArmor(material);
    }

    public static boolean checkLeatherArmor(Material material) {
        return itemManager.checkLeatherArmor(material);
    }

    public static boolean checkGoldenArmor(Material material) {
        return itemManager.checkGoldenArmor(material);
    }

    public static boolean checkDiamondArmor(Material material) {
        return itemManager.checkDiamondArmor(material);
    }

    public static boolean checkChainMailArmor(Material material) {
        return itemManager.checkChainMailArmor(material);
    }

    public static boolean checkIronArmor(Material material) {
        return itemManager.checkIronArmor(material);
    }

    public static boolean checkHelmet(Material material) {
        return itemManager.checkHelmet(material);
    }

    public static boolean checkChestPlate(Material material) {
        return itemManager.checkChestPlate(material);
    }

    public static boolean checkLeggings(Material material) {
        return itemManager.checkLeggings(material);
    }

    public static boolean checkBoots(Material material) {
        return itemManager.checkBoots(material);
    }

    public static boolean checkWeapon(Material material) {
        return itemManager.checkWeapon(material);
    }

    public static boolean checkProjectileWeapon(Material material) {
        return itemManager.checkProjectileWeapon(material);
    }

    public static boolean checkMeleeWeapon(Material material) {
        return itemManager.checkMeleeWeapon(material);
    }

    public static double getProjectileOriginMaxDamage(ItemStack itemStack) {
        return itemManager.getProjectileOriginMaxDamage(itemStack);
    }

    public static double getShapenessDamage(int level) {
        return itemManager.getShapenessDamage(level);
    }

    public static double getShapenessDamage(ItemStack itemStack) {
        return itemManager.getShapenessDamage(itemStack);
    }

    public static double getSmiteDamage(int level) {
        return itemManager.getSmiteDamage(level);
    }

    public static double getSmiteDamage(ItemStack itemStack) {
        return itemManager.getSmiteDamage(itemStack);
    }

    public static double getBaneOfArthropodsDamage(int level) {
        return itemManager.getBaneOfArthropodsDamage(level);
    }

    public static double getBaneOfArthropodsDamage(ItemStack itemStack) {
        return itemManager.getBaneOfArthropodsDamage(itemStack);
    }

    public static double getItemOriginMaxDamage(Material material) {
        return itemManager.getItemOriginMaxDamage(material);
    }

    public static ItemStack makeItem(String display, Material material, List<String> lore, int data, boolean unbreakable) {
        return itemManager.makeItem(display, material, lore, data, unbreakable);
    }

    public static ItemStack makeItem(String display, Material material, String explain, int data, boolean unbreakable) {
        return itemManager.makeItem(display, material, explain, data, unbreakable);
    }

    public static ItemStack makeItem(String display, Material material, List<String> lore, int data) {
        return itemManager.makeItem(display, material, lore, data);
    }

    public static ItemStack makeItem(String display, Material material, String explain, int data) {
        return itemManager.makeItem(display, material, explain, data);
    }

    public static ItemStack makeItem(String display, Material material, List<String> lore) {
        return itemManager.makeItem(display, material, lore);
    }

    public static ItemStack makeItem(String display, Material material, String explain) {
        return itemManager.makeItem(display, material, explain);
    }

    public static ItemStack makeItem(String display, Material material, int data, boolean unbreakable) {
        return itemManager.makeItem(display, material, data, unbreakable);
    }

    public static ItemStack makeItem(String display, Material material, int data) {
        return itemManager.makeItem(display, material, data);
    }

    public static ItemStack makeItem(String display, Material material, boolean unbreakable) {
        return itemManager.makeItem(display, material, unbreakable);
    }

    public static ItemStack makeItem(String display, Material material) {
        return itemManager.makeItem(display, material);
    }

    public static ItemStack getAir() {
        return itemManager.getAir();
    }
}
