package org.red.library.util;

import org.bukkit.Bukkit;
import org.red.library.CommediaDell_arte;

public class NewScheduler {
    private int repeatNum = 1;
    private int startDelay = 0;
    private int repeatDelay = 0;
    private boolean started = false;
    public NewScheduler() {}

    public NewScheduler setRepeatNum(int repeatNum) {
        this.repeatNum = repeatNum;
        return this;
    }

    public int getRepeatNum() {
        return this.repeatNum;
    }

    public NewScheduler setStartDelay(int startDelay) {
        this.startDelay = startDelay;
        return this;
    }

    public int getStartDelay() {
        return this.startDelay;
    }

    public NewScheduler setRepeatDelay(int repeatDelay) {
        this.repeatDelay = repeatDelay;
        return this;
    }

    public int getRepeatDelay() {
        return this.repeatDelay;
    }

    public NewScheduler setRepeatInfinitely() {
        this.repeatNum = -1;
        return this;
    }

    public boolean isRepeatInfinitely() {
        return this.repeatNum == -1;
    }

    public boolean isStarted() {
        return this.started;
    }

    public void stop() {
        if (!started)
            throw new IllegalStateException("Scheduler is not started");

        started = false;
    }

    public void run(Runnable runnable) {
        this.started = true;
    }

    private class RunnableEx implements Runnable {

        @Override
        public void run() {

        }
    }
}
