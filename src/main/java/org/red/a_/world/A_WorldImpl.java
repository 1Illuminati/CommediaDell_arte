package org.red.a_.world;

import org.bukkit.*;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.boss.DragonBattle;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
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
import org.red.CommediaDell_arte;
import org.red.library.a_.A_Data;
import org.red.a_.A_ManagerImpl;
import org.red.library.a_.world.A_World;
import org.red.library.util.map.NameSpaceMap;
import org.red.library.world.Area;
import org.red.library.world.rule.Rule;
import org.red.library.world.rule.RuleMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;

public class A_WorldImpl implements A_World {
    private final World world;
    private final A_Data aData = A_Data.newAData();
    private final RuleMap ruleMap = new RuleMap();
    private final NameSpaceMap<Area> areaMap = new NameSpaceMap<>();
    private final A_ManagerImpl.A_Version version;

    public A_WorldImpl(World world, A_ManagerImpl.A_Version version) {
        this.world = world;
        this.version = version;
        this.aDataLoad();
    }

    @Override
    public World getWorld() {
        return this.world;
    }

    @Override
    public Area getArea(NamespacedKey key) {
        return areaMap.getOrDefault(key, null);
    }

    @Override
    public void putArea(Area area) {
        areaMap.put(area.getKey(), area);
    }

    @Override
    public void removeArea(Area area) {
        areaMap.remove(area.getKey());
    }

    @Override
    public void removeArea(NamespacedKey key) {
        areaMap.remove(key);
    }

    @Override
    public Collection<Area> getAreas() {
        return areaMap.values();
    }

    @Override
    public Set<Area> getContainAreas(Vector... vectors) {
        List<Area> areas = new ArrayList<>();
        areaMap.values().forEach(area -> {
            for (Vector vec : vectors) if (area.contain(vec)) areas.add(area);
        });
        return new HashSet<>(areas);
    }

    @Override
    public Set<Area> getContainAreas(Location... locs) {
        List<Area> areas = new ArrayList<>();
        areaMap.values().forEach(area -> {
            for (Location loc : locs) if (area.contain(loc) || !areaMap.containsValue(area)) areas.add(area);
        });
        return new HashSet<>(areas);
    }

    @Override
    public Set<Area> getContainAreas(Vector start, Vector end) {
        return this.getContainAreas(BoundingBox.of(start, end));
    }

    @Override
    public Set<Area> getContainAreas(Location start, Location end) {
        return this.getContainAreas(BoundingBox.of(start, end));
    }

    @Override
    public Set<Area> getContainAreas(BoundingBox boundingBox) {
        List<Area> areas = new ArrayList<>();
        areaMap.values().forEach(area -> {
            if (area.contain(boundingBox)) areas.add(area);
        });
        return new HashSet<>(areas);
    }

    @Override
    public Set<Area> getContainAreas(Area area) {
        return this.getContainAreas(area.getBoundingBox());
    }

    @Override
    public Set<Area> getOverlapAreas(Vector start, Vector end) {
        return this.getOverlapAreas(BoundingBox.of(start, end));
    }

    @Override
    public Set<Area> getOverlapAreas(Location start, Location end) {
        return this.getOverlapAreas(BoundingBox.of(start, end));
    }

    @Override
    public Set<Area> getOverlapAreas(BoundingBox boundingBox) {
        List<Area> areas = new ArrayList<>();
        areaMap.values().forEach(area -> {
            if (area.overlap(boundingBox)) areas.add(area);
        });
        return new HashSet<>(areas);
    }

    @Override
    public Set<Area> getOverlapAreas(Area area) {
        return this.getOverlapAreas(area.getBoundingBox());
    }

    @Override
    public <T> T getWorldRuleValue(Rule<T> rule) {
        return this.ruleMap.get(rule);
    }

    @Override
    public <T> void setWorldRuleValue(Rule<T> rule, T value) {
        this.ruleMap.set(rule, value);
    }

    @Override
    public <T> T getRuleValue(Rule<T> rule, Location... locs) {
        T result = ruleMap.get(rule);
        int num = 6;
        Set<Area> areas = this.getContainAreas(locs);
        for (Area area : areas) {
            int areaNum = area.getRulePriority().getNum();

            if (areaNum < num) {
                num = areaNum;
                result = area.getRuleValue(rule);
            }
        }

        return result;
    }

