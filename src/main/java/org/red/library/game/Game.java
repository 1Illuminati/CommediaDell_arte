package org.red.library.game;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.Plugin;
import org.red.library.entity.player.offline.NewOfflinePlayer;

import java.util.*;

public abstract class Game {
    private final UUID gameID = UUID.randomUUID();
    private final List<NewOfflinePlayer> joinPlayers = new ArrayList<>();
    private final List<NewOfflinePlayer> specterPlayers = new ArrayList<>();
    private GameStatus gameStatus = GameStatus.WAITING;

    public List<NewOfflinePlayer> getJoinPlayers() {
        return joinPlayers;
    }

    public List<NewOfflinePlayer> getSpecterPlayers() {
        return specterPlayers;
    }

    public List<NewOfflinePlayer> getPlayers() {
        List<NewOfflinePlayer> players = new ArrayList<>();
        players.addAll(joinPlayers);
        players.addAll(specterPlayers);
        return players;
    }

    public UUID getGameID() {
        return gameID;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public boolean isJoinable() {
        return gameStatus == GameStatus.WAITING;
    }

    public void sendMessage(String message, List<NewOfflinePlayer> players) {
        players.forEach(player -> {
            if (player.isOnline())
                player.getNewPlayer().sendMessage(message);
        });
    }

    public void sendMessage(String message) {
        sendMessage(message, getPlayers());
    }

    public void sendTitle(String title, String subtitle, int fadeIn, int stay, int fadeOut, List<NewOfflinePlayer> players) {
        players.forEach(player -> {
            if (player.isOnline())
                player.getNewPlayer().sendTitle(title, subtitle, fadeIn, stay, fadeOut);
        });
    }

    public void sendTitle(String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        sendTitle(title, subtitle, fadeIn, stay, fadeOut, getPlayers());
    }

    public void sendActionBar(String message, List<NewOfflinePlayer> players) {
        players.forEach(player -> {
            if (player.isOnline())
                player.getNewPlayer().sendActionBar(message);
        });
    }

    public void sendActionBar(String message) {
        sendActionBar(message, getPlayers());
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public abstract Plugin getPlugin();
    public abstract String getName();
    public abstract void start();
    public abstract void stop();

    public enum GameStatus {
        WAITING,
        STARTING,
        RUNNING,
        STOPPING,
        STOPPED
    }
}
