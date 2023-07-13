package org.red.library.game;

import org.bukkit.boss.KeyedBossBar;
import org.red.library.util.Timer;

public interface GameTimer {
    Timer getTimer();

    KeyedBossBar getBossBar();
}
