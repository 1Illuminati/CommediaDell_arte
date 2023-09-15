package org.red.library.game;

import org.bukkit.boss.KeyedBossBar;
import org.red.util.s;

public interface GameTimer {
    s getTimer();

    KeyedBossBar getBossBar();
}