    @Override
    public void aDataSave() {
        FileConfiguration fileConfiguration = new YamlConfiguration();
        fileConfiguration.set("aData", this.aData);


        File file = new File("plugins/Dell_arte/worldData/" + this.world.getName() + ".yml");

        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        CommediaDell_arte.sendLog("§aSave WorldData: " + this.world.getName());
    }

    @Override
    public void aDataLoad() {
        FileConfiguration fileConfiguration = new YamlConfiguration();
        File file = new File("plugins/Dell_arte/worldData/" + this.world.getName() + ".yml");

        try {
            fileConfiguration.load(file);
        }  catch (IOException | InvalidConfigurationException e) {
            if (e instanceof FileNotFoundException) CommediaDell_arte.sendLog("§cNot Found WorldData: " + this.world.getName());
            else e.printStackTrace();

            return;
        }

        A_Data aData = (A_Data) fileConfiguration.get("aData");
        if (aData != null) this.aData.copy(aData);
        CommediaDell_arte.sendLog("§aLoad WorldData: " + this.world.getName());
    }

    @Override
    public A_Data getAData() {
        return this.aData;
    }

    @Override
    @NotNull
    public Block getBlockAt(int i, int i1, int i2) {
        return world.getBlockAt(i, i1, i2);
    }

    @Override
    @NotNull
    public Block getBlockAt(@NotNull Location location) {
        return world.getBlockAt(location);
    }

    @Override
    public int getHighestBlockYAt(int i, int i1) {
        return world.getHighestBlockYAt(i, i1);
    }

    @Override
    public int getHighestBlockYAt(@NotNull Location location) {
        return world.getHighestBlockYAt(location);
    }

    @Override
    @NotNull
    public Block getHighestBlockAt(int i, int i1) {
        return world.getHighestBlockAt(i, i1);
    }

    @Override
    @NotNull
    public Block getHighestBlockAt(@NotNull Location location) {
        return world.getHighestBlockAt(location);
    }

    @Override
    public int getHighestBlockYAt(int i, int i1, @NotNull HeightMap heightMap) {
        return world.getHighestBlockYAt(i, i1, heightMap);
    }

    @Override
    public int getHighestBlockYAt(@NotNull Location location, @NotNull HeightMap heightMap) {
        return world.getHighestBlockYAt(location, heightMap);
    }

    @Override
    @NotNull
    public Block getHighestBlockAt(int i, int i1, @NotNull HeightMap heightMap) {
        return world.getHighestBlockAt(i, i1, heightMap);
    }

    @Override
    @NotNull
    public Block getHighestBlockAt(@NotNull Location location, @NotNull HeightMap heightMap) {
        return world.getHighestBlockAt(location, heightMap);
    }

    @Override
    @NotNull
    public Chunk getChunkAt(int i, int i1) {
        return world.getChunkAt(i, i1);
    }

    @Override
    @NotNull
    public Chunk getChunkAt(@NotNull Location location) {
        return world.getChunkAt(location);
    }

    @Override
    @NotNull
    public Chunk getChunkAt(@NotNull Block block) {
        return world.getChunkAt(block);
    }

    @Override
    public boolean isChunkLoaded(@NotNull Chunk chunk) {
        return world.isChunkLoaded(chunk);
    }

    @Override
    @NotNull
    public Chunk[] getLoadedChunks() {
        return world.getLoadedChunks();
    }

    @Override
    public void loadChunk(@NotNull Chunk chunk) {
        world.loadChunk(chunk);
    }

    @Override
    public boolean isChunkLoaded(int i, int i1) {
        return world.isChunkLoaded(i, i1);
    }

    @Override
    public boolean isChunkGenerated(int i, int i1) {
        return world.isChunkGenerated(i, i1);
    }

    @Override
    public boolean isChunkInUse(int i, int i1) {
        return world.isChunkInUse(i, i1);
    }

    @Override
    public void loadChunk(int i, int i1) {
        world.loadChunk(i, i1);
    }

    @Override
    public boolean loadChunk(int i, int i1, boolean b) {
        return world.loadChunk(i, i1, b);
    }

    @Override
    public boolean unloadChunk(@NotNull Chunk chunk) {
        return world.unloadChunk(chunk);
    }

