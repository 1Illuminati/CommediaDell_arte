package org.red.library.item;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.MaterialData;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

public abstract class AbstractItemManager {
    public ItemStack getSkullByUrl(String url) {
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        if (url.isEmpty()) {
            return skull;
        }

        SkullMeta skullMeta = (SkullMeta)skull.getItemMeta();

        StringBuilder builder = new StringBuilder();
        url.chars().filter(c -> (c >= '0' && c <= '9') || (c >= 'a' && c <= 'f')).forEach(c -> {
            if (builder.length() != 0 && builder.length() / 4 == 0) builder.append("-");
            builder.append((char) c);
        });

        try {
            Class<?> gameProfile = Class.forName("com.mojang.authlib.GameProfile");
            Class<?> property = Class.forName("com.mojang.authlib.properties.Property");
            Object profile = gameProfile.getConstructor(UUID.class, String.class).newInstance(UUID.fromString(builder.toString()), null);
            byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
            gameProfile.getMethod("getProperties").invoke(profile).getClass().getMethod("put", Object.class, Object.class).invoke(gameProfile.getMethod("getProperties").invoke(profile), "textures", property.getConstructor(String.class, String.class).newInstance("textures", new String(encodedData)));
            Field profileField = skullMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(skullMeta, profile);
        } catch (Exception e) {
            throw new RuntimeException();
        }

        skull.setItemMeta(skullMeta);
        return skull;
    }
    public ItemStack getSkull(OfflinePlayer player) {
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        if (player == null) {
            return skull;
        }

        SkullMeta skullMeta = (SkullMeta)skull.getItemMeta();
        skullMeta.setOwningPlayer(player);
        skull.setItemMeta(skullMeta);
        return skull;
    }

    public boolean checkNetheriteTool(Material material) {
        return material == Material.NETHERITE_AXE || material == Material.NETHERITE_HOE || material == Material.NETHERITE_SWORD
                || material == Material.NETHERITE_SHOVEL || material == Material.NETHERITE_PICKAXE;
    }

    public boolean checkIronTool(Material material) {
        return material == Material.IRON_AXE || material == Material.IRON_HOE || material == Material.IRON_SWORD
                || material == Material.IRON_SHOVEL || material == Material.IRON_PICKAXE;
    }

    public boolean checkGoldenTool(Material material) {
        return material == Material.GOLDEN_AXE || material == Material.GOLDEN_HOE || material == Material.GOLDEN_SWORD
                || material == Material.GOLDEN_SHOVEL || material == Material.GOLDEN_PICKAXE;
    }

    public boolean checkDiamondTool(Material material) {
        return material == Material.DIAMOND_AXE || material == Material.DIAMOND_HOE
                || material == Material.DIAMOND_SWORD || material == Material.DIAMOND_SHOVEL
                || material == Material.DIAMOND_PICKAXE;
    }

    public boolean checkStoneTool(Material material) {
        return material == Material.STONE_AXE || material == Material.STONE_HOE || material == Material.STONE_SWORD
                || material == Material.STONE_SHOVEL || material == Material.STONE_PICKAXE;
    }

    /**
     * Check if the item is a wooden tool
     *
     * @param material the material of the item
     * @return true if the item is a wooden tool
     */
    public boolean checkWoodenTool(Material material) {
        return material == Material.WOODEN_AXE || material == Material.WOODEN_HOE || material == Material.WOODEN_SWORD
                || material == Material.WOODEN_SHOVEL || material == Material.WOODEN_PICKAXE;
    }

    /**
     * Check if the item is an axe
     *
     * @param material the material of the item
     * @return true if the item is an axe
     */
    public boolean checkAxe(Material material) {
        return material == Material.IRON_AXE || material == Material.GOLDEN_AXE || material == Material.STONE_AXE
                || material == Material.WOODEN_AXE || material == Material.DIAMOND_AXE || material == Material.NETHERITE_AXE;
    }

