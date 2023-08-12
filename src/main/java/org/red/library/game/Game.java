package org.red.library.game;

import org.bukkit.plugin.Plugin;
import org.red.library.entity.a_.player.offline.A_OfflinePlayer;
import org.red.library.game.setting.Setting;

import java.util.*;

public abstract class Game {
    private final UUID gameID = UUID.randomUUID();
    private final List<A_OfflinePlayer> joinPlayers = new ArrayList<>();
    private GameStatus gameStatus = GameStatus.WAITING;

    public List<A_OfflinePlayer> getJoinPlayers() {
        return joinPlayers;
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

    public void sendGameMessage(String message) {
        sendMessage(gameDisplayName() + message);
    }

    public void sendGameMessage(String message, List<A_OfflinePlayer> players) {
        sendMessage(gameDisplayName() + message, players);
    }

    public void sendMessage(String message, List<A_OfflinePlayer> players) {
        players.forEach(player -> {
            if (player.isOnline())
                player.getAPlayer().sendMessage(message);
        });
    }

    public void sendMessage(String message) {
        sendMessage(message, this.getJoinPlayers());
    }

    public void sendTitle(String title, String subtitle, int fadeIn, int stay, int fadeOut, List<A_OfflinePlayer> players) {
        players.forEach(player -> {
            if (player.isOnline())
                player.getAPlayer().sendTitle(title, subtitle, fadeIn, stay, fadeOut);
        });
    }

    public void sendTitle(String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        sendTitle(title, subtitle, fadeIn, stay, fadeOut, this.getJoinPlayers());
    }

    public void sendActionBar(String message, List<A_OfflinePlayer> players) {
        players.forEach(player -> {
            if (player.isOnline())
                player.getAPlayer().sendActionBar(message);
        });
    }

    public void sendActionBar(String message) {
        sendActionBar(message, this.getJoinPlayers());
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public abstract String gameDisplayName();
    public abstract Plugin getPlugin();
    public abstract String getName();
    public abstract void start();
    public abstract void stop();
    public abstract Setting getSetting();

    public enum GameStatus {
        WAITING,
        STARTING,
        RUNNING,
        STOPPING,
        STOPPED
    }
}
