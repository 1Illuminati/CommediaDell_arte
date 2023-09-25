package org.red.library;

import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.red.library.a_.entity.A_Entity;
import org.red.library.a_.entity.A_LivingEntity;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.a_.entity.player.offline.A_OfflinePlayer;
import org.red.library.a_.world.A_World;
import org.red.library.item.event.EventItem;
import org.red.library.item.shop.ShopItem;
import org.red.library.item.shop.price.Price;
import org.red.library.util.timer.BossBarTimer;
import org.red.library.util.timer.Timer;

import java.util.UUID;

public final class A_ {
    private static A_Manager plugin;

    public static void setA_Plugin(A_Manager plugin) {
        A_.plugin = plugin;
    }

    private A_() {
    }

    public static ShopItem createBuyShopItem(ItemStack originItem, Price buyPrice) {
        return plugin.createBuyShopItem(originItem, buyPrice);
    }

    public static ShopItem createSellShopItem(ItemStack originItem, Price sellPrice) {
        return plugin.createSellShopItem(originItem, sellPrice);
    }

    public static ShopItem createBothShopItem(ItemStack originItem, Price buyPrice, Price sellPrice) {
        return plugin.createBothShopItem(originItem, buyPrice, sellPrice);
    }

    public static void setItemInEvent(EventItem eventItem, ItemStack itemStack) {
        plugin.setItemInEvent(eventItem, itemStack);
    }

    public static void setItemInEvent(NamespacedKey eventItemKey, ItemStack itemStack) {
        plugin.setItemInEvent(eventItemKey, itemStack);
    }

    public static boolean isItemInEvent(ItemStack itemStack) {
        return plugin.isItemInEvent(itemStack);
    }

    public static EventItem getEventInItem(ItemStack itemStack) {
        return plugin.getEventInItem(itemStack);
    }

    public static Timer createTimer(NamespacedKey key, int maxTime) {
        return plugin.createTimer(key, maxTime);
    }

    public static BossBarTimer createBossBarTimer(NamespacedKey key, int maxTime, BossBar... bossBars) {
        return plugin.createBossBarTimer(key, maxTime, bossBars);
    }

    public static A_Player getAPlayer(Player player) {
        return plugin.getAPlayer(player);
    }

    public static A_Player getAPlayer(String playerName) {
        return plugin.getAPlayer(playerName);
    }

    public static A_Player getAPlayer(UUID uuid) {
        return plugin.getAPlayer(uuid);
    }

    public static A_OfflinePlayer getAOfflinePlayer(String playerName) {
        return plugin.getAOfflinePlayer(playerName);
    }

    public static A_OfflinePlayer getAOfflinePlayer(UUID uuid) {
        return plugin.getAOfflinePlayer(uuid);
    }

    public static A_OfflinePlayer getAOfflinePlayer(OfflinePlayer offlinePlayer) {
        return plugin.getAOfflinePlayer(offlinePlayer);
    }

    public static A_LivingEntity getALivingEntity(LivingEntity livingEntity) {
        return plugin.getALivingEntity(livingEntity);
    }

    public static A_Entity getAEntity(Entity entity) {
        return plugin.getAEntity(entity);
    }

    public static A_Entity getAEntity(UUID uuid) {
        return plugin.getAEntity(uuid);
    }

    public static A_World getAWorld(String worldName) {
        return plugin.getAWorld(worldName);
    }

    public static A_World getAWorld(World world) {
        return plugin.getAWorld(world);
    }
}
