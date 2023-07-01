package org.red.library;

import org.bukkit.plugin.java.JavaPlugin;

public final class CommediaDell_arte extends JavaPlugin {
    private static CommediaDell_arte plugin;

    public static CommediaDell_arte getPlugin() {
        return CommediaDell_arte.plugin;
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
