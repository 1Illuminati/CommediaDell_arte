package org.red.library.a_.world;

import org.bukkit.*;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.boss.DragonBattle;
import org.bukkit.entity.*;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Consumer;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.red.library.a_.A_DataHolder;
import org.red.library.block.loot.LootChest;
import org.red.library.item.material.HasMaterialBan;
import org.red.library.world.Area;
import org.red.library.world.rule.HasRule;
import org.red.library.world.rule.Rule;

import java.io.File;
import java.util.*;
import java.util.function.Predicate;

public interface A_World extends A_DataHolder, HasMaterialBan, HasRule {
    World getWorld();

    Area getArea(NamespacedKey key);

    void putArea(Area area);

    void removeArea(Area area);

    void removeArea(NamespacedKey key);

    Collection<LootChest> getLootChests();
    void registeredLootChest(LootChest lootChest);
    boolean isLootChest(Location location);
    LootChest getLootChest(Location location);
    LootChest getLootChest(String name);
    void removeLootChest(LootChest lootChest);
    void removeLootChest(String name);
    Collection<Area> getAreas();
    Set<Area> getContainAreas(Vector... vectors);
    Set<Area> getContainAreas(Location... locs);
    Set<Area> getContainAreas(Vector start, Vector end);
    Set<Area> getContainAreas(Location start, Location end);
    Set<Area> getContainAreas(BoundingBox boundingBox);
    Set<Area> getContainAreas(Area area);
    Set<Area> getOverlapAreas(Vector start, Vector end);
    Set<Area> getOverlapAreas(Location start, Location end);
    Set<Area> getOverlapAreas(BoundingBox boundingBox);
    Set<Area> getOverlapAreas(Area area);

    <T> T getRuleValue(Rule<T> rule, Location... locs);

    void aDataSave();

    void aDataLoad();

    void sendPluginMessage(@NotNull Plugin var1, @NotNull String var2, @NotNull byte[] var3);

    @NotNull
    Set<String> getListeningPluginChannels();

    void setMetadata(@NotNull String var1, @NotNull MetadataValue var2);

    @NotNull
    List<MetadataValue> getMetadata(@NotNull String var1);

    boolean hasMetadata(@NotNull String var1);

    void removeMetadata(@NotNull String var1, @NotNull Plugin var2);

    Block getBlockAt(int var1, int var2, int var3);

    @NotNull
    Block getBlockAt(@NotNull Location var1);

    int getHighestBlockYAt(int var1, int var2);

    int getHighestBlockYAt(@NotNull Location var1);

    @NotNull
    Block getHighestBlockAt(int var1, int var2);

    @NotNull
    Block getHighestBlockAt(@NotNull Location var1);

    int getHighestBlockYAt(int var1, int var2, @NotNull HeightMap var3);

    int getHighestBlockYAt(@NotNull Location var1, @NotNull HeightMap var2);

    @NotNull
    Block getHighestBlockAt(int var1, int var2, @NotNull HeightMap var3);

    @NotNull
    Block getHighestBlockAt(@NotNull Location var1, @NotNull HeightMap var2);

    @NotNull
    Chunk getChunkAt(int var1, int var2);

    @NotNull
    Chunk getChunkAt(@NotNull Location var1);

    @NotNull
    Chunk getChunkAt(@NotNull Block var1);

    boolean isChunkLoaded(@NotNull Chunk var1);

    @NotNull
    Chunk[] getLoadedChunks();

    void loadChunk(@NotNull Chunk var1);

    boolean isChunkLoaded(int var1, int var2);

    boolean isChunkGenerated(int var1, int var2);

    /** @deprecated */
    @Deprecated
    boolean isChunkInUse(int var1, int var2);

    void loadChunk(int var1, int var2);

    boolean loadChunk(int var1, int var2, boolean var3);

    boolean unloadChunk(@NotNull Chunk var1);

    boolean unloadChunk(int var1, int var2);

    boolean unloadChunk(int var1, int var2, boolean var3);

    boolean unloadChunkRequest(int var1, int var2);

