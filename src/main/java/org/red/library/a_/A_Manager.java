package org.red.library.a_;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.red.library.CommediaDell_arte;
import org.red.library.entity.a_.A_Entity;
import org.red.library.entity.a_.A_LivingEntity;
import org.red.library.entity.a_.impl.*;
import org.red.library.entity.a_.player.A_Player;
import org.red.library.entity.a_.player.npc.A_NPC;
import org.red.library.entity.a_.player.offline.A_OfflinePlayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class A_Manager {
    public static final A_Manager INSTANCE = new A_Manager(CommediaDell_arte.getPlugin());

    public static A_Player getPlayer(Player player) {
        return INSTANCE.getAPlayer(player);
    }

    public static A_Player getPlayer(String name) {
        Player player = Bukkit.getPlayer(name);
        return player == null ? null : INSTANCE.getAPlayer(player);
    }

    public static A_Player getPlayer(UUID uuid) {
        Player player = Bukkit.getPlayer(uuid);
        return player == null ? null : INSTANCE.getAPlayer(player);
    }

    private final Map<UUID, A_Entity> aEntities = new HashMap<>();
    private final Map<UUID, A_Player> aPlayers = new HashMap<>();
    private final Map<UUID, A_NPC> aNPCs = new HashMap<>();
    private final Map<UUID, A_OfflinePlayer> aOfflinePlayers = new HashMap<>();
    private final CommediaDell_arte plugin;
    private final A_Version aVersion;
    private A_Manager(CommediaDell_arte plugin) {
        this.plugin = plugin;
        this.aVersion = new A_Version(plugin);
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

    public A_Entity getAEntity(Entity entity) {
        return aEntities.computeIfAbsent(entity.getUniqueId(), uuid -> {
            if (entity instanceof LivingEntity) {
                return new A_LivingEntityImpl((LivingEntity) entity, A_Data.newAData(), aVersion);
            }

            return new A_EntityImpl(entity, A_Data.newAData(), aVersion);
        });
    }

    public A_OfflinePlayer getAOfflinePlayer(OfflinePlayer offlinePlayer) {
        return aOfflinePlayers.computeIfAbsent(offlinePlayer.getUniqueId(), uuid -> new A_OfflinePlayerImpl(offlinePlayer, A_Data.newAData(), aVersion));
    }

    public A_LivingEntity getALivingEntity(LivingEntity livingEntity) {
        return getAEntity(livingEntity).getALivingEntity();
    }

    public A_Player getAPlayer(Player player) {
        if (player.hasMetadata("NPC")) {
            return aNPCs.computeIfAbsent(player.getUniqueId(), uuid -> new A_NPCImpl(player, A_Data.newAData(), aVersion));
        }

        return aPlayers.computeIfAbsent(player.getUniqueId(), uuid -> new A_PlayerImpl(player, getAOfflinePlayer(player), aVersion));
    }

    public static final class A_Version {
        private A_Version(CommediaDell_arte plugin) {}

        public double version() {
            return 1.0;
        }
    }
}
