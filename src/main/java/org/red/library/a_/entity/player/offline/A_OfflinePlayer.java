package org.red.library.a_.entity.player.offline;

import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.red.library.a_.A_DataHolder;
import org.red.library.a_.entity.player.A_Player;

import java.util.UUID;

public interface A_OfflinePlayer extends A_DataHolder {

    ItemStack getPlayerSkull();

    void aDataSave();

    void aDataLoad();

    boolean isOp();

    void setOp(boolean var1);

    boolean isOnline();

    @Nullable
    String getName();

    @NotNull
    UUID getUniqueId();

    boolean isBanned();

    boolean isWhitelisted();

    void setWhitelisted(boolean var1);

    @Nullable
    Player getPlayer();

    @Nullable A_Player getAPlayer();

    long getFirstPlayed();

    long getLastPlayed();

    boolean hasPlayedBefore();

    @Nullable
    Location getBedSpawnLocation();

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
}