    /**
     * Check if the item is a hoe
     *
     * @param material the material of the item
     * @return true if the item is a hoe
     */
    public boolean checkHoe(Material material) {
        return material == Material.IRON_HOE || material == Material.GOLDEN_HOE || material == Material.STONE_HOE
                || material == Material.WOODEN_HOE || material == Material.DIAMOND_HOE || material == Material.NETHERITE_HOE;
    }

    /**
     * Check if the item is a shovel
     *
     * @param material the material of the item
     * @return true if the item is a shovel
     */
    public boolean checkShovel(Material material) {
        return material == Material.IRON_SHOVEL || material == Material.GOLDEN_SHOVEL || material == Material.STONE_SHOVEL
                || material == Material.WOODEN_SHOVEL || material == Material.DIAMOND_SHOVEL || material == Material.NETHERITE_SHOVEL;
    }

    /**
     * Check if the item is a pickaxe
     *
     * @param material the material of the item
     * @return true if the item is a pickaxe
     */
    public boolean checkPickaxe(Material material) {
        return material == Material.IRON_PICKAXE || material == Material.GOLDEN_PICKAXE
                || material == Material.STONE_PICKAXE || material == Material.WOODEN_PICKAXE
                || material == Material.DIAMOND_PICKAXE || material == Material.NETHERITE_PICKAXE;
    }

    /**
     * Check if the item is a sword
     *
     * @param material the material of the item
     * @return true if the item is a sword
     */
    public boolean checkSword(Material material) {
        return material == Material.IRON_SWORD || material == Material.GOLDEN_SWORD || material == Material.STONE_SWORD
                || material == Material.WOODEN_SWORD || material == Material.DIAMOND_SWORD || material == Material.NETHERITE_SWORD;
    }

    /**
     * Check if the item is an armor
     *
     * @param material the material of the item
     * @return true if the item is an armor
     */
    public boolean checkArmor(Material material) {
        return material == Material.IRON_HELMET || material == Material.IRON_CHESTPLATE
                || material == Material.IRON_LEGGINGS || material == Material.IRON_BOOTS
                || material == Material.CHAINMAIL_HELMET || material == Material.CHAINMAIL_CHESTPLATE
                || material == Material.CHAINMAIL_LEGGINGS || material == Material.CHAINMAIL_BOOTS
                || material == Material.DIAMOND_HELMET || material == Material.DIAMOND_CHESTPLATE
                || material == Material.DIAMOND_LEGGINGS || material == Material.DIAMOND_BOOTS
                || material == Material.LEATHER_HELMET || material == Material.LEATHER_CHESTPLATE
                || material == Material.LEATHER_LEGGINGS || material == Material.LEATHER_BOOTS
                || material == Material.GOLDEN_HELMET || material == Material.GOLDEN_CHESTPLATE
                || material == Material.GOLDEN_LEGGINGS || material == Material.GOLDEN_BOOTS;
    }

    /**
     * Check if the item is a netherite armor
     *
     * @param material the material of the item
     * @return true if the item is a netherite armor
     */
    public boolean checkNetheriteArmor(Material material) {
        return material == Material.NETHERITE_HELMET || material == Material.NETHERITE_CHESTPLATE
                || material == Material.NETHERITE_LEGGINGS || material == Material.NETHERITE_BOOTS;
    }

    /**
     * Check if the item is a leather armor
     *
     * @param material the material of the item
     * @return true if the item is a leather armor
     */
    public boolean checkLeatherArmor(Material material) {
        return material == Material.LEATHER_HELMET || material == Material.LEATHER_CHESTPLATE
                || material == Material.LEATHER_LEGGINGS || material == Material.LEATHER_BOOTS;
    }

    /**
     * Check if the item is a golden armor
     *
     * @param material the material of the item
     * @return true if the item is a golden armor
     */
    public boolean checkGoldenArmor(Material material) {
        return material == Material.GOLDEN_HELMET || material == Material.GOLDEN_CHESTPLATE
                || material == Material.GOLDEN_LEGGINGS || material == Material.GOLDEN_BOOTS;
    }

