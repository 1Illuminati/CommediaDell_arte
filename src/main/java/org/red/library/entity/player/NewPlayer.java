package org.red.library.entity.player;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.red.library.CommediaDell_arte;
import org.red.library.entity.NewLivingEntity;
import org.red.library.entity.player.npc.NpcPlayer;
import org.red.library.entity.player.offline.NewOfflinePlayer;
import org.red.library.inventory.CustomGui;
import org.red.library.util.map.CoolTime;
import org.red.library.util.map.DataMap;

import java.util.*;

public class NewPlayer extends NewLivingEntity {
    private static final HashMap<UUID, NewPlayer> newPlayerMap = new HashMap<>();

    public static NewPlayer getNewPlayer(UUID playerUUID) {
        Player player = Bukkit.getPlayer(playerUUID);

        if (player == null)
            return null;

        return NewPlayer.getNewPlayer(player);
    }

    public static NewPlayer getNewPlayer(String playerName) {
        Player player = Bukkit.getPlayer(playerName);

        if (player == null)
            return null;

        return NewPlayer.getNewPlayer(player);
    }

    public static NewPlayer getNewPlayer(Player player) {
        if (player.hasMetadata("NPC")) {
            return NpcPlayer.getNPCPlayer(player);
        }

        if (!newPlayerMap.containsKey(player.getUniqueId()))
            newPlayerMap.put(player.getUniqueId(), new NewPlayer(player));
        else if (!newPlayerMap.get(player.getUniqueId()).getPlayer().equals(player))
            newPlayerMap.put(player.getUniqueId(), new NewPlayer(player));

        return newPlayerMap.get(player.getUniqueId());
    }
    private final Player player;
    private final NewOfflinePlayer newOfflinePlayer;
    private final PlayerData playerData;
    private boolean ignoreCloseEvent = false;
    protected NewPlayer(Player player) {
        super(player);
        this.player = player;
        this.newOfflinePlayer = NewOfflinePlayer.getNewOfflinePlayer(player);
        this.playerData = newOfflinePlayer.getPlayerData();
    }

    public void openInventory(CustomGui gui) {
        this.openInventory(gui.getInventory());
    }

    public void delayOpenInventory(CustomGui gui) {
        this.delayOpenInventory(gui.getInventory(), false);
    }

    public void delayOpenInventory(CustomGui gui, boolean ignoreCloseEvent) {
        this.delayOpenInventory(gui.getInventory(), ignoreCloseEvent);
    }

    public void delayOpenInventory(Inventory inv) {
        this.delayOpenInventory(inv, false);
    }

    public void delayOpenInventory(Inventory inv, boolean ignoreCloseEvent) {
        Bukkit.getScheduler().runTaskLater(CommediaDell_arte.getPlugin(), () -> {
            openInventory(inv);

            if (ignoreCloseEvent)
                setIgnoreCloseEvent(true);
        }, 2L);
    }

    public boolean isIgnoreCloseEvent() {
        return ignoreCloseEvent;
    }

    public void setIgnoreCloseEvent(boolean ignoreCloseEvent) {
        this.ignoreCloseEvent = ignoreCloseEvent;
    }

    public NewOfflinePlayer getNewOfflinePlayer() {
        return newOfflinePlayer;
    }

    public DataMap getDataMap() {
        return newOfflinePlayer.getDataMap();
    }

    public CoolTime getCoolTime() {
        return newOfflinePlayer.getCoolTime();
    }

    public PlayerData getPlayerData() {
        return playerData;
    }

    public boolean isClown() {
        return newOfflinePlayer.isClown();
    }

    public boolean isNpc() {
        return false;
    }

