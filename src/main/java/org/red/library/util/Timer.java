package org.red.library.util;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BossBar;
import org.bukkit.boss.KeyedBossBar;
import org.red.library.CommediaDell_arte;
import org.red.library.event.TimerEndEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Timer {
    private static final Map<NamespacedKey, Timer> timersMap = new HashMap<>();

    public static Timer getTimer(NamespacedKey key) {
        return timersMap.get(key);
    }

    public static void registerTimer(Timer timer) {
        timersMap.put(timer.getKey(), timer);
    }

    private final NamespacedKey key;
    private final List<BossBar> bossBars = new ArrayList<>();
    private int maxTime;
    private int time = 0;
    private boolean stop = false;
    public Timer(NamespacedKey key, int maxTime) {
        this.key = key;
        this.maxTime = maxTime;
    }

    public NamespacedKey getKey() {
        return key;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }

    public void reset() {
        this.time = 0;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void addTime(int time) {
        this.time += time;
        this.time = Math.min(this.time, maxTime);
        this.time = Math.max(this.time, 0);
    }

    public void addMaxTime(int time) {
        this.maxTime += time;
        this.maxTime = Math.max(this.maxTime, 0);
    }

    public void stop() {
        TimerEndEvent event = new TimerEndEvent(this);
        Bukkit.getPluginManager().callEvent(event);
        this.stop = !event.isCancelled();
    }

    public List<BossBar> getBossBars() {
        return bossBars;
    }

    public void addBossBar(BossBar bossBar) {
        bossBars.add(bossBar);
    }

    public void start() {
        Bukkit.getScheduler().runTaskAsynchronously(CommediaDell_arte.getPlugin(), () -> {
            while (!stop) {
                try {
                    Thread.sleep(1);

                    for (BossBar bossBar : this.bossBars) {
                        bossBar.setProgress((double) time / maxTime);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                addTime(1);

                if (time >= maxTime) {
                    stop();
                }
            }
        });
    }
}
