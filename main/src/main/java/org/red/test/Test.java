package org.red.test;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.red.CommediaDell_arte;

import java.util.*;

public class Test {
    public static void start() {
        Bukkit.getPluginCommand("test").setExecutor(new TestCommand());
        Bukkit.getPluginManager().registerEvents(new TestEvent(), CommediaDell_arte.getPlugin());
    }
}
