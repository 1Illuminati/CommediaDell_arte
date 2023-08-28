package org.red.library.entity.a_.player;

import org.bukkit.*;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.block.data.BlockData;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.*;
import org.bukkit.map.MapView;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.Scoreboard;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.red.library.a_.A_Manager;
import org.red.library.entity.a_.A_LivingEntity;
import org.red.library.entity.a_.player.offline.A_OfflinePlayer;
import org.red.library.game.Game;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

public interface A_Player extends A_LivingEntity {
    static A_Player getAPlayer(Player player) {
        return A_Manager.INSTANCE.getAPlayer(player);
    }

    static A_Player getAPlayer(String name) {
        Player player = Bukkit.getPlayer(name);
        return player == null ? null : A_Manager.INSTANCE.getAPlayer(player);
    }

    static A_Player getAPlayer(UUID uuid) {
        Player player = Bukkit.getPlayer(uuid);
        return player == null ? null : A_Manager.INSTANCE.getAPlayer(player);
    }

    void sendActionBar(@NotNull String var1);

    boolean playingGame();

    @Nullable
    Game getPlayingGame();

    void aDataSave();

    void aDataLoad();

    boolean isNPC();

    A_OfflinePlayer getAOfflinePlayer();

    boolean isOnline();

    boolean isBanned();

    boolean isWhitelisted();

    void setWhitelisted(boolean var1);

    Player getPlayer();

    A_Player getAPlayer();

    long getFirstPlayed();

    long getLastPlayed();

    boolean hasPlayedBefore();

    void incrementStatistic(@NotNull Statistic var1) throws IllegalArgumentException;

    void decrementStatistic(@NotNull Statistic var1) throws IllegalArgumentException;

    void incrementStatistic(@NotNull Statistic var1, int var2) throws IllegalArgumentException;

    void decrementStatistic(@NotNull Statistic var1, int var2) throws IllegalArgumentException;

    void setStatistic(@NotNull Statistic var1, int var2) throws IllegalArgumentException;

    int getStatistic(@NotNull Statistic var1) throws IllegalArgumentException;

    void incrementStatistic(@NotNull Statistic var1, @NotNull Material var2) throws IllegalArgumentException;

    void decrementStatistic(@NotNull Statistic var1, @NotNull Material var2) throws IllegalArgumentException;

    int getStatistic(@NotNull Statistic var1, @NotNull Material var2) throws IllegalArgumentException;

    void incrementStatistic(@NotNull Statistic var1, @NotNull Material var2, int var3) throws IllegalArgumentException;

    void decrementStatistic(@NotNull Statistic var1, @NotNull Material var2, int var3) throws IllegalArgumentException;

    void setStatistic(@NotNull Statistic var1, @NotNull Material var2, int var3) throws IllegalArgumentException;

    void incrementStatistic(@NotNull Statistic var1, @NotNull EntityType var2) throws IllegalArgumentException;

    void decrementStatistic(@NotNull Statistic var1, @NotNull EntityType var2) throws IllegalArgumentException;

    int getStatistic(@NotNull Statistic var1, @NotNull EntityType var2) throws IllegalArgumentException;

    void incrementStatistic(@NotNull Statistic var1, @NotNull EntityType var2, int var3) throws IllegalArgumentException;

    void decrementStatistic(@NotNull Statistic var1, @NotNull EntityType var2, int var3);

    void setStatistic(@NotNull Statistic var1, @NotNull EntityType var2, int var3);

    Player getEntity();

    @NotNull
    String getName();

    @NotNull
    PlayerInventory getInventory();

    @NotNull
    Inventory getEnderChest();

    @NotNull
    MainHand getMainHand();

    boolean setWindowProperty(@NotNull InventoryView.Property var1, int var2);

    @NotNull
    InventoryView getOpenInventory();

    @Nullable
    InventoryView openInventory(@NotNull Inventory var1);

    @Nullable
    InventoryView openWorkbench(@Nullable Location var1, boolean var2);

    @Nullable
    InventoryView openEnchanting(@Nullable Location var1, boolean var2);

    void openInventory(@NotNull InventoryView var1);

    @Nullable
    InventoryView openMerchant(@NotNull Villager var1, boolean var2);

    @Nullable
    InventoryView openMerchant(@NotNull Merchant var1, boolean var2);

    void closeInventory();

    @NotNull
    ItemStack getItemInHand();

    void setItemInHand(@Nullable ItemStack var1);

    @NotNull
    ItemStack getItemOnCursor();

    void setItemOnCursor(@Nullable ItemStack var1);

    boolean hasCooldown(@NotNull Material var1);