    @Override
    public boolean unloadChunk(int i, int i1) {
        return world.unloadChunk(i, i1);
    }

    @Override
    public boolean unloadChunk(int i, int i1, boolean b) {
        return world.unloadChunk(i, i1, b);
    }

    @Override
    public boolean unloadChunkRequest(int i, int i1) {
        return world.unloadChunkRequest(i, i1);
    }

    @Override
    public boolean regenerateChunk(int i, int i1) {
        return world.regenerateChunk(i, i1);
    }

    @Override

    public boolean refreshChunk(int i, int i1) {
        return world.refreshChunk(i, i1);
    }

    @Override
    public boolean isChunkForceLoaded(int i, int i1) {
        return world.isChunkForceLoaded(i, i1);
    }

    @Override
    public void setChunkForceLoaded(int i, int i1, boolean b) {
        world.setChunkForceLoaded(i, i1, b);
    }

    @Override
    @NotNull
    public Collection<Chunk> getForceLoadedChunks() {
        return world.getForceLoadedChunks();
    }

    @Override
    public boolean addPluginChunkTicket(int i, int i1, @NotNull Plugin plugin) {
        return world.addPluginChunkTicket(i, i1, plugin);
    }

    @Override
    public boolean removePluginChunkTicket(int i, int i1, @NotNull Plugin plugin) {
        return world.removePluginChunkTicket(i, i1, plugin);
    }

    @Override
    public void removePluginChunkTickets(@NotNull Plugin plugin) {
        world.removePluginChunkTickets(plugin);
    }

    @Override
    @NotNull
    public Collection<Plugin> getPluginChunkTickets(int i, int i1) {
        return world.getPluginChunkTickets(i, i1);
    }

    @Override
    @NotNull
    public Map<Plugin, Collection<Chunk>> getPluginChunkTickets() {
        return world.getPluginChunkTickets();
    }

    @Override
    @NotNull
    public Item dropItem(@NotNull Location location, @NotNull ItemStack itemStack) {
        return world.dropItem(location, itemStack);
    }

    @Override
    @NotNull
    public Item dropItem(@NotNull Location location, @NotNull ItemStack itemStack, @Nullable Consumer<Item> consumer) {
        return world.dropItem(location, itemStack, consumer);
    }

    @Override
    @NotNull
    public Item dropItemNaturally(@NotNull Location location, @NotNull ItemStack itemStack) {
        return world.dropItemNaturally(location, itemStack);
    }

    @Override
    @NotNull
    public Item dropItemNaturally(@NotNull Location location, @NotNull ItemStack itemStack, @Nullable Consumer<Item> consumer) {
        return world.dropItemNaturally(location, itemStack, consumer);
    }

    @Override
    @NotNull
    public Arrow spawnArrow(@NotNull Location location, @NotNull Vector vector, float v, float v1) {
        return world.spawnArrow(location, vector, v, v1);
    }

    @Override
    @NotNull
    public <T extends AbstractArrow> T spawnArrow(@NotNull Location location, @NotNull Vector vector, float v, float v1, @NotNull Class<T> aClass) {
        return world.spawnArrow(location, vector, v, v1, aClass);
    }

    @Override
    public boolean generateTree(@NotNull Location location, @NotNull TreeType treeType) {
        return world.generateTree(location, treeType);
    }

    @Override
    public boolean generateTree(@NotNull Location location, @NotNull TreeType treeType, @NotNull BlockChangeDelegate blockChangeDelegate) {
        return world.generateTree(location, treeType, blockChangeDelegate);
    }

    @Override
    @NotNull
    public Entity spawnEntity(@NotNull Location location, @NotNull EntityType entityType) {
        return world.spawnEntity(location, entityType);
    }

    @Override
    @NotNull
    public LightningStrike strikeLightning(@NotNull Location location) {
        return world.strikeLightning(location);
    }

    @Override
    @NotNull
    public LightningStrike strikeLightningEffect(@NotNull Location location) {
        return world.strikeLightningEffect(location);
    }

    @Override
    @NotNull
    public List<Entity> getEntities() {
        return world.getEntities();
    }

    @Override
    @NotNull
    public List<LivingEntity> getLivingEntities() {
        return world.getLivingEntities();
    }

    @Override
    @NotNull
    public <T extends Entity> Collection<T> getEntitiesByClass(@NotNull Class<T>... classes) {
        return world.getEntitiesByClass(classes);
    }