    /**
     * Check if the item is a diamond armor
     *
     * @param material the material of the item
     * @return true if the item is a diamond armor
     */
    public boolean checkDiamondArmor(Material material) {
        return material == Material.DIAMOND_HELMET || material == Material.DIAMOND_CHESTPLATE
                || material == Material.DIAMOND_LEGGINGS || material == Material.DIAMOND_BOOTS;
    }

    /**
     * Check if the item is a chainmail armor
     *
     * @param material the material of the item
     * @return true if the item is a chainmail armor
     */
    public boolean checkChainMailArmor(Material material) {
        return material == Material.CHAINMAIL_HELMET || material == Material.CHAINMAIL_CHESTPLATE
                || material == Material.CHAINMAIL_LEGGINGS || material == Material.CHAINMAIL_BOOTS;
    }

    /**
     * Check if the item is an iron armor
     *
     * @param material the material of the item
     * @return true if the item is an iron armor
     */
    public boolean checkIronArmor(Material material) {
        return material == Material.IRON_HELMET || material == Material.IRON_CHESTPLATE
                || material == Material.IRON_LEGGINGS || material == Material.IRON_BOOTS;
    }

    /**
     * Check if the item is a helmet
     *
     * @param material the material of the item
     * @return true if the item is a helmet
     */
    public boolean checkHelmet(Material material) {
        return material == Material.IRON_HELMET || material == Material.CHAINMAIL_HELMET
                || material == Material.DIAMOND_HELMET || material == Material.LEATHER_HELMET
                || material == Material.GOLDEN_HELMET || material == Material.NETHERITE_HELMET;
    }

    /**
     * Check if the item is a chestPlate
     *
     * @param material the material of the item
     * @return true if the item is a chestPlate
     */
    public boolean checkChestPlate(Material material) {
        return material == Material.IRON_CHESTPLATE || material == Material.CHAINMAIL_CHESTPLATE
                || material == Material.DIAMOND_CHESTPLATE || material == Material.LEATHER_CHESTPLATE
                || material == Material.GOLDEN_CHESTPLATE || material == Material.NETHERITE_CHESTPLATE;
    }

    /**
     * Check if the item is a leggings
     *
     * @param material the material of the item
     * @return true if the item is a leggings
     */
    public boolean checkLeggings(Material material) {
        return material == Material.IRON_LEGGINGS || material == Material.CHAINMAIL_LEGGINGS
                || material == Material.DIAMOND_LEGGINGS || material == Material.LEATHER_LEGGINGS
                || material == Material.GOLDEN_LEGGINGS || material == Material.NETHERITE_LEGGINGS;
    }

    /**
     * Check if the item is a boots
     *
     * @param material the material of the item
     * @return true if the item is a boots
     */
    public boolean checkBoots(Material material) {
        return material == Material.IRON_BOOTS || material == Material.CHAINMAIL_BOOTS
                || material == Material.DIAMOND_BOOTS || material == Material.LEATHER_BOOTS
                || material == Material.GOLDEN_BOOTS || material == Material.NETHERITE_BOOTS;
    }

    /**
     * Check if the item is a weapon
     *
     * @param material the material of the item
     * @return true if the item is a weapon
     */
    public boolean checkWeapon(Material material) {
        return checkMeleeWeapon(material) || checkProjectileWeapon(material);
    }

    /**
     * Check if the item is a projectile weapon
     *
     * @param material the material of the item
     * @return true if the item is a projectile weapon
     */
    public boolean checkProjectileWeapon(Material material) {
        return material == Material.BOW || material == Material.CROSSBOW || material == Material.TRIDENT;
    }

    /**
     * Check if the item is a melee weapon
     *
     * @param material the material of the item
     * @return true if the item is a melee weapon
     */
    public boolean checkMeleeWeapon(Material material) {
        return checkSword(material) || checkAxe(material) || material == Material.TRIDENT;
    }

    public double getProjectileOriginMaxDamage(ItemStack itemStack) {
        if (!checkProjectileWeapon(itemStack.getType()))
            return 0;
        return 10 + itemStack.getEnchantmentLevel(Enchantment.ARROW_DAMAGE) * 2.5;
    }

    public double getShapenessDamage(int level) {
        return level > 0 ? 0.5 * (level - 1) + 1 : 0;
    }

