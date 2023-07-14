package org.red.library.game;

import org.red.library.entity.player.offline.NewOfflinePlayer;

import java.util.List;

public interface GameSpecter {
    List<NewOfflinePlayer> getSpecterPlayers();
}
