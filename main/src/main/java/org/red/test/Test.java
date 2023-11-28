package org.red.test;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.red.CommediaDell_arte;

import java.util.*;

public class Test {
    public static void start() {
//        Bukkit.getPluginManager().registerEvents(new TestEvent(), CommediaDell_arte.getPlugin());

        HashMap<UUID, Location> lastLocation = new HashMap<>();

        abstract class Run {
            public abstract void run();
            public abstract void exception(Exception e);
        }

        ArrayList<Runnable> runnableArrayList = new ArrayList<>();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Bukkit.getScheduler().runTask(CommediaDell_arte.getPlugin(), () -> {
                    for (Runnable runnable : runnableArrayList) {
                        try {
                            runnable.run();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }, 0L, 10L);
    }
}
