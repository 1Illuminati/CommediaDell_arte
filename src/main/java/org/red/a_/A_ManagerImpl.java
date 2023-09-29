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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.red.a_.entity.*;
import org.red.CommediaDell_arte;
import org.red.a_.util.A_BossBarTimer;
import org.red.a_.util.A_Timer;
import org.red.a_.world.A_WorldImpl;
import org.red.interactive.InteractiveObjInfo;
import org.red.interactive.block.InteractiveTileInfo;
import org.red.interactive.item.InteractiveItemInfo;
import org.red.item.shop.ShopItemImpl;
import org.red.library.A_Manager;
import org.red.library.a_.A_Data;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.a_.entity.player.npc.A_NPC;
import org.red.library.a_.entity.player.offline.A_OfflinePlayer;
import org.red.library.a_.world.A_World;
import org.red.library.interactive.InteractiveAct;
import org.red.library.interactive.InteractiveObj;
import org.red.library.interactive.block.InteractiveTile;
import org.red.library.interactive.block.InteractiveTileAct;
import org.red.library.interactive.item.InteractiveItem;
import org.red.library.interactive.item.InteractiveItemAct;
import org.red.library.item.shop.ShopItem;
import org.red.library.item.shop.price.Price;
import org.red.library.util.map.NameSpaceMap;
import org.red.library.util.persistent.NameSpaceKeyPersistentDataType;
import org.red.library.util.timer.BossBarTimer;
import org.red.library.util.timer.Timer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class A_ManagerImpl implements A_Manager {
    public static final A_ManagerImpl INSTANCE = new A_ManagerImpl(CommediaDell_arte.getPlugin());
    private final Map<UUID, A_EntityImpl> aEntities = new HashMap<>();
    private final Map<UUID, A_PlayerImpl> aPlayers = new HashMap<>();
    private final Map<UUID, A_NPCImpl> aNPCs = new HashMap<>();
    private final Map<UUID, A_OfflinePlayerImpl> aOfflinePlayers = new HashMap<>();
    private final Map<String, A_WorldImpl> aWorlds = new HashMap<>();
    private final NameSpaceMap<InteractiveObjInfo<?>> interactiveObjs = new NameSpaceMap<>();
    private final CommediaDell_arte plugin;
    private final A_Version aVersion;
    private A_ManagerImpl(CommediaDell_arte plugin) {
        this.plugin = plugin;
        this.aVersion = new A_Version(plugin);
    }

    public void allSave() {
        entitiesADataSave();
        aNPCs.values().forEach(A_NPC::aDataSave);
        aOfflinePlayers.values().forEach(A_OfflinePlayer::aDataSave);
        aWorlds.values().forEach(A_World::aDataSave);
    }

    public void allLoad() {
        entitiesADataLoad();
        aNPCs.values().forEach(A_NPC::aDataLoad);
        aOfflinePlayers.values().forEach(A_OfflinePlayer::aDataLoad);
        aWorlds.values().forEach(A_World::aDataLoad);
    }

    public void entitiesADataSave() {
        FileConfiguration fileConfiguration = new YamlConfiguration();
        aEntities.values().forEach(aEntity -> fileConfiguration.set(aEntity.getUniqueId().toString(), aEntity.getAData()));
        File file = new File("plugins/Dell_arte/entities.yml");

        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        CommediaDell_arte.sendLog("§aSave EntitiesData");
    }

    public void entitiesADataLoad() {
        FileConfiguration fileConfiguration = new YamlConfiguration();
        File file = new File("plugins/Dell_arte/entities.yml");

        try {
            fileConfiguration.load(file);
        }  catch (IOException | InvalidConfigurationException e) {
            if (e instanceof FileNotFoundException) CommediaDell_arte.sendLog("§cNot Found EntitiesData");
            else e.printStackTrace();

            return;
        }

        for (String key : fileConfiguration.getKeys(false)) {
            UUID uuid = UUID.fromString(key);
            A_Data aData = (A_Data) fileConfiguration.get(key);

            Entity entity = Bukkit.getEntity(uuid);
            if (entity == null) {
                CommediaDell_arte.sendDebugLog("§cNot Found Entity: " + uuid);
                continue;
            }

            if (entity instanceof LivingEntity) {
                aEntities.put(uuid, new A_LivingEntityImpl((LivingEntity) entity, aData, aVersion));
            } else {
                aEntities.put(uuid, new A_EntityImpl(entity, aData, aVersion));
            }
        }

        CommediaDell_arte.sendLog("§aLoad EntitiesData");
    }

    public Plugin getPlugin() {
        return plugin;
    }

    @Override

    public A_EntityImpl getAEntity(Entity entity) {
        if (entity instanceof Player) return getAPlayer((Player) entity);
        return aEntities.computeIfAbsent(entity.getUniqueId(), uuid -> {
            if (entity instanceof LivingEntity) {
                return new A_LivingEntityImpl((LivingEntity) entity, A_Data.newAData(), aVersion);
            }

            return new A_EntityImpl(entity, A_Data.newAData(), aVersion);
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
        return this.aWorlds.computeIfAbsent(world.getName(), name -> new A_WorldImpl(world, aVersion));
    }

    @Override
    public A_OfflinePlayerImpl getAOfflinePlayer(OfflinePlayer offlinePlayer) {
        return aOfflinePlayers.computeIfAbsent(offlinePlayer.getUniqueId(), uuid -> new A_OfflinePlayerImpl(offlinePlayer, aVersion)).updateOfflinePlayer();
    }

    @Override
    public A_LivingEntityImpl getALivingEntity(LivingEntity livingEntity) {
        return (A_LivingEntityImpl) getAEntity(livingEntity).getALivingEntity();
    }

    public void deleteOldAPlayer(Player player) {
        this.aPlayers.remove(player.getUniqueId());
    }

    @Override
    public void registerInteractiveObj(InteractiveObj<?> interactiveObj) {
        InteractiveObjInfo<?> interactiveObjInfo;

        if (interactiveObj instanceof InteractiveItem) {
            interactiveObjInfo = new InteractiveItemInfo((InteractiveItem) interactiveObj);
        } else if(interactiveObj instanceof InteractiveTile) {
            interactiveObjInfo = new InteractiveTileInfo((InteractiveTile) interactiveObj);
        } else {
            throw new IllegalArgumentException("Not Supported InteractiveObj: " + interactiveObj.getClass().getSimpleName());
        }
        this.interactiveObjs.put(interactiveObj.getKey(), interactiveObjInfo);
        CommediaDell_arte.sendLog("Register InteractiveObj: " + interactiveObj.getKey());
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
            throw new IllegalArgumentException("Class Type Not Same InteractiveObj: " + interactiveObj.getClass().getSimpleName());
        }

        interactiveObjInfo.setEventInObj(obj);
    }

    @Override
    public <T> void setInteractiveInObj(NamespacedKey key, T obj) {
        if (!isRegisteredInteractiveObj(key)) {
            throw new IllegalArgumentException("Not Registered InteractiveObj: " + key);
        }

        InteractiveObjInfo<T> interactiveObjInfo;
        try {
            interactiveObjInfo = (InteractiveObjInfo<T>) this.interactiveObjs.get(key);
        } catch (ClassCastException exception) {
            throw new IllegalArgumentException("Class Type Not Same InteractiveObj: " + key);
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
    public ShopItem createBuyShopItem(ItemStack originItem, Price buyPrice) {
        return new ShopItemImpl(originItem, true, buyPrice, false, null);
    }

    @Override
    public ShopItem createSellShopItem(ItemStack originItem, Price sellPrice) {
        return new ShopItemImpl(originItem, false, null, true, sellPrice);
    }

    @Override
    public ShopItem createBothShopItem(ItemStack originItem, Price buyPrice, Price sellPrice) {
        return null;
    }

    @Override
    public A_PlayerImpl getAPlayer(Player player) {
        if (player.hasMetadata("NPC")) {
            return aNPCs.computeIfAbsent(player.getUniqueId(), uuid -> new A_NPCImpl(player, aVersion));
        }

        return aPlayers.computeIfAbsent(player.getUniqueId(), uuid -> new A_PlayerImpl(player, getAOfflinePlayer(player), aVersion));
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
