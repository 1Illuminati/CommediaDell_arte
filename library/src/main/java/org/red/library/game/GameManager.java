package org.red.library.game;

import org.bukkit.Bukkit;
import org.red.library.A_;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class GameManager {
    private static final GameManager gameManager = new GameManager();

    public static GameManager getInstance() {
        return gameManager;
    }

    private final Map<Class<? extends Game>, Map<UUID, Game>> games = new HashMap<>();

    private GameManager() {}

    private Map<UUID, Game> getGameMap(Game game) {
        return games.computeIfAbsent(game.getClass(), k -> new HashMap<>());
    }

    public void startGame(Game game) {
        Map<UUID, Game> gameMap = getGameMap(game);
        gameMap.put(game.getGameID(), game);
        game.setGameStatus(Game.GameStatus.STARTING);

        if (game instanceof GameArea) {
            GameArea gameArea = (GameArea) game;
            gameArea.getArea().forEach(area -> A_.getAWorld(area.getWorld()).putArea(area));
        }

        Bukkit.getScheduler().runTask(game.getPlugin(), () -> {
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
            gameArea.getArea().forEach(area -> A_.getAWorld(area.getWorld()).removeArea(area.getKey()));
        }

        Bukkit.getScheduler().runTask(game.getPlugin(), () -> {
            game.stop();
            game.setGameStatus(Game.GameStatus.STOPPED);
        });
    }
}
