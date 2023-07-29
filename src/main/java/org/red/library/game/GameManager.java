package org.red.library.game;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.KeyedBossBar;
import org.red.library.entity.player.offline.NewOfflinePlayer;
import org.red.library.util.Timer;
import org.red.library.util.map.NameSpaceMap;
import org.red.library.world.Area;
import org.red.library.world.WorldData;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class GameManager {
    private static final GameManager gameManager = new GameManager();

    public static GameManager getInstance() {
        return gameManager;
    }

    private final NameSpaceMap<Map<UUID, Game>> games = new NameSpaceMap<>();

    private GameManager() {}

    private Map<UUID, Game> getGameMap(Game game) {
        return games.computeIfAbsent(new NamespacedKey(game.getPlugin(), game.getName()), k -> new HashMap<>());
    }

    public Game getPlayerJoinedGame(NewOfflinePlayer player) {
        return games.values().stream().flatMap(map -> map.values().stream()).filter(game -> game.getJoinPlayers().contains(player)).findFirst().orElse(null);
    }

    public void startGame(Game game) {
        Map<UUID, Game> gameMap = getGameMap(game);
        gameMap.put(game.getGameID(), game);
        game.setGameStatus(Game.GameStatus.STARTING);

        if (game instanceof GameArea) {
            GameArea gameArea = (GameArea) game;
            gameArea.getArea().forEach(area -> {
                WorldData worldData = WorldData.getWorldData(area.getWorld());
                worldData.putArea(area);
            });
        }

        Bukkit.getScheduler().runTask(game.getPlugin(), () -> {
            if (game instanceof GameTimer) {
                GameTimer gameTimer = (GameTimer) game;
                Timer timer = gameTimer.getTimer();
                KeyedBossBar bossBar = gameTimer.getBossBar();
                timer.addBossBar(bossBar);
                timer.start();

                game.getJoinPlayers().forEach(player -> {
                    if (player.isOnline())
                        bossBar.addPlayer(player.getNewPlayer().getPlayer());
                });
            }

            game.start();
            game.setGameStatus(Game.GameStatus.RUNNING);
        });
    }

    public void stopGame(Game game) {
        Map<UUID, Game> gameMap = getGameMap(game);
        gameMap.remove(game.getGameID());
        game.setGameStatus(Game.GameStatus.STOPPING);

        if (game instanceof GameArea) {
            GameArea gameArea = (GameArea) game;
            gameArea.getArea().forEach(area -> {
                WorldData worldData = WorldData.getWorldData(area.getWorld());
                worldData.removeArea(area.getKey());
            });
        }

        Bukkit.getScheduler().runTask(game.getPlugin(), () -> {
            game.stop();
            game.setGameStatus(Game.GameStatus.STOPPED);

            if (game instanceof GameTimer) {
                GameTimer gameTimer = (GameTimer) game;
                Timer timer = gameTimer.getTimer();
                KeyedBossBar bossBar = gameTimer.getBossBar();
                timer.getBossBars().clear();
                timer.stop();
                bossBar.removeAll();
            }
        });
    }
}