    int getCooldown(@NotNull Material var1);

    void setCooldown(@NotNull Material var1, int var2);

    int getSleepTicks();

    boolean sleep(@NotNull Location var1, boolean var2);

    void wakeup(boolean var1);

    @NotNull
    Location getBedLocation();

    @NotNull
    GameMode getGameMode();

    void setGameMode(@NotNull GameMode var1);

    boolean isBlocking();

    boolean isHandRaised();

    int getExpToLevel();

    float getAttackCooldown();

    boolean discoverRecipe(@NotNull NamespacedKey var1);

    int discoverRecipes(@NotNull Collection<NamespacedKey> var1);

    boolean undiscoverRecipe(@NotNull NamespacedKey var1);

    int undiscoverRecipes(@NotNull Collection<NamespacedKey> var1);

    boolean hasDiscoveredRecipe(@NotNull NamespacedKey var1);

    @NotNull
    Set<NamespacedKey> getDiscoveredRecipes();

    @Nullable
    Entity getShoulderEntityLeft();

    void setShoulderEntityLeft(@Nullable Entity var1);

    @Nullable
    Entity getShoulderEntityRight();

    void setShoulderEntityRight(@Nullable Entity var1);

    boolean dropItem(boolean var1);

    float getExhaustion();

    void setExhaustion(float var1);

    float getSaturation();

    void setSaturation(float var1);

    int getFoodLevel();

    void setFoodLevel(int var1);

    int getSaturatedRegenRate();

    void setSaturatedRegenRate(int var1);

    int getUnsaturatedRegenRate();

    void setUnsaturatedRegenRate(int var1);

    int getStarvationRate();

    void setStarvationRate(int var1);

    boolean isConversing();

    void acceptConversationInput(@NotNull String var1);

    boolean beginConversation(@NotNull Conversation var1);

    void abandonConversation(@NotNull Conversation var1);

    void abandonConversation(@NotNull Conversation var1, @NotNull ConversationAbandonedEvent var2);

    void sendRawMessage(@Nullable UUID var1, @NotNull String var2);

    @NotNull
    String getDisplayName();

    void setDisplayName(@Nullable String var1);

    @NotNull
    String getPlayerListName();

    void setPlayerListName(@Nullable String var1);

    @Nullable
    String getPlayerListHeader();

    @Nullable
    String getPlayerListFooter();

    void setPlayerListHeader(@Nullable String var1);

    void setPlayerListFooter(@Nullable String var1);

    void setPlayerListHeaderFooter(@Nullable String var1, @Nullable String var2);

    void setCompassTarget(@NotNull Location var1);

    @NotNull
    Location getCompassTarget();

    @Nullable
    InetSocketAddress getAddress();

    void sendRawMessage(@NotNull String var1);

    void kickPlayer(@Nullable String var1);

    void chat(@NotNull String var1);

    boolean performCommand(@NotNull String var1);

    boolean isOnGround();

    boolean isSneaking();

    void setSneaking(boolean var1);

    boolean isSprinting();

    void setSprinting(boolean var1);

    void saveData();

    void loadData();

    void setSleepingIgnored(boolean var1);

    boolean isSleepingIgnored();

    @Nullable
    Location getBedSpawnLocation();

    void setBedSpawnLocation(@Nullable Location var1);

    void setBedSpawnLocation(@Nullable Location var1, boolean var2);

    void playNote(@NotNull Location var1, byte var2, byte var3);

    void playNote(@NotNull Location var1, @NotNull Instrument var2, @NotNull Note var3);

    void playSound(@NotNull Location var1, @NotNull Sound var2, float var3, float var4);

    void playSound(@NotNull Location var1, @NotNull String var2, float var3, float var4);

    void playSound(@NotNull Location var1, @NotNull Sound var2, @NotNull SoundCategory var3, float var4, float var5);

    void playSound(@NotNull Location var1, @NotNull String var2, @NotNull SoundCategory var3, float var4, float var5);

    void stopSound(@NotNull Sound var1);

    void stopSound(@NotNull String var1);

    void stopSound(@NotNull Sound var1, @Nullable SoundCategory var2);

    void stopSound(@NotNull String var1, @Nullable SoundCategory var2);

    void playEffect(@NotNull Location var1, @NotNull Effect var2, int var3);

    <T> void playEffect(@NotNull Location var1, @NotNull Effect var2, @Nullable T var3);

    void sendBlockChange(@NotNull Location var1, @NotNull Material var2, byte var3);

    void sendBlockChange(@NotNull Location var1, @NotNull BlockData var2);

