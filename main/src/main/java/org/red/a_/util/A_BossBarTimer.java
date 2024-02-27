package org.red.a_.util;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BossBar;
import org.bukkit.scheduler.BukkitRunnable;
import org.red.CommediaDell_arte;
import org.red.library.event.TimerEndEvent;
import org.red.library.util.timer.BossBarTimer;

import java.util.ArrayList;
import java.util.List;

public class A_BossBarTimer extends A_Timer implements BossBarTimer {
    private final List<BossBar> bossBars = new ArrayList<>();
    public A_BossBarTimer(NamespacedKey key, int maxTime) {
        super(key, maxTime);
    }

    public A_BossBarTimer(NamespacedKey key, int maxTime, Runnable runnable) {
        super(key, maxTime, runnable);
    }

    @Override
    public void addBossBar(BossBar bossBar) {
        this.bossBars.add(bossBar);
    }

    @Override
    public List<BossBar> getBossBars() {
        return bossBars;
    }

    @Override
    public void removeBossBar(BossBar bossBar) {
        this.bossBars.remove(bossBar);
    }

    @Override
    public boolean containsBossBar(BossBar bossBar) {
        return this.bossBars.contains(bossBar);
    }

    @Override
    public void start() {
        this.setRunning(true);
        new BukkitRunnable() {
            @Override
            public void run() {
                addTime(1);
                getRunnable().run();
                Bukkit.getScheduler().runTask(CommediaDell_arte.getPlugin(), () -> bossBars.forEach(bossBar -> bossBar.setProgress((double) getTime() / (double) getMaxTime())));

                if (getTime() >= getMaxTime() || !isRunning()) {
                    Bukkit.getScheduler().runTask(CommediaDell_arte.getPlugin(), () -> {
                        Bukkit.getPluginManager().callEvent(new TimerEndEvent(A_BossBarTimer.this));
                    });
                    this.cancel();
                }
            }
        }.runTaskTimerAsynchronously(CommediaDell_arte.getPlugin(), 1, 0);
    }
}