    public void sendActionBar(String message) {
        this.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public HumanEntity getEntity() {
        return player;
    }

    public PlayerInventory getInventory() {
        return player.getInventory();
    }

    public Inventory getEnderChest() {
        return player.getEnderChest();
    }

    public MainHand getMainHand() {
        return player.getMainHand();
    }

    public boolean setWindowProperty(InventoryView.Property property, int i) {
        return player.setWindowProperty(property, i);
    }

    public InventoryView getOpenInventory() {
        return player.getOpenInventory();
    }

    public InventoryView openInventory(Inventory inventory) {
        return player.openInventory(inventory);
    }

    public InventoryView openWorkbench(Location location, boolean b) {
        return player.openWorkbench(location, b);
    }

    public InventoryView openEnchanting(Location location, boolean b) {
        return player.openEnchanting(location, b);
    }

    public void openInventory(InventoryView inventoryView) {
        player.openInventory(inventoryView);
    }

    public InventoryView openMerchant(Villager villager, boolean b) {
        return player.openMerchant(villager, b);
    }

    public InventoryView openMerchant(Merchant merchant, boolean b) {
        return player.openMerchant(merchant, b);
    }

    public void closeInventory() {
        player.closeInventory();
    }

    @Deprecated
    public ItemStack getItemInHand() {
        return player.getItemInHand();
    }

    @Deprecated
    public void setItemInHand(ItemStack itemStack) {
        player.setItemInHand(itemStack);
    }

    public ItemStack getItemOnCursor() {
        return player.getItemOnCursor();
    }

    public void setItemOnCursor(ItemStack itemStack) {
        player.setItemOnCursor(itemStack);
    }

    public boolean hasCooldown(Material material) {
        return player.hasCooldown(material);
    }

    public int getCooldown(Material material) {
        return player.getCooldown(material);
    }

    public void setCooldown(Material material, int i) {
        player.setCooldown(material, i);
    }

    public int getSleepTicks() {
        return player.getSleepTicks();
    }

    public boolean sleep(Location location, boolean b) {
        return player.sleep(location, b);
    }

    public void wakeup(boolean b) {
        player.wakeup(b);
    }

    public Location getBedLocation() {
        return player.getBedLocation();
    }

    public GameMode getGameMode() {
        return player.getGameMode();
    }

    public void setGameMode(GameMode gameMode) {
        player.setGameMode(gameMode);
    }

    public boolean isBlocking() {
        return player.isBlocking();
    }

    public boolean isHandRaised() {
        return player.isHandRaised();
    }

    public int getExpToLevel() {
        return player.getExpToLevel();
    }

    public float getAttackCooldown() {
        return player.getAttackCooldown();
    }

    public boolean discoverRecipe(NamespacedKey namespacedKey) {
        return player.discoverRecipe(namespacedKey);
    }

    public int discoverRecipes(Collection<NamespacedKey> collection) {
        return player.discoverRecipes(collection);
    }

    public boolean undiscoverRecipe(NamespacedKey namespacedKey) {
        return player.undiscoverRecipe(namespacedKey);
    }

    public int undiscoverRecipes(Collection<NamespacedKey> collection) {
        return player.undiscoverRecipes(collection);
    }

    public boolean hasDiscoveredRecipe(NamespacedKey namespacedKey) {
        return player.hasDiscoveredRecipe(namespacedKey);
    }

    public Set<NamespacedKey> getDiscoveredRecipes() {
        return player.getDiscoveredRecipes();
    }

    @Deprecated
    public Entity getShoulderEntityLeft() {
        return player.getShoulderEntityLeft();
    }

    @Deprecated
    public void setShoulderEntityLeft(Entity entity) {
        player.setShoulderEntityLeft(entity);
    }

    @Deprecated
    public Entity getShoulderEntityRight() {
        return player.getShoulderEntityRight();
    }

    @Deprecated
    public void setShoulderEntityRight(Entity entity) {
        player.setShoulderEntityRight(entity);
    }

    public boolean dropItem(boolean b) {
        return player.dropItem(b);
    }

    public float getExhaustion() {
        return player.getExhaustion();
    }

    public void setExhaustion(float v) {
        player.setExhaustion(v);
    }

    public float getSaturation() {
        return player.getSaturation();
    }

    public void setSaturation(float v) {
        player.setSaturation(v);
    }

    public int getFoodLevel() {
        return player.getFoodLevel();
    }

    public void setFoodLevel(int i) {
        player.setFoodLevel(i);
    }

    public int getSaturatedRegenRate() {
        return player.getSaturatedRegenRate();
    }

    public void setSaturatedRegenRate(int i) {
        player.setSaturatedRegenRate(i);
    }

    public int getUnsaturatedRegenRate() {
        return player.getUnsaturatedRegenRate();
    }

    public void setUnsaturatedRegenRate(int i) {
        player.setUnsaturatedRegenRate(i);
    }

    public int getStarvationRate() {
        return player.getStarvationRate();
    }

    public void setStarvationRate(int i) {
        player.setStarvationRate(i);
    }
}
