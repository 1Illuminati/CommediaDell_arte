package org.red.library;

import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.block.BlockState;
import org.bukkit.block.TileState;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.red.library.interactive.block.InteractiveTile;
import org.red.library.interactive.item.InteractiveItem;
import org.red.library.interactive.item.InteractiveItemAct;
import org.red.library.a_.entity.A_Entity;
import org.red.library.a_.entity.A_LivingEntity;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.a_.entity.player.offline.A_OfflinePlayer;
import org.red.library.a_.world.A_World;
import org.red.library.interactive.InteractiveAct;
import org.red.library.interactive.InteractiveObj;
import org.red.library.interactive.block.InteractiveTileAct;
import org.red.library.item.randombox.RandomBox;
import org.red.library.item.shop.ShopItem;
import org.red.library.item.shop.price.Price;
import org.red.library.util.map.DataMap;
import org.red.library.util.timer.BossBarTimer;
import org.red.library.util.timer.Timer;
import org.red.library.vault.EconomyAccount;

import java.util.List;
import java.util.UUID;

public final class A_ {
    private static A_Manager plugin;

    public static void setA_Plugin(A_Manager plugin) {
        A_.plugin = plugin;
    }

    public static RandomBox createRandomBox(String name, List<ItemStack> items) {
        return plugin.createRandomBox(name, items);
    }

    public static RandomBox createRandomBox(String name, ItemStack... items) {
        return plugin.createRandomBox(name, items);
    }

    public static DataMap getPluginDataMap(Plugin plugin, String name) {
        return A_.plugin.getPluginDataMap(plugin, name);
    }

    public static void removeAEntity(UUID uuid) {
        plugin.removeAEntity(uuid);
    }

    public static void removeAEntity(Entity entity) {
        plugin.removeAEntity(entity);
    }

    public static boolean isTileInInteractive(TileState tileState) {
        return plugin.isTileInInteractive(tileState);
    }

    public static void disableInteractiveObj(NamespacedKey key) {
        plugin.disableInteractiveObj(key);
    }

    public static void disableInteractiveObj(InteractiveObj<?> interactiveObj) {
        plugin.disableInteractiveObj(interactiveObj);
    }

    private A_() {
        throw new IllegalStateException("Utility class");
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

    public static void registerInteractiveObj(InteractiveObj<?> interactiveObj) {
        plugin.registerInteractiveObj(interactiveObj);
    }

    public static <T> void setInteractiveInObj(InteractiveObj<T> interactiveObj, T obj) {
        plugin.setInteractiveInObj(interactiveObj, obj);
    }

    public static <T> void setInteractiveInObj(NamespacedKey key, T obj) {
        plugin.setInteractiveInObj(key, obj);
    }

    public static boolean isItemInInteractive(ItemStack itemStack) {
        return plugin.isItemInInteractive(itemStack);
    }

    public static boolean isItemInTile(TileState tileState) {
        return plugin.isTileInInteractive(tileState);
    }

    public static boolean isRegisteredInteractiveObj(NamespacedKey key) {
        return plugin.isRegisteredInteractiveObj(key);
    }

    public static InteractiveItem getInteractiveInItem(ItemStack itemStack) {
        return plugin.getInteractiveInItem(itemStack);
    }

    public static InteractiveTile getInteractiveInBlock(TileState tileState) {
        return plugin.getInteractiveInBlock(tileState);
    }

    public static InteractiveObj<?> getInteractiveObj(NamespacedKey key) {
        return plugin.getInteractiveObj(key);
    }

    public static void runInteractiveEvent(InteractiveObj<?> obj, Class<? extends InteractiveAct> act, A_Player player, Event event) {
        plugin.runInteractiveEvent(obj, act, player, event);
    }

    public static void canRunInteractiveTileEvent(BlockState blockState, Class<? extends InteractiveTileAct> act, A_Player player, Event event) {
        plugin.canRunInteractiveTileEvent(blockState, act, player, event);
    }

    public static void canRunInteractiveItemEvent(ItemStack itemStack, Class<? extends InteractiveItemAct> act, A_Player player, Event event) {
        plugin.canRunInteractiveItemEvent(itemStack, act, player, event);
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

    public static EconomyAccount createEmptyEconomyAccount() {
        return plugin.createEmptyEconomyAccount();
    }

    public static A_World getAWorld(String worldName) {
        return plugin.getAWorld(worldName);
    }

    public static A_World getAWorld(World world) {
        return plugin.getAWorld(world);
    }
}