    /** @deprecated */
    @Deprecated
    boolean regenerateChunk(int var1, int var2);

    /** @deprecated */
    @Deprecated
    boolean refreshChunk(int var1, int var2);

    boolean isChunkForceLoaded(int var1, int var2);

    void setChunkForceLoaded(int var1, int var2, boolean var3);

    @NotNull
    Collection<Chunk> getForceLoadedChunks();

    boolean addPluginChunkTicket(int var1, int var2, @NotNull Plugin var3);

    boolean removePluginChunkTicket(int var1, int var2, @NotNull Plugin var3);

    void removePluginChunkTickets(@NotNull Plugin var1);

    @NotNull
    Collection<Plugin> getPluginChunkTickets(int var1, int var2);

    @NotNull
    Map<Plugin, Collection<Chunk>> getPluginChunkTickets();

    @NotNull
    Item dropItem(@NotNull Location var1, @NotNull ItemStack var2);

    @NotNull
    Item dropItem(@NotNull Location var1, @NotNull ItemStack var2, @Nullable Consumer<Item> var3);

    @NotNull
    Item dropItemNaturally(@NotNull Location var1, @NotNull ItemStack var2);

    @NotNull
    Item dropItemNaturally(@NotNull Location var1, @NotNull ItemStack var2, @Nullable Consumer<Item> var3);

    @NotNull
    Arrow spawnArrow(@NotNull Location var1, @NotNull Vector var2, float var3, float var4);

    @NotNull
    <T extends AbstractArrow> T spawnArrow(@NotNull Location var1, @NotNull Vector var2, float var3, float var4, @NotNull Class<T> var5);

    boolean generateTree(@NotNull Location var1, @NotNull TreeType var2);

    boolean generateTree(@NotNull Location var1, @NotNull TreeType var2, @NotNull BlockChangeDelegate var3);

    @NotNull
    Entity spawnEntity(@NotNull Location var1, @NotNull EntityType var2);

    @NotNull
    LightningStrike strikeLightning(@NotNull Location var1);

    @NotNull
    LightningStrike strikeLightningEffect(@NotNull Location var1);

    @NotNull
    List<Entity> getEntities();

    @NotNull
    List<LivingEntity> getLivingEntities();

    /** @deprecated */
    @Deprecated
    @NotNull
    <T extends Entity> Collection<T> getEntitiesByClass(@NotNull Class<T>... var1);

    @NotNull
    <T extends Entity> Collection<T> getEntitiesByClass(@NotNull Class<T> var1);

    @NotNull
    Collection<Entity> getEntitiesByClasses(@NotNull Class<?>... var1);

    @NotNull
    List<Player> getPlayers();

    @NotNull
    Collection<Entity> getNearbyEntities(@NotNull Location var1, double var2, double var4, double var6);

    @NotNull
    Collection<Entity> getNearbyEntities(@NotNull Location var1, double var2, double var4, double var6, @Nullable Predicate<Entity> var8);

    @NotNull
    Collection<Entity> getNearbyEntities(@NotNull BoundingBox var1);

    @NotNull
    Collection<Entity> getNearbyEntities(@NotNull BoundingBox var1, @Nullable Predicate<Entity> var2);

    @Nullable
    RayTraceResult rayTraceEntities(@NotNull Location var1, @NotNull Vector var2, double var3);

    @Nullable
    RayTraceResult rayTraceEntities(@NotNull Location var1, @NotNull Vector var2, double var3, double var5);

    @Nullable
    RayTraceResult rayTraceEntities(@NotNull Location var1, @NotNull Vector var2, double var3, @Nullable Predicate<Entity> var5);

    @Nullable
    RayTraceResult rayTraceEntities(@NotNull Location var1, @NotNull Vector var2, double var3, double var5, @Nullable Predicate<Entity> var7);

    @Nullable
    RayTraceResult rayTraceBlocks(@NotNull Location var1, @NotNull Vector var2, double var3);

