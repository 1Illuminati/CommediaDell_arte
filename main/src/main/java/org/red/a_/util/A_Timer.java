package org.red.a_.util;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.scheduler.BukkitRunnable;
import org.red.CommediaDell_arte;
import org.red.library.util.timer.Timer;

public class A_Timer implements Timer {
    private final NamespacedKey key;
    private int maxTime;
    private int time = 0;
    private boolean isRunning = false;
    private final Runnable runnable;

    public A_Timer(NamespacedKey key, int maxTime) {
        this(key, maxTime, ()->{});
    }

    public A_Timer(NamespacedKey key, int maxTime, Runnable runnable) {
        this.key = key;
        this.maxTime = maxTime;
        this.runnable = runnable;
    }

    @Override
    public NamespacedKey getKey() {
        return key;
    }

    @Override
    public void start() {
        this.setRunning(true);

        Bukkit.getScheduler().runTaskTimerAsynchronously(CommediaDell_arte.getPlugin(), new BukkitRunnable() {
            @Override
            public void run() {
                addTime(1);
                getRunnable().run();
                if (getTime() >= getMaxTime() || !isRunning()) {
                    //이벤트 코드
                    this.cancel();
                }
            }
        },1, 0);
    }

    @Override
    public void stop() {
        this.setRunning(false);
    }

    @Override
    public boolean isRunning() {
        return this.isRunning;
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    @Override
    public void addTime(int time) {
        this.setTime(this.getTime() + time);
    }

    @Override
    public void addMaxTime(int maxTime) {
        this.setMaxTime(this.getMaxTime() + maxTime);
    }

    @Override
    public void setTime(int time) {
        if (time > maxTime) throw new IllegalArgumentException("time cannot be greater than maxTime");
        this.time = time;
    }

    @Override
    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }

    @Override
    public int getTime() {
        return time;
    }

    @Override
    public int getMaxTime() {
        return maxTime;
    }

    @Override
    public Runnable getRunnable() {
        return runnable;
    }
}
