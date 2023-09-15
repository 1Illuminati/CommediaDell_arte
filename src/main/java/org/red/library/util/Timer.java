package org.red.library.util;

import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.Nullable;

public interface Timer {
    NamespacedKey getKey();
    void start();
    void stop();

    boolean isRunning();

    void addTime(int time);
    void addMaxTime(int maxTime);
    void setTime(int time);
    void setMaxTime(int maxTime);
    int getTime();
    int getMaxTime();
    Runnable getRunnable();
}