    public double getShapenessDamage(ItemStack itemStack) {
        return getShapenessDamage(itemStack.getEnchantmentLevel(Enchantment.DAMAGE_ALL));
    }

    public double getSmiteDamage(int level) {
        return level * 2.5;
    }

    public double getSmiteDamage(ItemStack itemStack) {
        return getSmiteDamage(itemStack.getEnchantmentLevel(Enchantment.DAMAGE_UNDEAD));
    }

    public double getBaneOfArthropodsDamage(int level) {
        return level * 2.5;
    }

    public double getBaneOfArthropodsDamage(ItemStack itemStack) {
        return getBaneOfArthropodsDamage(itemStack.getEnchantmentLevel(Enchantment.DAMAGE_ARTHROPODS));
    }

    /**
     * Get the origin max damage to item
     * Does not include additional damage from enchantments
     *
     * @param material the material of the item
     * @return the origin max damage to item
     */
    public double getItemOriginMaxDamage(Material material) {
        switch (material) {
            case NETHERITE_AXE: return 10;
            case IRON_AXE:
            case STONE_AXE:
            case DIAMOND_AXE: return 9;
            case WOODEN_AXE:
            case GOLDEN_AXE:
            case DIAMOND_SWORD: return 7;
            case IRON_SHOVEL: return 4.5;
            case NETHERITE_SHOVEL: return 6.5;
            case STONE_SHOVEL: return 3.5;
            case DIAMOND_SHOVEL: return 5.5;
            case WOODEN_SHOVEL:
            case GOLDEN_SHOVEL: return 2.5;
            case IRON_PICKAXE:
            case WOODEN_SWORD:
            case GOLDEN_SWORD: return 4;
            case STONE_PICKAXE: return 3;
            case DIAMOND_PICKAXE:
            case STONE_SWORD: return 5;
            case WOODEN_PICKAXE:
            case GOLDEN_PICKAXE: return 2;
            case IRON_SWORD:
            case NETHERITE_PICKAXE: return 6;
            case NETHERITE_SWORD: return 8;
            default: return 1;
        }
    }

    public ItemStack makeItem(String display, Material material, List<String> lore, int data, boolean unbreakable) {
        ItemStack itemStack = new MaterialData(material, (byte) data).toItemStack(1);
        setItemMeta(itemStack, display, lore, unbreakable);
        return itemStack;
    }

    public ItemStack makeItem(String display, Material material, String explain, int data, boolean unbreakable) {
        List<String> lore = new ArrayList<>();
        lore.add(explain);
        return makeItem(display, material, lore, data, unbreakable);
    }

    public ItemStack makeItem(String display, Material material, List<String> lore, int data) {
        return makeItem(display, material, lore, data, false);
    }

    public ItemStack makeItem(String display, Material material, String explain, int data) {
        return makeItem(display, material, explain, data, false);
    }

    public ItemStack makeItem(String display, Material material, List<String> lore) {
        return makeItem(display, material, lore, 0, false);
    }

    public ItemStack makeItem(String display, Material material, String explain) {
        return makeItem(display, material, explain, 0, false);
    }

    public ItemStack makeItem(String display, Material material, int data, boolean unbreakable) {
        return makeItem(display, material, new ArrayList<>(), data, unbreakable);
    }

    public ItemStack makeItem(String display, Material material, int data) {
        return makeItem(display, material, new ArrayList<>(), data, false);
    }

    public ItemStack makeItem(String display, Material material, boolean unbreakable) {
        return makeItem(display, material, new ArrayList<>(), 0, unbreakable);
    }

    public ItemStack makeItem(String display, Material material) {
        return makeItem(display, material, new ArrayList<>(), 0, false);
    }

    public ItemStack getAir() {
        return new ItemStack(Material.AIR);
    }

    private void setItemMeta(ItemStack itemStack, String display, List<String> lore, boolean unbreakable) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(display);
        itemMeta.setLore(lore);
        itemMeta.setUnbreakable(unbreakable);
        itemStack.setItemMeta(itemMeta);
    }
}
