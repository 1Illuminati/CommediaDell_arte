package org.red.library;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.red.library.event.listener.block.BlockBreakListener;
import org.red.library.event.listener.block.BlockPlaceListener;
import org.red.library.event.listener.entity.EntityDamageByEntityListener;
import org.red.library.event.listener.inventory.InventoryClickListener;
import org.red.library.event.listener.inventory.InventoryCloseListener;
import org.red.library.event.listener.inventory.InventoryOpenListener;
import org.red.library.event.listener.player.*;

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
        this.setEvent();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerEvent(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, this);
    }

    private void setEvent() {
        this.registerEvent(new InventoryClickListener());
        this.registerEvent(new InventoryCloseListener());
        this.registerEvent(new InventoryOpenListener());

        this.registerEvent(new EntityDamageByEntityListener());

        this.registerEvent(new PlayerCommandPreProcessListener());
        this.registerEvent(new PlayerDropItemListener());
        this.registerEvent(new PlayerInteractListener());
        this.registerEvent(new PlayerFishListener());
        this.registerEvent(new PlayerQuitListener());
        this.registerEvent(new PlayerJoinListener());
        this.registerEvent(new AsyncPlayerChatListener());
        this.registerEvent(new RunEventItemListener());
        this.registerEvent(new PlayerSwapHandItemsListener());

        this.registerEvent(new BlockBreakListener());
        this.registerEvent(new BlockPlaceListener());
    }
}