    @Override
    @NotNull
    public <T extends Entity> Collection<T> getEntitiesByClass(@NotNull Class<T> aClass) {
        return world.getEntitiesByClass(aClass);
    }

    @Override
    @NotNull
    public Collection<Entity> getEntitiesByClasses(@NotNull Class<?>... classes) {
        return world.getEntitiesByClasses(classes);
    }

    @Override
    @NotNull
    public List<Player> getPlayers() {
        return world.getPlayers();
    }

    @Override
    @NotNull
    public Collection<Entity> getNearbyEntities(@NotNull Location location, double v, double v1, double v2) {
        return world.getNearbyEntities(location, v, v1, v2);
    }

    @Override
    @NotNull
    public Collection<Entity> getNearbyEntities(@NotNull Location location, double v, double v1, double v2, @Nullable Predicate<Entity> predicate) {
        return world.getNearbyEntities(location, v, v1, v2, predicate);
    }

    @Override
    @NotNull
    public Collection<Entity> getNearbyEntities(@NotNull BoundingBox boundingBox) {
        return world.getNearbyEntities(boundingBox);
    }

    @Override
    @NotNull
    public Collection<Entity> getNearbyEntities(@NotNull BoundingBox boundingBox, @Nullable Predicate<Entity> predicate) {
        return world.getNearbyEntities(boundingBox, predicate);
    }

    @Override
    @Nullable
    public RayTraceResult rayTraceEntities(@NotNull Location location, @NotNull Vector vector, double v) {
        return world.rayTraceEntities(location, vector, v);
    }

    @Override
    @Nullable
    public RayTraceResult rayTraceEntities(@NotNull Location location, @NotNull Vector vector, double v, double v1) {
        return world.rayTraceEntities(location, vector, v, v1);
    }

    @Override
    @Nullable
    public RayTraceResult rayTraceEntities(@NotNull Location location, @NotNull Vector vector, double v, @Nullable Predicate<Entity> predicate) {
        return world.rayTraceEntities(location, vector, v, predicate);
    }

    @Override
    @Nullable
    public RayTraceResult rayTraceEntities(@NotNull Location location, @NotNull Vector vector, double v, double v1, @Nullable Predicate<Entity> predicate) {
        return world.rayTraceEntities(location, vector, v, v1, predicate);
    }

    @Override
    @Nullable
    public RayTraceResult rayTraceBlocks(@NotNull Location location, @NotNull Vector vector, double v) {
        return world.rayTraceBlocks(location, vector, v);
    }

    @Override
    @Nullable
    public RayTraceResult rayTraceBlocks(@NotNull Location location, @NotNull Vector vector, double v, @NotNull FluidCollisionMode fluidCollisionMode) {
        return world.rayTraceBlocks(location, vector, v, fluidCollisionMode);
    }

    @Override
    @Nullable
    public RayTraceResult rayTraceBlocks(@NotNull Location location, @NotNull Vector vector, double v, @NotNull FluidCollisionMode fluidCollisionMode, boolean b) {
        return world.rayTraceBlocks(location, vector, v, fluidCollisionMode, b);
    }

    @Override
    @Nullable
    public RayTraceResult rayTrace(@NotNull Location location, @NotNull Vector vector, double v, @NotNull FluidCollisionMode fluidCollisionMode, boolean b, double v1, @Nullable Predicate<Entity> predicate) {
        return world.rayTrace(location, vector, v, fluidCollisionMode, b, v1, predicate);
    }

    @Override
    @NotNull
    public String getName() {
        return world.getName();
    }

    @Override
    @NotNull
    public UUID getUID() {
        return world.getUID();
    }

    @Override
    @NotNull
    public Location getSpawnLocation() {
        return world.getSpawnLocation();
    }

    @Override
    public boolean setSpawnLocation(@NotNull Location location) {
        return world.setSpawnLocation(location);
    }

    @Override
    public boolean setSpawnLocation(int i, int i1, int i2, float v) {
        return world.setSpawnLocation(i, i1, i2, v);
    }

    @Override
    public boolean setSpawnLocation(int i, int i1, int i2) {
        return world.setSpawnLocation(i, i1, i2);
    }

    @Override
    public long getTime() {
        return world.getTime();
    }

