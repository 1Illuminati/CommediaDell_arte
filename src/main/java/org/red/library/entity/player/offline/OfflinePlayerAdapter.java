package org.red.library.entity.player.offline;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

public abstract class OfflinePlayerAdapter {
    private final OfflinePlayer offlinePlayer;

    protected OfflinePlayerAdapter(OfflinePlayer offlinePlayer) {
        this.offlinePlayer = offlinePlayer;
    }

    public OfflinePlayer getOfflinePlayer() {
        return offlinePlayer;
    }

    public boolean isOnline() {
        return offlinePlayer.isOnline();
    }

    public String getName() {
        return offlinePlayer.getName();
    }

    public UUID getUniqueId() {
        return offlinePlayer.getUniqueId();
    }

    public boolean isBanned() {
        return offlinePlayer.isBanned();
    }

    public boolean isWhitelisted() {
        return offlinePlayer.isWhitelisted();
    }

    public void setWhitelisted(boolean b) {
        offlinePlayer.setWhitelisted(b);
    }

    public Player getPlayer() {
        return offlinePlayer.getPlayer();
    }

    public long getFirstPlayed() {
        return offlinePlayer.getFirstPlayed();
    }

    public long getLastPlayed() {
        return offlinePlayer.getLastPlayed();
    }

    public boolean hasPlayedBefore() {
        return offlinePlayer.hasPlayedBefore();
    }

    public Location getBedSpawnLocation() {
        return offlinePlayer.getBedSpawnLocation();
    }

    public void incrementStatistic(Statistic statistic) throws IllegalArgumentException {
        offlinePlayer.incrementStatistic(statistic);
    }

    public void decrementStatistic(Statistic statistic) throws IllegalArgumentException {
        offlinePlayer.decrementStatistic(statistic);
    }

    public void incrementStatistic(Statistic statistic, int i) throws IllegalArgumentException {
        offlinePlayer.incrementStatistic(statistic, i);
    }

    public void decrementStatistic(Statistic statistic, int i) throws IllegalArgumentException {
        offlinePlayer.decrementStatistic(statistic, i);
    }

    public void setStatistic(Statistic statistic, int i) throws IllegalArgumentException {
        offlinePlayer.setStatistic(statistic, i);
    }

    public int getStatistic(Statistic statistic) throws IllegalArgumentException {
        return offlinePlayer.getStatistic(statistic);
    }

    public void incrementStatistic(Statistic statistic, Material material) throws IllegalArgumentException {
        offlinePlayer.incrementStatistic(statistic, material);
    }

    public void decrementStatistic(Statistic statistic, Material material) throws IllegalArgumentException {
        offlinePlayer.decrementStatistic(statistic, material);
    }

    public int getStatistic(Statistic statistic, Material material) throws IllegalArgumentException {
        return offlinePlayer.getStatistic(statistic, material);
    }

    public void incrementStatistic(Statistic statistic, Material material, int i) throws IllegalArgumentException {
        offlinePlayer.incrementStatistic(statistic, material, i);
    }

    public void decrementStatistic(Statistic statistic, Material material, int i) throws IllegalArgumentException {
        offlinePlayer.decrementStatistic(statistic, material, i);
    }

    public void setStatistic(Statistic statistic, Material material, int i) throws IllegalArgumentException {
        offlinePlayer.setStatistic(statistic, material, i);
    }

    public void incrementStatistic(Statistic statistic, EntityType entityType) throws IllegalArgumentException {
        offlinePlayer.incrementStatistic(statistic, entityType);
    }

    public void decrementStatistic(Statistic statistic, EntityType entityType) throws IllegalArgumentException {
        offlinePlayer.decrementStatistic(statistic, entityType);
    }

    public int getStatistic(Statistic statistic, EntityType entityType) throws IllegalArgumentException {
        return offlinePlayer.getStatistic(statistic, entityType);
    }

    public void incrementStatistic(Statistic statistic, EntityType entityType, int i) throws IllegalArgumentException {
        offlinePlayer.incrementStatistic(statistic, entityType, i);
    }

    public void decrementStatistic(Statistic statistic, EntityType entityType, int i) {
        offlinePlayer.decrementStatistic(statistic, entityType, i);
    }

    public void setStatistic(Statistic statistic, EntityType entityType, int i) {
        offlinePlayer.setStatistic(statistic, entityType, i);
    }

    public boolean isOp() {
        return offlinePlayer.isOp();
    }

    public void setOp(boolean b) {
        offlinePlayer.setOp(b);
    }

    public Map<String, Object> serialize() {
        return offlinePlayer.serialize();
    }
}
