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
import org.red.library.interactive.item.InteractiveItem;
import org.red.library.item.shop.ShopItem;
import org.red.library.item.shop.price.Price;
import org.red.library.util.timer.BossBarTimer;
import org.red.library.util.timer.Timer;

import java.util.UUID;

public interface A_Manager {
    void registerEventItem(InteractiveItem interactiveItem);

    void setItemInEvent(InteractiveItem interactiveItem, ItemStack itemStack);

    void setItemInEvent(NamespacedKey eventItemKey, ItemStack itemStack);

    boolean isItemInEvent(ItemStack itemStack);

    InteractiveItem getEventInItem(ItemStack itemStack);

    Timer createTimer(NamespacedKey key, int maxTime);

    BossBarTimer createBossBarTimer(NamespacedKey key, int maxTime, BossBar... bossBars);

    ShopItem createBuyShopItem(ItemStack originItem, Price buyPrice);

    ShopItem createSellShopItem(ItemStack originItem, Price sellPrice);

    ShopItem createBothShopItem(ItemStack originItem, Price buyPrice, Price sellPrice);

    A_Player getAPlayer(Player player);

    A_Player getAPlayer(String playerName);

    A_Player getAPlayer(UUID uuid);

    A_OfflinePlayer getAOfflinePlayer(String playerName);

    A_OfflinePlayer getAOfflinePlayer(UUID uuid);

    A_OfflinePlayer getAOfflinePlayer(OfflinePlayer offlinePlayer);

    A_LivingEntity getALivingEntity(LivingEntity livingEntity);

    A_Entity getAEntity(Entity entity);

    A_Entity getAEntity(UUID uuid);

    A_World getAWorld(String worldName);

    A_World getAWorld(World world);
}