    @Override
    public void setTime(long l) {
        world.setTime(l);
    }

    @Override
    public long getFullTime() {
        return world.getFullTime();
    }

    @Override
    public void setFullTime(long l) {
        world.setFullTime(l);
    }

    @Override
    public long getGameTime() {
        return world.getGameTime();
    }

    @Override
    public boolean hasStorm() {
        return world.hasStorm();
    }

    @Override
    public void setStorm(boolean b) {
        world.setStorm(b);
    }

    @Override
    public int getWeatherDuration() {
        return world.getWeatherDuration();
    }

    @Override
    public void setWeatherDuration(int i) {
        world.setWeatherDuration(i);
    }

    @Override
    public boolean isThundering() {
        return world.isThundering();
    }

    @Override
    public void setThundering(boolean b) {
        world.setThundering(b);
    }

    @Override
    public int getThunderDuration() {
        return world.getThunderDuration();
    }

    @Override
    public void setThunderDuration(int i) {
        world.setThunderDuration(i);
    }

    @Override
    public boolean isClearWeather() {
        return world.isClearWeather();
    }

    @Override
    public void setClearWeatherDuration(int i) {
        world.setClearWeatherDuration(i);
    }

    @Override
    public int getClearWeatherDuration() {
        return world.getClearWeatherDuration();
    }

    @Override
    public boolean createExplosion(double v, double v1, double v2, float v3) {
        return world.createExplosion(v, v1, v2, v3);
    }

    @Override
    public boolean createExplosion(double v, double v1, double v2, float v3, boolean b) {
        return world.createExplosion(v, v1, v2, v3, b);
    }

    @Override
    public boolean createExplosion(double v, double v1, double v2, float v3, boolean b, boolean b1) {
        return world.createExplosion(v, v1, v2, v3, b, b1);
    }

    @Override
    public boolean createExplosion(double v, double v1, double v2, float v3, boolean b, boolean b1, @Nullable Entity entity) {
        return world.createExplosion(v, v1, v2, v3, b, b1, entity);
    }

    @Override
    public boolean createExplosion(@NotNull Location location, float v) {
        return world.createExplosion(location, v);
    }

    @Override
    public boolean createExplosion(@NotNull Location location, float v, boolean b) {
        return world.createExplosion(location, v, b);
    }

    @Override
    public boolean createExplosion(@NotNull Location location, float v, boolean b, boolean b1) {
        return world.createExplosion(location, v, b, b1);
    }

    @Override
    public boolean createExplosion(@NotNull Location location, float v, boolean b, boolean b1, @Nullable Entity entity) {
        return world.createExplosion(location, v, b, b1, entity);
    }

    @Override
    @NotNull
    public World.Environment getEnvironment() {
        return world.getEnvironment();
    }

    @Override
    public long getSeed() {
        return world.getSeed();
    }

    @Override
    public boolean getPVP() {
        return world.getPVP();
    }

    @Override
    public void setPVP(boolean b) {
        world.setPVP(b);
    }

    @Override
    @Nullable
    public ChunkGenerator getGenerator() {
        return world.getGenerator();
    }

    @Override
    public void save() {
        world.save();
    }

    @Override
    @NotNull
    public List<BlockPopulator> getPopulators() {
        return world.getPopulators();
    }

    @Override
    @NotNull
    public <T extends Entity> T spawn(@NotNull Location location, @NotNull Class<T> aClass) throws IllegalArgumentException {
        return world.spawn(location, aClass);
    }

    @Override
    @NotNull
    public <T extends Entity> T spawn(@NotNull Location location, @NotNull Class<T> aClass, @Nullable Consumer<T> consumer) throws IllegalArgumentException {
        return world.spawn(location, aClass, consumer);
    }

    @Override
    @NotNull
    public FallingBlock spawnFallingBlock(@NotNull Location location, @NotNull MaterialData materialData) throws IllegalArgumentException {
        return world.spawnFallingBlock(location, materialData);
    }

    @Override
    @NotNull
    public FallingBlock spawnFallingBlock(@NotNull Location location, @NotNull BlockData blockData) throws IllegalArgumentException {
        return world.spawnFallingBlock(location, blockData);
    }

    @Override
    @NotNull

    public FallingBlock spawnFallingBlock(@NotNull Location location, @NotNull Material material, byte b) throws IllegalArgumentException {
        return world.spawnFallingBlock(location, material, b);
    }

