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
import org.red.library.a_.entity.A_Entity;
import org.red.library.a_.entity.A_LivingEntity;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.a_.entity.player.offline.A_OfflinePlayer;
import org.red.library.a_.world.A_World;
import org.red.library.interactive.InteractiveAct;
import org.red.library.interactive.InteractiveObj;
import org.red.library.interactive.block.InteractiveTileAct;
import org.red.library.interactive.block.InteractiveTile;
import org.red.library.interactive.item.InteractiveItem;
import org.red.library.interactive.item.InteractiveItemAct;
import org.red.library.item.randombox.RandomBox;
import org.red.library.item.shop.ShopItem;
import org.red.library.item.shop.price.Price;
import org.red.library.util.timer.BossBarTimer;
import org.red.library.util.timer.Timer;

import java.util.List;
import java.util.UUID;

public interface A_Manager {
    RandomBox createRandomBox(String name, List<ItemStack> items);
    RandomBox createRandomBox(String name, ItemStack... items);
    void registerInteractiveObj(InteractiveObj<?> interactiveObj);

    <T> void setInteractiveInObj(InteractiveObj<T> interactiveObj, T obj);

    <T> void setInteractiveInObj(NamespacedKey key, T obj);

    boolean isItemInInteractive(ItemStack itemStack);

    boolean isTileInInteractive(TileState tileState);

    boolean isRegisteredInteractiveObj(NamespacedKey key);

    InteractiveItem getInteractiveInItem(ItemStack itemStack);

    InteractiveTile getInteractiveInBlock(TileState tileState);

    InteractiveObj<?> getInteractiveObj(NamespacedKey key);

    void runInteractiveEvent(InteractiveObj<?> obj, Class<? extends InteractiveAct> act, A_Player player, Event event);

    void canRunInteractiveTileEvent(BlockState blockState, Class<? extends InteractiveTileAct> act, A_Player player, Event event);

    void canRunInteractiveItemEvent(ItemStack itemStack, Class<? extends InteractiveItemAct> act, A_Player player, Event event);
    void disableInteractiveObj(NamespacedKey key);
    void disableInteractiveObj(InteractiveObj<?> interactiveObj);

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
