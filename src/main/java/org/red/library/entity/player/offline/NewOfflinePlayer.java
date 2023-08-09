package org.red.library.entity.player.offline;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.red.library.CommediaDell_arte;
import org.red.library.entity.player.APlayer;
import org.red.library.entity.player.PlayerData;
import org.red.library.util.map.CoolTime;
import org.red.library.util.map.DataMap;

import java.util.*;

public final class NewOfflinePlayer extends OfflinePlayerAdapter {
    private static final List<UUID> clowns = Arrays.asList(UUID.fromString("5652f272-bced-4a09-8785-3e5bf260a3f9"),
            UUID.fromString("697c7e70-8863-4595-bc3a-cd190af795d2"), UUID.fromString("a9f022ea-c7b0-4b13-8543-e6ed24e8396f"),
            UUID.fromString("7940594e-9a43-4fb1-b8a1-ede1869fd2f0"));
    private static final Map<UUID, NewOfflinePlayer> newOfflinePlayerMap = new HashMap<>();

    public static NewOfflinePlayer getNewOfflinePlayer(UUID playerUUID) {
        return NewOfflinePlayer.getNewOfflinePlayer(Bukkit.getOfflinePlayer(playerUUID));
    }

    public static NewOfflinePlayer getNewOfflinePlayer(OfflinePlayer offlinePlayer) {
        if (!newOfflinePlayerMap.containsKey(offlinePlayer.getUniqueId()))
            newOfflinePlayerMap.put(offlinePlayer.getUniqueId(), new NewOfflinePlayer(offlinePlayer));

        return newOfflinePlayerMap.get(offlinePlayer.getUniqueId());
    }

    public static void saveAll() {
        for (NewOfflinePlayer newOfflinePlayer : newOfflinePlayerMap.values()) {
            newOfflinePlayer.save();
        }

        CommediaDell_arte.sendLog("Saved all player data");
    }

    public static void loadAll() {
        for (NewOfflinePlayer newOfflinePlayer : newOfflinePlayerMap.values()) {
            newOfflinePlayer.load();
        }

        CommediaDell_arte.sendLog("Loaded all player data");
    }

    private final PlayerData playerData;
    private final boolean isClown;
    private NewOfflinePlayer(OfflinePlayer offlinePlayer) {
        super(offlinePlayer);
        playerData = new PlayerData(offlinePlayer);
        playerData.load();
        this.isClown = clowns.contains(offlinePlayer.getUniqueId());
    }

    public APlayer getNewPlayer() {
        return this.isOnline() ? APlayer.getNewPlayer(this.getPlayer()) : null;
    }

    public boolean isClown() {
        return this.isClown;
    }

    public PlayerData getPlayerData() {
        return playerData;
    }

    public DataMap getDataMap() {
        return playerData.getDataMap();
    }

    public CoolTime getCoolTime() {
        return playerData.getCoolTime();
    }

    public void save() {
        playerData.save();
    }

    public void load() {
        playerData.load();
    }
}