    @Override
    public void playEffect(@NotNull Location location, @NotNull Effect effect, int i) {
        world.playEffect(location, effect, i);
    }

    @Override
    public void playEffect(@NotNull Location location, @NotNull Effect effect, int i, int i1) {
        world.playEffect(location, effect, i, i1);
    }

    @Override
    public <T> void playEffect(@NotNull Location location, @NotNull Effect effect, @Nullable T t) {
        world.playEffect(location, effect, t);
    }

    @Override
    public <T> void playEffect(@NotNull Location location, @NotNull Effect effect, @Nullable T t, int i) {
        world.playEffect(location, effect, t, i);
    }

    @Override
    @NotNull
    public ChunkSnapshot getEmptyChunkSnapshot(int i, int i1, boolean b, boolean b1) {
        return world.getEmptyChunkSnapshot(i, i1, b, b1);
    }

    @Override
    public void setSpawnFlags(boolean b, boolean b1) {
        world.setSpawnFlags(b, b1);
    }

    @Override
    public boolean getAllowAnimals() {
        return world.getAllowAnimals();
    }

    @Override
    public boolean getAllowMonsters() {
        return world.getAllowMonsters();
    }

    @Override
    @NotNull

    public Biome getBiome(int i, int i1) {
        return world.getBiome(i, i1);
    }

    @Override
    @NotNull
    public Biome getBiome(int i, int i1, int i2) {
        return world.getBiome(i, i1, i2);
    }

    @Override
    public void setBiome(int i, int i1, @NotNull Biome biome) {
        world.setBiome(i, i1, biome);
    }

    @Override
    public void setBiome(int i, int i1, int i2, @NotNull Biome biome) {
        world.setBiome(i, i1, i2, biome);
    }

    @Override
    public double getTemperature(int i, int i1) {
        return world.getTemperature(i, i1);
    }

    @Override
    public double getTemperature(int i, int i1, int i2) {
        return world.getTemperature(i, i1, i2);
    }

    @Override
    public double getHumidity(int i, int i1) {
        return world.getHumidity(i, i1);
    }

    @Override
    public double getHumidity(int i, int i1, int i2) {
        return world.getHumidity(i, i1, i2);
    }

    @Override
    public int getMinHeight() {
        return world.getMinHeight();
    }

    @Override
    public int getMaxHeight() {
        return world.getMaxHeight();
    }

    @Override
    public int getSeaLevel() {
        return world.getSeaLevel();
    }

    @Override
    public boolean getKeepSpawnInMemory() {
        return world.getKeepSpawnInMemory();
    }

    @Override
    public void setKeepSpawnInMemory(boolean b) {
        world.setKeepSpawnInMemory(b);
    }

    @Override
    public boolean isAutoSave() {
        return world.isAutoSave();
    }

    @Override
    public void setAutoSave(boolean b) {
        world.setAutoSave(b);
    }

    @Override
    public void setDifficulty(@NotNull Difficulty difficulty) {
        world.setDifficulty(difficulty);
    }

    @Override
    @NotNull
    public Difficulty getDifficulty() {
        return world.getDifficulty();
    }

    @Override
    @NotNull
    public File getWorldFolder() {
        return world.getWorldFolder();
    }

    @Override
    @Nullable
    public WorldType getWorldType() {
        return world.getWorldType();
    }

    @Override
    public boolean canGenerateStructures() {
        return world.canGenerateStructures();
    }

    @Override
    public boolean isHardcore() {
        return world.isHardcore();
    }

    @Override
    public void setHardcore(boolean b) {
        world.setHardcore(b);
    }

    @Override
    public long getTicksPerAnimalSpawns() {
        return world.getTicksPerAnimalSpawns();
    }

    @Override
    public void setTicksPerAnimalSpawns(int i) {
        world.setTicksPerAnimalSpawns(i);
    }

    @Override
    public long getTicksPerMonsterSpawns() {
        return world.getTicksPerMonsterSpawns();
    }

    @Override
    public void setTicksPerMonsterSpawns(int i) {
        world.setTicksPerMonsterSpawns(i);
    }

    @Override
    public long getTicksPerWaterSpawns() {
        return world.getTicksPerWaterSpawns();
    }