    @Nullable
    RayTraceResult rayTraceBlocks(@NotNull Location var1, @NotNull Vector var2, double var3, @NotNull FluidCollisionMode var5);

    @Nullable
    RayTraceResult rayTraceBlocks(@NotNull Location var1, @NotNull Vector var2, double var3, @NotNull FluidCollisionMode var5, boolean var6);

    @Nullable
    RayTraceResult rayTrace(@NotNull Location var1, @NotNull Vector var2, double var3, @NotNull FluidCollisionMode var5, boolean var6, double var7, @Nullable Predicate<Entity> var9);

    @NotNull
    String getName();

    @NotNull
    UUID getUID();

    @NotNull
    Location getSpawnLocation();

    boolean setSpawnLocation(@NotNull Location var1);

    boolean setSpawnLocation(int var1, int var2, int var3, float var4);

    boolean setSpawnLocation(int var1, int var2, int var3);

    long getTime();

    void setTime(long var1);

    long getFullTime();

    void setFullTime(long var1);

    long getGameTime();

    boolean hasStorm();

    void setStorm(boolean var1);

    int getWeatherDuration();

    void setWeatherDuration(int var1);

    boolean isThundering();

    void setThundering(boolean var1);

    int getThunderDuration();

    void setThunderDuration(int var1);

    boolean isClearWeather();

    void setClearWeatherDuration(int var1);

    int getClearWeatherDuration();

    boolean createExplosion(double var1, double var3, double var5, float var7);

    boolean createExplosion(double var1, double var3, double var5, float var7, boolean var8);

    boolean createExplosion(double var1, double var3, double var5, float var7, boolean var8, boolean var9);

    boolean createExplosion(double var1, double var3, double var5, float var7, boolean var8, boolean var9, @Nullable Entity var10);

    boolean createExplosion(@NotNull Location var1, float var2);

    boolean createExplosion(@NotNull Location var1, float var2, boolean var3);

    boolean createExplosion(@NotNull Location var1, float var2, boolean var3, boolean var4);

    boolean createExplosion(@NotNull Location var1, float var2, boolean var3, boolean var4, @Nullable Entity var5);

    @NotNull
    World.Environment getEnvironment();

    long getSeed();

    boolean getPVP();

    void setPVP(boolean var1);

    @Nullable
    ChunkGenerator getGenerator();

    void save();

    @NotNull
    List<BlockPopulator> getPopulators();

    @NotNull
    <T extends Entity> T spawn(@NotNull Location var1, @NotNull Class<T> var2) throws IllegalArgumentException;

    @NotNull
    <T extends Entity> T spawn(@NotNull Location var1, @NotNull Class<T> var2, @Nullable Consumer<T> var3) throws IllegalArgumentException;

    @NotNull
    FallingBlock spawnFallingBlock(@NotNull Location var1, @NotNull MaterialData var2) throws IllegalArgumentException;

    @NotNull
    FallingBlock spawnFallingBlock(@NotNull Location var1, @NotNull BlockData var2) throws IllegalArgumentException;

    /** @deprecated */
    @Deprecated
    @NotNull
    FallingBlock spawnFallingBlock(@NotNull Location var1, @NotNull Material var2, byte var3) throws IllegalArgumentException;

    void playEffect(@NotNull Location var1, @NotNull Effect var2, int var3);

    void playEffect(@NotNull Location var1, @NotNull Effect var2, int var3, int var4);

    <T> void playEffect(@NotNull Location var1, @NotNull Effect var2, @Nullable T var3);

    <T> void playEffect(@NotNull Location var1, @NotNull Effect var2, @Nullable T var3, int var4);

    @NotNull
    ChunkSnapshot getEmptyChunkSnapshot(int var1, int var2, boolean var3, boolean var4);

    void setSpawnFlags(boolean var1, boolean var2);

    boolean getAllowAnimals();

    boolean getAllowMonsters();

    /** @deprecated */
    @Deprecated
    @NotNull
    Biome getBiome(int var1, int var2);

    @NotNull
    Biome getBiome(int var1, int var2, int var3);

