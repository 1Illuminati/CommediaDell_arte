package org.red.a_.entity;

import org.bukkit.*;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.red.CommediaDell_arte;
import org.red.library.A_;
import org.red.library.a_.A_Data;
import org.red.a_.A_ManagerImpl;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.a_.entity.player.offline.A_OfflinePlayer;

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
        ItemStack itemStack = new ItemStack(org.bukkit.Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
        skullMeta.setOwningPlayer(this.offlinePlayer);
        itemStack.setItemMeta(skullMeta);
        return itemStack;
    }

    @Override
    public void aDataSave() {
        FileConfiguration fileConfiguration = new YamlConfiguration();
        fileConfiguration.set("aData", this.aData);


        File file = new File("plugins/Dell_arte/playerData/" + this.getUniqueId() + ".yml");

        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        CommediaDell_arte.sendLog("§aSave PlayerData: " + this.getUniqueId());
    }

    @Override
    public void aDataLoad() {
        FileConfiguration fileConfiguration = new YamlConfiguration();
        File file = new File("plugins/Dell_arte/playerData/" + this.getUniqueId() + ".yml");

        try {
            fileConfiguration.load(file);
        }  catch (IOException | InvalidConfigurationException e) {
            if (e instanceof FileNotFoundException) CommediaDell_arte.sendLog("§cNot Found PlayerData: " + this.getUniqueId());
            else e.printStackTrace();

            return;
        }

        A_Data aData = (A_Data) fileConfiguration.get("aData");
        if (aData != null) this.aData.copy(aData);

        CommediaDell_arte.sendLog("§aLoad PlayerData: " + this.getUniqueId());
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