    @Override
    public void setTicksPerWaterSpawns(int i) {
        world.setTicksPerWaterSpawns(i);
    }

    @Override
    public long getTicksPerWaterAmbientSpawns() {
        return world.getTicksPerWaterAmbientSpawns();
    }

    @Override
    public void setTicksPerWaterAmbientSpawns(int i) {
        world.setTicksPerWaterAmbientSpawns(i);
    }

    @Override
    public long getTicksPerAmbientSpawns() {
        return world.getTicksPerAmbientSpawns();
    }

    @Override
    public void setTicksPerAmbientSpawns(int i) {
        world.setTicksPerAmbientSpawns(i);
    }

    @Override
    public int getMonsterSpawnLimit() {
        return world.getMonsterSpawnLimit();
    }

    @Override
    public void setMonsterSpawnLimit(int i) {
        world.setMonsterSpawnLimit(i);
    }

    @Override
    public int getAnimalSpawnLimit() {
        return world.getAnimalSpawnLimit();
    }

    @Override
    public void setAnimalSpawnLimit(int i) {
        world.setAnimalSpawnLimit(i);
    }

    @Override
    public int getWaterAnimalSpawnLimit() {
        return world.getWaterAnimalSpawnLimit();
    }

    @Override
    public void setWaterAnimalSpawnLimit(int i) {
        world.setWaterAnimalSpawnLimit(i);
    }

    @Override
    public int getWaterAmbientSpawnLimit() {
        return world.getWaterAmbientSpawnLimit();
    }

    @Override
    public void setWaterAmbientSpawnLimit(int i) {
        world.setWaterAmbientSpawnLimit(i);
    }

    @Override
    public int getAmbientSpawnLimit() {
        return world.getAmbientSpawnLimit();
    }

    @Override
    public void setAmbientSpawnLimit(int i) {
        world.setAmbientSpawnLimit(i);
    }

    @Override
    public void playSound(@NotNull Location location, @NotNull Sound sound, float v, float v1) {
        world.playSound(location, sound, v, v1);
    }

    @Override
    public void playSound(@NotNull Location location, @NotNull String s, float v, float v1) {
        world.playSound(location, s, v, v1);
    }

    @Override
    public void playSound(@NotNull Location location, @NotNull Sound sound, @NotNull SoundCategory soundCategory, float v, float v1) {
        world.playSound(location, sound, soundCategory, v, v1);
    }

    @Override
    public void playSound(@NotNull Location location, @NotNull String s, @NotNull SoundCategory soundCategory, float v, float v1) {
        world.playSound(location, s, soundCategory, v, v1);
    }

    @Override
    @NotNull
    public String[] getGameRules() {
        return world.getGameRules();
    }

    @Override
    @Nullable
    @Contract("null -> null; !null -> !null")

    public String getGameRuleValue(@Nullable String s) {
        return world.getGameRuleValue(s);
    }

    @Override
    public boolean setGameRuleValue(@NotNull String s, @NotNull String s1) {
        return world.setGameRuleValue(s, s1);
    }

    @Override
    public boolean isGameRule(@NotNull String s) {
        return world.isGameRule(s);
    }

    @Override
    @Nullable
    public <T> T getGameRuleValue(@NotNull GameRule<T> gameRule) {
        return world.getGameRuleValue(gameRule);
    }

    @Override
    @Nullable
    public <T> T getGameRuleDefault(@NotNull GameRule<T> gameRule) {
        return world.getGameRuleDefault(gameRule);
    }

    @Override
    public <T> boolean setGameRule(@NotNull GameRule<T> gameRule, @NotNull T t) {
        return world.setGameRule(gameRule, t);
    }

    @Override
    @NotNull
    public WorldBorder getWorldBorder() {
        return world.getWorldBorder();
    }

    @Override
    public void spawnParticle(@NotNull Particle particle, @NotNull Location location, int i) {
        world.spawnParticle(particle, location, i);
    }

    @Override
    public void spawnParticle(@NotNull Particle particle, double v, double v1, double v2, int i) {
        world.spawnParticle(particle, v, v1, v2, i);
    }

    @Override
    public <T> void spawnParticle(@NotNull Particle particle, @NotNull Location location, int i, @Nullable T t) {
        world.spawnParticle(particle, location, i, t);
    }

