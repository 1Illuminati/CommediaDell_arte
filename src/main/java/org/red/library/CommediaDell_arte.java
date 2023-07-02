package org.red.library;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class CommediaDell_arte extends JavaPlugin {
    private static CommediaDell_arte plugin;

    public static CommediaDell_arte getPlugin() {
        return CommediaDell_arte.plugin;
    }

    private static boolean debug = true;

    public static void sendLog(Object message) {
        Bukkit.getConsoleSender().sendMessage("§c§l[ CommediaDell_arte ] §f" + message);
    }

    public static void sendDebugLog(Object message) {
        if (debug)
            Bukkit.getConsoleSender().sendMessage("§c§l[ CommediaDell_arte Debug ] §f" + message);
    }

    public static boolean isDebug() {
        return debug;
    }

    public static void setDebug(boolean debug) {
        CommediaDell_arte.debug = debug;
    }

    @Override
    public void onEnable() {
        CommediaDell_arte.plugin = this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
