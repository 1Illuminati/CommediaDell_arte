package org.red.a_.entity;

import org.bukkit.*;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.red.CommediaDell_arte;
import org.red.a_.A_ManagerImpl;
import org.red.a_.util.A_File;
import org.red.a_.util.A_YamlConfiguration;
import org.red.library.A_;
import org.red.library.a_.A_Data;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.a_.entity.player.offline.A_OfflinePlayer;
import org.red.library.util.map.CoolTime;
import org.red.library.util.map.DataMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

public class A_OfflinePlayerImpl implements A_OfflinePlayer {
    private OfflinePlayer offlinePlayer;
    private final A_Data aData = A_Data.newAData();
    private final A_ManagerImpl.A_Version aVersion;

    public A_OfflinePlayerImpl(OfflinePlayer offlinePlayer, A_ManagerImpl.A_Version aVersion) {
        this.offlinePlayer = offlinePlayer;
        this.aVersion = aVersion;
        this.aDataLoad();
    }

    public A_OfflinePlayerImpl updateOfflinePlayer() {
        this.offlinePlayer = Bukkit.getOfflinePlayer(this.getUniqueId());
        return this;
    }

    @Override
    public DataMap getDataMap() {
        return this.aData.getDataMap(CommediaDell_arte.getPlugin());
    }

    @Override
    public DataMap getDataMap(Plugin plugin) {
        return this.aData.getDataMap(plugin);
    }

    @Override
    public CoolTime getCoolTime() {
        return this.aData.getCoolTime(CommediaDell_arte.getPlugin());
    }

    @Override
    public CoolTime getCoolTime(Plugin plugin) {
        return this.aData.getCoolTime(plugin);
    }

    @Override
    public A_Data getAData() {
        return this.aData;
    }

    @Override
    public boolean isOnline() {
        return offlinePlayer.isOnline();
    }

    @Override
    @Nullable
    public String getName() {
        return offlinePlayer.getName();
    }

    @Override
    @NotNull
    public UUID getUniqueId() {
        return offlinePlayer.getUniqueId();
    }

    @Override
    public boolean isBanned() {
        return offlinePlayer.isBanned();
    }

    @Override
    public boolean isWhitelisted() {
        return offlinePlayer.isWhitelisted();
    }

    @Override
    public void setWhitelisted(boolean b) {
        offlinePlayer.setWhitelisted(b);
    }

    @Override
    @Nullable
    public Player getPlayer() {
        return offlinePlayer.getPlayer();
    }

    @Override
    public @Nullable A_Player getAPlayer() {
        return isOnline() ? A_.getAPlayer(this.getUniqueId()) : null;
    }

    @Override
    public long getFirstPlayed() {
        return offlinePlayer.getFirstPlayed();
    }

    @Override
    public long getLastPlayed() {
        return offlinePlayer.getLastPlayed();
    }

    @Override
    public boolean hasPlayedBefore() {
        return offlinePlayer.hasPlayedBefore();
    }

    @Override
    @Nullable
    public Location getBedSpawnLocation() {
        return offlinePlayer.getBedSpawnLocation();
    }

    @Override
    public void incrementStatistic(@NotNull Statistic statistic) throws IllegalArgumentException {
        offlinePlayer.incrementStatistic(statistic);
    }

    @Override
    public void decrementStatistic(@NotNull Statistic statistic) throws IllegalArgumentException {
        offlinePlayer.decrementStatistic(statistic);
    }

    @Override
    public void incrementStatistic(@NotNull Statistic statistic, int i) throws IllegalArgumentException {
        offlinePlayer.incrementStatistic(statistic, i);
    }

    @Override
    public void decrementStatistic(@NotNull Statistic statistic, int i) throws IllegalArgumentException {
        offlinePlayer.decrementStatistic(statistic, i);
    }

    @Override
    public void setStatistic(@NotNull Statistic statistic, int i) throws IllegalArgumentException {
        offlinePlayer.setStatistic(statistic, i);
    }

    @Override
    public int getStatistic(@NotNull Statistic statistic) throws IllegalArgumentException {
        return offlinePlayer.getStatistic(statistic);
    }

    @Override
    public void incrementStatistic(@NotNull Statistic statistic, @NotNull Material material) throws IllegalArgumentException {
        offlinePlayer.incrementStatistic(statistic, material);
    }

    @Override
    public void decrementStatistic(@NotNull Statistic statistic, @NotNull Material material) throws IllegalArgumentException {
        offlinePlayer.decrementStatistic(statistic, material);
    }

    @Override
    public int getStatistic(@NotNull Statistic statistic, @NotNull Material material) throws IllegalArgumentException {
        return offlinePlayer.getStatistic(statistic, material);
    }

    @Override
    public void incrementStatistic(@NotNull Statistic statistic, @NotNull Material material, int i) throws IllegalArgumentException {
        offlinePlayer.incrementStatistic(statistic, material, i);
    }

    @Override
    public void decrementStatistic(@NotNull Statistic statistic, @NotNull Material material, int i) throws IllegalArgumentException {
        offlinePlayer.decrementStatistic(statistic, material, i);
    }

    @Override
    public void setStatistic(@NotNull Statistic statistic, @NotNull Material material, int i) throws IllegalArgumentException {
        offlinePlayer.setStatistic(statistic, material, i);
    }

    @Override
    public void incrementStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType) throws IllegalArgumentException {
        offlinePlayer.incrementStatistic(statistic, entityType);
    }

    @Override
    public void decrementStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType) throws IllegalArgumentException {
        offlinePlayer.decrementStatistic(statistic, entityType);
    }

    @Override
    public int getStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType) throws IllegalArgumentException {
        return offlinePlayer.getStatistic(statistic, entityType);
    }

    @Override
    public void incrementStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType, int i) throws IllegalArgumentException {
        offlinePlayer.incrementStatistic(statistic, entityType, i);
    }

    @Override
    public void decrementStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType, int i) {
        offlinePlayer.decrementStatistic(statistic, entityType, i);
    }

    @Override
    public void setStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType, int i) {
        offlinePlayer.setStatistic(statistic, entityType, i);
    }

    @Override
    public ItemStack getPlayerSkull() {
        ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
        skullMeta.setOwningPlayer(this.offlinePlayer);
        itemStack.setItemMeta(skullMeta);
        return itemStack;
    }

    @Override
    public void aDataSave() {
        A_YamlConfiguration fileConfiguration = new A_YamlConfiguration();
        fileConfiguration.set("aData", this.aData);
        fileConfiguration.save(new A_File("playerData/" + this.getUniqueId() + ".yml"));
        CommediaDell_arte.sendDebugLog("§aSave PlayerData: " + this.getUniqueId());
    }

    @Override
    public void aDataLoad() {
        A_YamlConfiguration fileConfiguration = new A_YamlConfiguration();
        fileConfiguration.load(new A_File("playerData/" + this.getUniqueId() + ".yml"));
        A_Data aData = (A_Data) fileConfiguration.get("aData");
        if (aData != null) this.aData.copy(aData);
        CommediaDell_arte.sendDebugLog("§aLoad PlayerData: " + this.getUniqueId());
    }

    @Override
    public boolean isOp() {
        return offlinePlayer.isOp();
    }

    @Override
    public void setOp(boolean b) {
        offlinePlayer.setOp(b);
    }
}