    @Override
    public <T> void spawnParticle(@NotNull Particle particle, double v, double v1, double v2, int i, @Nullable T t) {
        world.spawnParticle(particle, v, v1, v2, i, t);
    }

    @Override
    public void spawnParticle(@NotNull Particle particle, @NotNull Location location, int i, double v, double v1, double v2) {
        world.spawnParticle(particle, location, i, v, v1, v2);
    }

    @Override
    public void spawnParticle(@NotNull Particle particle, double v, double v1, double v2, int i, double v3, double v4, double v5) {
        world.spawnParticle(particle, v, v1, v2, i, v3, v4, v5);
    }

    @Override
    public <T> void spawnParticle(@NotNull Particle particle, @NotNull Location location, int i, double v, double v1, double v2, @Nullable T t) {
        world.spawnParticle(particle, location, i, v, v1, v2, t);
    }

    @Override
    public <T> void spawnParticle(@NotNull Particle particle, double v, double v1, double v2, int i, double v3, double v4, double v5, @Nullable T t) {
        world.spawnParticle(particle, v, v1, v2, i, v3, v4, v5, t);
    }

    @Override
    public void spawnParticle(@NotNull Particle particle, @NotNull Location location, int i, double v, double v1, double v2, double v3) {
        world.spawnParticle(particle, location, i, v, v1, v2, v3);
    }

    @Override
    public void spawnParticle(@NotNull Particle particle, double v, double v1, double v2, int i, double v3, double v4, double v5, double v6) {
        world.spawnParticle(particle, v, v1, v2, i, v3, v4, v5, v6);
    }

    @Override
    public <T> void spawnParticle(@NotNull Particle particle, @NotNull Location location, int i, double v, double v1, double v2, double v3, @Nullable T t) {
        world.spawnParticle(particle, location, i, v, v1, v2, v3, t);
    }

    @Override
    public <T> void spawnParticle(@NotNull Particle particle, double v, double v1, double v2, int i, double v3, double v4, double v5, double v6, @Nullable T t) {
        world.spawnParticle(particle, v, v1, v2, i, v3, v4, v5, v6, t);
    }

    @Override
    public <T> void spawnParticle(@NotNull Particle particle, @NotNull Location location, int i, double v, double v1, double v2, double v3, @Nullable T t, boolean b) {
        world.spawnParticle(particle, location, i, v, v1, v2, v3, t, b);
    }

    @Override
    public <T> void spawnParticle(@NotNull Particle particle, double v, double v1, double v2, int i, double v3, double v4, double v5, double v6, @Nullable T t, boolean b) {
        world.spawnParticle(particle, v, v1, v2, i, v3, v4, v5, v6, t, b);
    }

    @Override
    @Nullable
    public Location locateNearestStructure(@NotNull Location location, @NotNull StructureType structureType, int i, boolean b) {
        return world.locateNearestStructure(location, structureType, i, b);
    }

    @Override
    public int getViewDistance() {
        return world.getViewDistance();
    }

    @Override
    @NotNull
    public World.Spigot spigot() {
        return world.spigot();
    }

    @Override
    @Nullable
    public Raid locateNearestRaid(@NotNull Location location, int i) {
        return world.locateNearestRaid(location, i);
    }

    @Override
    @NotNull
    public List<Raid> getRaids() {
        return world.getRaids();
    }

    @Override
    @Nullable
    public DragonBattle getEnderDragonBattle() {
        return world.getEnderDragonBattle();
    }

    @Override
    public void sendPluginMessage(@NotNull Plugin plugin, @NotNull String s, @NotNull byte[] bytes) {
        world.sendPluginMessage(plugin, s, bytes);
    }

    @Override
    @NotNull
    public Set<String> getListeningPluginChannels() {
        return world.getListeningPluginChannels();
    }

    @Override
    public void setMetadata(@NotNull String s, @NotNull MetadataValue metadataValue) {
        world.setMetadata(s, metadataValue);
    }

    @Override
    @NotNull
    public List<MetadataValue> getMetadata(@NotNull String s) {
        return world.getMetadata(s);
    }

    @Override
    public boolean hasMetadata(@NotNull String s) {
        return world.hasMetadata(s);
    }

    @Override
    public void removeMetadata(@NotNull String s, @NotNull Plugin plugin) {
        world.removeMetadata(s, plugin);
    }
}
