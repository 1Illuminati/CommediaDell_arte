package org.red.a_;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.block.BlockState;
import org.bukkit.block.TileState;
import org.bukkit.boss.BossBar;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.BoundingBox;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.red.CommediaDell_arte;
import org.red.a_.entity.*;
import org.red.a_.util.A_BossBarTimer;
import org.red.a_.util.A_File;
import org.red.a_.util.A_Timer;
import org.red.a_.util.A_YamlConfiguration;
import org.red.a_.vault.A_EconomyAccount;
import org.red.a_.world.A_Area;
import org.red.a_.world.A_WorldImpl;
import org.red.interactive.InteractiveObjInfo;
import org.red.interactive.block.InteractiveTileInfo;
import org.red.interactive.item.InteractiveItemInfo;
import org.red.item.randombox.RandomBoxImpl;
import org.red.library.A_Manager;
import org.red.library.a_.A_Data;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.a_.entity.player.npc.A_NPC;
import org.red.library.a_.entity.player.offline.A_OfflinePlayer;
import org.red.library.interactive.InteractiveAct;
import org.red.library.interactive.InteractiveObj;
import org.red.library.interactive.block.InteractiveTile;
import org.red.library.interactive.block.InteractiveTileAct;
import org.red.library.interactive.item.InteractiveItem;
import org.red.library.interactive.item.InteractiveItemAct;
import org.red.library.item.randombox.RandomBox;
import org.red.library.util.map.DataMap;
import org.red.library.util.map.NameSpaceMap;
import org.red.library.util.persistent.NameSpaceKeyPersistentDataType;
import org.red.library.util.timer.BossBarTimer;
import org.red.library.util.timer.Timer;
import org.red.library.vault.EconomyAccount;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public final class A_ManagerImpl implements A_Manager {
    private final Map<UUID, A_EntityImpl> aEntities = new HashMap<>();
    private final Map<UUID, A_PlayerImpl> aPlayers = new HashMap<>();
    private final Map<UUID, A_NPCImpl> aNPCs = new HashMap<>();
    private final Map<UUID, A_OfflinePlayerImpl> aOfflinePlayers = new HashMap<>();
    private final Map<World, A_WorldImpl> aWorlds = new HashMap<>();
    private final Map<String, A_Area> aAreas = new HashMap<>();
    private final NameSpaceMap<RandomBoxImpl> randomBoxes = new NameSpaceMap<>();
    private final NameSpaceMap<InteractiveObjInfo<?>> interactiveObjs = new NameSpaceMap<>();
    private final NameSpaceMap<DataMap> pluginDataMaps = new NameSpaceMap<>();
    private final CommediaDell_arte plugin;
    private final A_Version aVersion;
    public A_ManagerImpl(CommediaDell_arte plugin) {
        this.plugin = plugin;
        this.aVersion = new A_Version(plugin);
    }

    public void allSave() {
        entitiesADataSave();
        aNPCs.values().forEach(A_NPC::aDataSave);
        aOfflinePlayers.values().forEach(A_OfflinePlayer::aDataSave);
        aWorlds.values().forEach(A_WorldImpl::aDataSave);
        pluginDataMapsSave();
        aAreasSave();
        CommediaDell_arte.sendLog("§aSaved All Data");
    }

    public void allLoad() {
        entitiesADataLoad();
        aNPCs.values().forEach(A_NPC::aDataLoad);
        CommediaDell_arte.sendDebugLog("§aLoaded All NPC Data");
        aOfflinePlayers.values().forEach(A_OfflinePlayer::aDataLoad);
        CommediaDell_arte.sendDebugLog("§aLoaded All Player Data");
        aWorlds.values().forEach(A_WorldImpl::aDataLoad);
        CommediaDell_arte.sendDebugLog("§aLoaded All Worlds Data");
        pluginDataMapsLoad();
        aAreasLoad();
        CommediaDell_arte.sendLog("§aLoaded All Data");
    }

    public void aAreasSave() {
        A_YamlConfiguration aYamlConfiguration = new A_YamlConfiguration();
        this.aAreas.forEach(aYamlConfiguration::set);
        aYamlConfiguration.save(new A_File("area.yml"));
        CommediaDell_arte.sendDebugLog("§aSaved All aAreas");
    }

    public void aAreasLoad() {
        A_YamlConfiguration aYamlConfiguration = new A_YamlConfiguration();
        aYamlConfiguration.load(new A_File("area.yml"));
        aYamlConfiguration.getKeys(false).forEach(key -> aAreas.put(key, (A_Area) aYamlConfiguration.get(key)));
        CommediaDell_arte.sendDebugLog("§aLoaded All aAreas");
    }

    public A_Area createAArea(World world, BoundingBox box, String name) {
        A_Area area = new A_Area(world, box, name, aVersion);
        this.aAreas.put(name, area);
        area.getAWorld().putArea(area);
        return area;
    }

    public Collection<A_Area> getAreas() {
        return this.aAreas.values();
    }

    @Nullable
    public A_Area getArea(String name) {
        return this.aAreas.getOrDefault(name, null);
    }

    @Nullable
    public A_Area removeArea(String name) {
        A_Area area = getArea(name);

        if (area == null) return null;

        area.getAWorld().removeArea(area);

        return this.aAreas.remove(name);
    }

    public void pluginDataMapsLoad() {
        File pluginsFile = new A_File("plugins");
        File[] files = pluginsFile.listFiles();
        if (files == null) return;

        for (File file : files) {
            A_YamlConfiguration aYamlConfiguration = new A_YamlConfiguration();
            aYamlConfiguration.load(file);

            for (String key : aYamlConfiguration.getKeys(false)) {
                NamespacedKey namespacedKey = new NamespacedKey(file.getName().replace(".yml", ""), key);
                DataMap dataMap = (DataMap) aYamlConfiguration.get(key);
                this.pluginDataMaps.put(namespacedKey, dataMap);
            }
        }

        CommediaDell_arte.sendDebugLog("§aLoad PluginDataMaps");
    }

    public void pluginDataMapsSave() {
        Map<String, A_YamlConfiguration> map = new HashMap<>();
        this.pluginDataMaps.forEach((namespacedKey, dataMap) -> {
            map.computeIfAbsent(namespacedKey.getNamespace(), namespace -> new A_YamlConfiguration()).set(namespacedKey.getKey(), dataMap);
        });
        map.forEach((name, configuration) -> configuration.save(new A_File("plugins/" + name + ".yml")));

        CommediaDell_arte.sendDebugLog("§aSave PluginDataMaps");
    }

    public void entitiesADataSave() {
        A_YamlConfiguration fileConfiguration = new A_YamlConfiguration();
        aEntities.values().forEach(aEntity -> {
            Entity entity = Bukkit.getEntity(aEntity.getUniqueId());
            if (entity != null)
                fileConfiguration.set(aEntity.getUniqueId().toString(), aEntity.getAData());
        });
        File file = new A_File("entities.yml");
        fileConfiguration.save(file);
        CommediaDell_arte.sendDebugLog("§aSave EntitiesData");
    }

    public void entitiesADataLoad() {
        A_YamlConfiguration fileConfiguration = new A_YamlConfiguration();
        File file = new A_File("entities.yml");
        fileConfiguration.load(file);

        for (String key : fileConfiguration.getKeys(false)) {
            UUID uuid = UUID.fromString(key);
            A_Data aData = (A_Data) fileConfiguration.get(key);

            Entity entity = Bukkit.getEntity(uuid);
            if (entity == null) {
                CommediaDell_arte.sendErrorLog("§cNot Found Entity: " + uuid);
                continue;
            }

            if (entity instanceof LivingEntity) {
                aEntities.put(uuid, new A_LivingEntityImpl((LivingEntity) entity, aData, aVersion));
            } else {
                aEntities.put(uuid, new A_EntityImpl(entity, aData, aVersion));
            }
        }

        CommediaDell_arte.sendDebugLog("§aLoad EntitiesData");
    }

    public Plugin getPlugin() {
        return plugin;
    }

    @Override
    public A_EntityImpl getAEntity(Entity entity) {
        if (entity instanceof Player) return getAPlayer((Player) entity);
        return aEntities.computeIfAbsent(entity.getUniqueId(), uuid -> {
            A_EntityImpl aEntity;
            if (entity instanceof LivingEntity) {
                aEntity = new A_LivingEntityImpl((LivingEntity) entity, A_Data.newAData(), aVersion);
                CommediaDell_arte.sendDebugLog("§aCreated LivingEntity: " + entity.getUniqueId());
            } else {
                aEntity = new A_EntityImpl(entity, A_Data.newAData(), aVersion);
                CommediaDell_arte.sendDebugLog("§aCreated Entity: " + entity.getUniqueId());
            }

            return aEntity;
        });
    }

    @Override
    public A_EntityImpl getAEntity(UUID uuid) {
        return this.getAEntity(Bukkit.getEntity(uuid));
    }

    @Override
    public A_WorldImpl getAWorld(String worldName) {
        World world = Bukkit.getWorld(worldName);
        return world == null ? null : getAWorld(world);
    }

    @Override
    public A_WorldImpl getAWorld(World world) {
        return this.aWorlds.computeIfAbsent(world, name -> new A_WorldImpl(world, aVersion));
    }

    @Override
    public EconomyAccount createEmptyEconomyAccount() {
        return new A_EconomyAccount();
    }

    @Override
    public void removeAEntity(UUID uuid) {
        Entity entity = Bukkit.getEntity(uuid);

        if (entity == null) return;

        removeAEntity(entity);
    }

    @Override
    public void removeAEntity(Entity entity) {
        UUID uuid = entity.getUniqueId();
        if (entity instanceof Player) {
            if (entity.hasMetadata("NPC")) this.aNPCs.remove(uuid);
            else this.aPlayers.remove(uuid);
            return;
        }

        this.aEntities.remove(uuid);
    }

    @Override
    public A_OfflinePlayerImpl getAOfflinePlayer(OfflinePlayer offlinePlayer) {
        return aOfflinePlayers.computeIfAbsent(offlinePlayer.getUniqueId(), uuid -> {
            A_OfflinePlayerImpl aOfflinePlayer = new A_OfflinePlayerImpl(offlinePlayer, aVersion);
            CommediaDell_arte.sendDebugLog("§aCreated OfflinePlayer: " + offlinePlayer.getUniqueId());
            return aOfflinePlayer;
        }).updateOfflinePlayer();
    }

    @Override
    public A_LivingEntityImpl getALivingEntity(LivingEntity livingEntity) {
        return (A_LivingEntityImpl) getAEntity(livingEntity).getALivingEntity();
    }

    @Override
    public DataMap getPluginDataMap(Plugin plugin, String name) {
        return this.pluginDataMaps.computeIfAbsent(new NamespacedKey(plugin, name), key -> new DataMap());
    }

    @Override
    public RandomBox createRandomBox(String name, List<ItemStack> items) {
        return null;
    }

    @Override
    public RandomBox createRandomBox(String name, ItemStack... items) {
        return null;
    }

    @Override
    public void registerInteractiveObj(InteractiveObj<?> interactiveObj) {
        InteractiveObjInfo<?> interactiveObjInfo;

        if (interactiveObj instanceof InteractiveItem) {
            interactiveObjInfo = new InteractiveItemInfo((InteractiveItem) interactiveObj);
        } else if(interactiveObj instanceof InteractiveTile) {
            interactiveObjInfo = new InteractiveTileInfo((InteractiveTile) interactiveObj);
        } else {
            CommediaDell_arte.sendErrorLog("Not Supported InteractiveObj: " + interactiveObj.getClass().getSimpleName());
            return;
        }
        this.interactiveObjs.put(interactiveObj.getKey(), interactiveObjInfo);
        CommediaDell_arte.sendDebugLog("Register InteractiveObj: " + interactiveObj.getKey());
    }

    @Override
    public <T> void setInteractiveInObj(InteractiveObj<T> interactiveObj, T obj) {
        if (!isRegisteredInteractiveObj(interactiveObj.getKey())) {
            this.registerInteractiveObj(interactiveObj);
        }

        InteractiveObjInfo<T> interactiveObjInfo;
        try {
            interactiveObjInfo = (InteractiveObjInfo<T>) this.interactiveObjs.get(interactiveObj.getKey());
        } catch (ClassCastException exception) {
            CommediaDell_arte.sendErrorLog("Class Type Not Same InteractiveObj: " + interactiveObj.getClass().getSimpleName());
            return;
        }

        interactiveObjInfo.setEventInObj(obj);
    }

    @Override
    public <T> void setInteractiveInObj(NamespacedKey key, T obj) {
        if (!isRegisteredInteractiveObj(key)) {
            CommediaDell_arte.sendErrorLog("Not Registered InteractiveObj: " + key);
            return;
        }

        InteractiveObjInfo<T> interactiveObjInfo;
        try {
            interactiveObjInfo = (InteractiveObjInfo<T>) this.interactiveObjs.get(key);
        } catch (ClassCastException exception) {
            CommediaDell_arte.sendErrorLog("Class Type Not Same InteractiveObj: " + key);
            return;
        }

        interactiveObjInfo.setEventInObj(obj);
    }

    @Override
    public boolean isRegisteredInteractiveObj(NamespacedKey key) {
        return this.interactiveObjs.containsKey(key);
    }

    @Override
    public boolean isItemInInteractive(@Nullable ItemStack itemStack) {
        if (itemStack == null || itemStack.getItemMeta() == null) return false;
        return itemStack.getItemMeta().getPersistentDataContainer().has(InteractiveObjInfo.INTERACTIVE_KEY, NameSpaceKeyPersistentDataType.INSTANCE);
    }

    @Override
    public boolean isTileInInteractive(@NotNull TileState tileState) {
        return tileState.getPersistentDataContainer().has(InteractiveObjInfo.INTERACTIVE_KEY, NameSpaceKeyPersistentDataType.INSTANCE);
    }

    @Override
    public InteractiveItem getInteractiveInItem(ItemStack itemStack) {
        if (!isItemInInteractive(itemStack)) return null;
        NamespacedKey key = itemStack.getItemMeta().getPersistentDataContainer().get(InteractiveObjInfo.INTERACTIVE_KEY, NameSpaceKeyPersistentDataType.INSTANCE);
        return this.isRegisteredInteractiveObj(key) ? (InteractiveItem) this.interactiveObjs.get(key).getObj() : null;
    }

    @Override
    public InteractiveTile getInteractiveInBlock(TileState tileState) {
        if (!isTileInInteractive(tileState)) return null;
        NamespacedKey key = tileState.getPersistentDataContainer().get(InteractiveObjInfo.INTERACTIVE_KEY, NameSpaceKeyPersistentDataType.INSTANCE);
        return this.isRegisteredInteractiveObj(key) ? (InteractiveTile) this.interactiveObjs.get(key).getObj() : null;
    }

    @Override
    public InteractiveObj<?> getInteractiveObj(NamespacedKey key) {
        InteractiveObjInfo<?> interactiveObjInfo = this.interactiveObjs.getOrDefault(key, null);
        return interactiveObjInfo == null ? null : interactiveObjInfo.getObj();
    }

    @Override
    public void runInteractiveEvent(InteractiveObj<?> obj, Class<? extends InteractiveAct> act, A_Player player, Event event) {
        if (!this.interactiveObjs.containsKey(obj.getKey()))
            this.registerInteractiveObj(obj);
        InteractiveObjInfo<?> interactiveObjInfo = this.interactiveObjs.get(obj.getKey());
        interactiveObjInfo.runMethod(act, player, event);
    }

    @Override
    public void canRunInteractiveTileEvent(BlockState blockState, Class<? extends InteractiveTileAct> act, A_Player player, Event event) {
        if (!(blockState instanceof TileState)) return;

        TileState tileState = (TileState) blockState;
        if (!isTileInInteractive(tileState)) return;
        InteractiveTile interactiveTile = getInteractiveInBlock(tileState);
        if (interactiveTile == null) return;
        runInteractiveEvent(interactiveTile, act, player, event);
    }

    @Override
    public void canRunInteractiveItemEvent(ItemStack itemStack, Class<? extends InteractiveItemAct> act, A_Player player, Event event) {
        if (!isItemInInteractive(itemStack)) return;

        InteractiveItem interactiveItem = getInteractiveInItem(itemStack);
        if (interactiveItem == null) return;

        runInteractiveEvent(interactiveItem, act, player, event);
    }

    @Override
    public void disableInteractiveObj(NamespacedKey key) {
        this.interactiveObjs.remove(key);
    }

    @Override
    public void disableInteractiveObj(InteractiveObj<?> interactiveObj) {
        this.interactiveObjs.remove(interactiveObj.getKey());
    }

    @Override
    public Timer createTimer(NamespacedKey key, int maxTime) {
        return new A_Timer(key, maxTime);
    }

    @Override
    public BossBarTimer createBossBarTimer(NamespacedKey key, int maxTime, BossBar... bossBars) {
        A_BossBarTimer aBossBarTimer = new A_BossBarTimer(key, maxTime);
        for (BossBar bossBar : bossBars) aBossBarTimer.addBossBar(bossBar);
        return aBossBarTimer;
    }


    @Override
    public A_PlayerImpl getAPlayer(Player player) {
        if (player.hasMetadata("NPC")) {
            return aNPCs.computeIfAbsent(player.getUniqueId(), uuid -> {
                A_NPCImpl aNpc = new A_NPCImpl(player, aVersion);
                CommediaDell_arte.sendDebugLog("§aCreated NPC: " + player.getUniqueId());
                return aNpc;
            });
        }

        return aPlayers.computeIfAbsent(player.getUniqueId(), uuid -> {
            A_PlayerImpl aPlayer = new A_PlayerImpl(player, getAOfflinePlayer(player), aVersion);
            CommediaDell_arte.sendDebugLog("§aCreated Player: " + player.getUniqueId());
            return aPlayer;
        });
    }

    @Override
    public A_PlayerImpl getAPlayer(String playerName) {
        Player player = Bukkit.getPlayer(playerName);
        return player == null ? null : getAPlayer(player);
    }

    @Override
    public A_PlayerImpl getAPlayer(UUID uuid) {
        Player player = Bukkit.getPlayer(uuid);
        return player == null ? null : getAPlayer(player);
    }

    @Override
    public A_OfflinePlayerImpl getAOfflinePlayer(String playerName) {
        return this.getAOfflinePlayer(Bukkit.getOfflinePlayer(playerName));
    }

    @Override
    public A_OfflinePlayerImpl getAOfflinePlayer(UUID uuid) {
        return this.getAOfflinePlayer(Bukkit.getOfflinePlayer(uuid));
    }

    public static final class A_Version {
        private A_Version(CommediaDell_arte plugin) {}

        public double version() {
            return 2.0;
        }
    }
}