    /** @deprecated */
    @Deprecated
    void setBiome(int var1, int var2, @NotNull Biome var3);

    void setBiome(int var1, int var2, int var3, @NotNull Biome var4);

    /** @deprecated */
    @Deprecated
    double getTemperature(int var1, int var2);

    double getTemperature(int var1, int var2, int var3);

    /** @deprecated */
    @Deprecated
    double getHumidity(int var1, int var2);

    double getHumidity(int var1, int var2, int var3);

    int getMinHeight();

    int getMaxHeight();

    int getSeaLevel();

    boolean getKeepSpawnInMemory();

    void setKeepSpawnInMemory(boolean var1);

    boolean isAutoSave();

    void setAutoSave(boolean var1);

    void setDifficulty(@NotNull Difficulty var1);

    @NotNull
    Difficulty getDifficulty();

    @NotNull
    File getWorldFolder();

    /** @deprecated */
    @Deprecated
    @Nullable
    WorldType getWorldType();

    boolean canGenerateStructures();

    boolean isHardcore();

    void setHardcore(boolean var1);

    long getTicksPerAnimalSpawns();

    void setTicksPerAnimalSpawns(int var1);

    long getTicksPerMonsterSpawns();

    void setTicksPerMonsterSpawns(int var1);

    long getTicksPerWaterSpawns();

    void setTicksPerWaterSpawns(int var1);

    long getTicksPerWaterAmbientSpawns();

    void setTicksPerWaterAmbientSpawns(int var1);

    long getTicksPerAmbientSpawns();

    void setTicksPerAmbientSpawns(int var1);

    int getMonsterSpawnLimit();

    void setMonsterSpawnLimit(int var1);

    int getAnimalSpawnLimit();

    void setAnimalSpawnLimit(int var1);

    int getWaterAnimalSpawnLimit();

    void setWaterAnimalSpawnLimit(int var1);

    int getWaterAmbientSpawnLimit();

    void setWaterAmbientSpawnLimit(int var1);

    int getAmbientSpawnLimit();

    void setAmbientSpawnLimit(int var1);

    void playSound(@NotNull Location var1, @NotNull Sound var2, float var3, float var4);

    void playSound(@NotNull Location var1, @NotNull String var2, float var3, float var4);

    void playSound(@NotNull Location var1, @NotNull Sound var2, @NotNull SoundCategory var3, float var4, float var5);

    void playSound(@NotNull Location var1, @NotNull String var2, @NotNull SoundCategory var3, float var4, float var5);

    @NotNull
    String[] getGameRules();

    /** @deprecated */
    @Deprecated
    @Contract("null -> null; !null -> !null")
    @Nullable
    String getGameRuleValue(@Nullable String var1);

    /** @deprecated */
    @Deprecated
    boolean setGameRuleValue(@NotNull String var1, @NotNull String var2);

    boolean isGameRule(@NotNull String var1);

    @Nullable
    <T> T getGameRuleValue(@NotNull GameRule<T> var1);

    @Nullable
    <T> T getGameRuleDefault(@NotNull GameRule<T> var1);

    <T> boolean setGameRule(@NotNull GameRule<T> var1, @NotNull T var2);

    @NotNull
    WorldBorder getWorldBorder();

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

    <T> void spawnParticle(@NotNull Particle var1, @NotNull Location var2, int var3, double var4, double var6, double var8, double var10, @Nullable T var12, boolean var13);

    <T> void spawnParticle(@NotNull Particle var1, double var2, double var4, double var6, int var8, double var9, double var11, double var13, double var15, @Nullable T var17, boolean var18);

    @Nullable
    Location locateNearestStructure(@NotNull Location var1, @NotNull StructureType var2, int var3, boolean var4);

    int getViewDistance();

    @NotNull
    World.Spigot spigot();

    @Nullable
    Raid locateNearestRaid(@NotNull Location var1, int var2);

    @NotNull
    List<Raid> getRaids();

    @Nullable
    DragonBattle getEnderDragonBattle();
}
