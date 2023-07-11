package org.red.library.entity.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.red.library.CommediaDell_arte;
import org.red.library.entity.player.npc.NpcPlayer;
import org.red.library.entity.player.offline.NewOfflinePlayer;
import org.red.library.inventory.CustomGui;
import org.red.library.util.map.CoolTime;
import org.red.library.util.map.DataMap;

import java.util.HashMap;
import java.util.UUID;

public class NewPlayer extends PlayerAdapter {
    private static final HashMap<UUID, NewPlayer> newPlayerMap = new HashMap<>();

    public static NewPlayer getNewPlayer(UUID playerUUID) {
        Player player = Bukkit.getPlayer(playerUUID);

        if (player == null)
            return null;

        return NewPlayer.getNewPlayer(player);
    }

    public static NewPlayer getNewPlayer(String playerName) {
        Player player = Bukkit.getPlayer(playerName);

        if (player == null)
            return null;

        return NewPlayer.getNewPlayer(player);
    }

    public static NewPlayer getNewPlayer(Player player) {
        if (player.hasMetadata("NPC")) {
            return NpcPlayer.getNPCPlayer(player);
        }

        if (!newPlayerMap.containsKey(player.getUniqueId()))
            newPlayerMap.put(player.getUniqueId(), new NewPlayer(player));
        else if (!newPlayerMap.get(player.getUniqueId()).getPlayer().equals(player))
            newPlayerMap.put(player.getUniqueId(), new NewPlayer(player));

        return newPlayerMap.get(player.getUniqueId());
    }

    private final NewOfflinePlayer newOfflinePlayer;
    private final PlayerData playerData;
    private boolean ignoreCloseEvent = false;
    protected NewPlayer(Player player) {
        super(player);
        this.newOfflinePlayer = NewOfflinePlayer.getNewOfflinePlayer(player);
        this.playerData = newOfflinePlayer.getPlayerData();
    }

    public void openInventory(CustomGui gui) {
        this.openInventory(gui.getInventory());
    }

    public void delayOpenInventory(CustomGui gui) {
        this.delayOpenInventory(gui.getInventory(), false);
    }

    public void delayOpenInventory(CustomGui gui, boolean ignoreCloseEvent) {
        this.delayOpenInventory(gui.getInventory(), ignoreCloseEvent);
    }

    public void delayOpenInventory(Inventory inv) {
        this.delayOpenInventory(inv, false);
    }

    public void delayOpenInventory(Inventory inv, boolean ignoreCloseEvent) {
        Bukkit.getScheduler().runTaskLater(CommediaDell_arte.getPlugin(), () -> {
            openInventory(inv);

            if (ignoreCloseEvent)
                setIgnoreCloseEvent(true);
        }, 2L);
    }

    public boolean isIgnoreCloseEvent() {
        return ignoreCloseEvent;
    }

    public void setIgnoreCloseEvent(boolean ignoreCloseEvent) {
        this.ignoreCloseEvent = ignoreCloseEvent;
    }

    public NewOfflinePlayer getNewOfflinePlayer() {
        return newOfflinePlayer;
    }

    public DataMap getDataMap() {
        return newOfflinePlayer.getDataMap();
    }

    public CoolTime getCoolTime() {
        return newOfflinePlayer.getCoolTime();
    }

    public PlayerData getPlayerData() {
        return playerData;
    }

    public boolean isClown() {
        return newOfflinePlayer.isClown();
    }

    public boolean isNpc() {
        return false;
    }
}