    void sendBlockDamage(@NotNull Location var1, float var2);

    boolean sendChunkChange(@NotNull Location var1, int var2, int var3, int var4, @NotNull byte[] var5);

    void sendSignChange(@NotNull Location var1, @Nullable String[] var2) throws IllegalArgumentException;

    void sendSignChange(@NotNull Location var1, @Nullable String[] var2, @NotNull DyeColor var3) throws IllegalArgumentException;

    void sendMap(@NotNull MapView var1);

    void updateInventory();

    void setPlayerTime(long var1, boolean var3);

    long getPlayerTime();

    long getPlayerTimeOffset();

    boolean isPlayerTimeRelative();

    void resetPlayerTime();

    void setPlayerWeather(@NotNull WeatherType var1);

    @Nullable
    WeatherType getPlayerWeather();

    void resetPlayerWeather();

    void giveExp(int var1);

    void giveExpLevels(int var1);

    float getExp();

    void setExp(float var1);

    int getLevel();

    void setLevel(int var1);

    int getTotalExperience();

    void setTotalExperience(int var1);

    void sendExperienceChange(float var1);

    void sendExperienceChange(float var1, int var2);

    boolean getAllowFlight();

    void setAllowFlight(boolean var1);

    void hidePlayer(@NotNull Player var1);

    void hidePlayer(@NotNull Plugin var1, @NotNull Player var2);

    void showPlayer(@NotNull Player var1);

    void showPlayer(@NotNull Plugin var1, @NotNull Player var2);

    boolean canSee(@NotNull Player var1);

    boolean isFlying();

    void setFlying(boolean var1);

    void setFlySpeed(float var1) throws IllegalArgumentException;

    void setFlySpeed(double var1) throws IllegalArgumentException;

    void setWalkSpeed(float var1) throws IllegalArgumentException;

    void setWalkSpeed(double var1) throws IllegalArgumentException;

    float getFlySpeed();

    float getWalkSpeed();

    void setTexturePack(@NotNull String var1);

    void setResourcePack(@NotNull String var1);

    void setResourcePack(@NotNull String var1, @NotNull byte[] var2);

    @NotNull
    Scoreboard getScoreboard();

    void setScoreboard(@NotNull Scoreboard var1) throws IllegalArgumentException, IllegalStateException;

    boolean isHealthScaled();

    void setHealthScaled(boolean var1);

    void setHealthScale(double var1) throws IllegalArgumentException;

    double getHealthScale();

    @Nullable
    Entity getSpectatorTarget();

    void setSpectatorTarget(@Nullable Entity var1);

    void sendTitle(@Nullable String var1, @Nullable String var2);

    void sendTitle(@Nullable String var1, @Nullable String var2, int var3, int var4, int var5);

    void resetTitle();

    void spawnParticle(@NotNull Particle var1, @NotNull Location var2, int var3);

    void spawnParticle(@NotNull Particle var1, double var2, double var4, double var6, int var8);

    <T> void spawnParticle(@NotNull Particle var1, @NotNull Location var2, int var3, @Nullable T var4);

    <T> void spawnParticle(@NotNull Particle var1, double var2, double var4, double var6, int var8, @Nullable T var9);

    void spawnParticle(@NotNull Particle var1, @NotNull Location var2, int var3, double var4, double var6, double var8);

    void spawnParticle(@NotNull Particle var1, double var2, double var4, double var6, int var8, double var9, double var11, double var13);

    <T> void spawnParticle(@NotNull Particle var1, @NotNull Location var2, int var3, double var4, double var6, double var8, @Nullable T var10);

    <T> void spawnParticle(@NotNull Particle var1, double var2, double var4, double var6, int var8, double var9, double var11, double var13, @Nullable T var15);

    void spawnParticle(@NotNull Particle var1, @NotNull Location var2, int var3, double var4, double var6, double var8, double var10);

    void spawnParticle(@NotNull Particle var1, double var2, double var4, double var6, int var8, double var9, double var11, double var13, double var15);

    <T> void spawnParticle(@NotNull Particle var1, @NotNull Location var2, int var3, double var4, double var6, double var8, double var10, @Nullable T var12);

    <T> void spawnParticle(@NotNull Particle var1, double var2, double var4, double var6, int var8, double var9, double var11, double var13, double var15, @Nullable T var17);

    @NotNull
    AdvancementProgress getAdvancementProgress(@NotNull Advancement var1);

    int getClientViewDistance();

    int getPing();

    @NotNull
    String getLocale();

    void updateCommands();

    void openBook(@NotNull ItemStack var1);

    @NotNull
    Player.Spigot spigot();
}
