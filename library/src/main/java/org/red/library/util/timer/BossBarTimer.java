package org.red.library.util.timer;

import org.bukkit.boss.BossBar;

import java.util.List;

public interface BossBarTimer extends Timer {
    void addBossBar(BossBar bossBar);
    List<BossBar> getBossBars();
    void removeBossBar(BossBar bossBar);
    boolean containsBossBar(BossBar bossBar);
}
