package org.red.a_;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.boss.BossBar;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.red.a_.entity.*;
import org.red.CommediaDell_arte;
import org.red.a_.util.A_BossBarTimer;
import org.red.a_.util.A_Timer;
import org.red.a_.world.A_WorldImpl;
import org.red.item.EventItemManager;
import org.red.library.A_Manager;
import org.red.library.a_.A_Data;
import org.red.library.a_.entity.A_Entity;
import org.red.library.a_.entity.A_LivingEntity;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.a_.entity.player.npc.A_NPC;
import org.red.library.a_.entity.player.offline.A_OfflinePlayer;
import org.red.library.a_.world.A_World;
import org.red.library.item.event.EventItem;
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

    public A_Entity getAEntity(Entity entity) {
        if (entity instanceof Player) return getAPlayer((Player) entity);
        return aEntities.computeIfAbsent(entity.getUniqueId(), uuid -> {
            if (entity instanceof LivingEntity) {
                return new A_LivingEntityImpl((LivingEntity) entity, A_Data.newAData(), aVersion);
            }

            return new A_EntityImpl(entity, A_Data.newAData(), aVersion);
        });
    }

    @Override
    public A_Entity getAEntity(UUID uuid) {
        return this.getAEntity(Bukkit.getEntity(uuid));
    }

    @Override
    public A_World getAWorld(String worldName) {
        World world = Bukkit.getWorld(worldName);
        return world == null ? null : getAWorld(world);
    }

    @Override
    public A_World getAWorld(World world) {
        return this.aWorlds.computeIfAbsent(world.getName(), name -> new A_WorldImpl(world, aVersion));
    }

    @Override
    public A_OfflinePlayer getAOfflinePlayer(OfflinePlayer offlinePlayer) {
        return aOfflinePlayers.computeIfAbsent(offlinePlayer.getUniqueId(), uuid -> new A_OfflinePlayerImpl(offlinePlayer, aVersion)).updateOfflinePlayer();
    }

    @Override
    public A_LivingEntity getALivingEntity(LivingEntity livingEntity) {
        return getAEntity(livingEntity).getALivingEntity();
    }

    public void deleteOldAPlayer(Player player) {
        this.aPlayers.remove(player.getUniqueId());
    }

    @Override
    public void setItemInEvent(EventItem eventItem, ItemStack itemStack) {
        EventItemManager.setEventItemInItem(itemStack, eventItem);
    }

    @Override
    public void setItemInEvent(NamespacedKey eventItemKey, ItemStack itemStack) {
        EventItem eventItem = EventItemManager.getEventItemByKey(eventItemKey);
        if (eventItem == null) throw new NullPointerException("Not Found EventItem: " + eventItemKey);
        EventItemManager.setEventItemInItem(itemStack, eventItem);
    }

    @Override
    public boolean isItemInEvent(ItemStack itemStack) {
        return EventItemManager.hasEventItem(itemStack);
    }

    @Override
    public EventItem getEventInItem(ItemStack itemStack) {
        return EventItemManager.getEventItemByItem(itemStack);
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
    public A_Player getAPlayer(Player player) {
        if (player.hasMetadata("NPC")) {
            return aNPCs.computeIfAbsent(player.getUniqueId(), uuid -> new A_NPCImpl(player, aVersion));
        }

        return aPlayers.computeIfAbsent(player.getUniqueId(), uuid -> new A_PlayerImpl(player, getAOfflinePlayer(player), aVersion));
    }

    @Override
    public A_Player getAPlayer(String playerName) {
        Player player = Bukkit.getPlayer(playerName);
        return player == null ? null : getAPlayer(player);
    }

    @Override
    public A_Player getAPlayer(UUID uuid) {
        Player player = Bukkit.getPlayer(uuid);
        return player == null ? null : getAPlayer(player);
    }

    @Override
    public A_OfflinePlayer getAOfflinePlayer(String playerName) {
        return this.getAOfflinePlayer(Bukkit.getOfflinePlayer(playerName));
    }

    @Override
    public A_OfflinePlayer getAOfflinePlayer(UUID uuid) {
        return this.getAOfflinePlayer(Bukkit.getOfflinePlayer(uuid));
    }

    public static final class A_Version {
        private A_Version(CommediaDell_arte plugin) {}

        public double version() {
            return 2.0;
        }
    }
}
