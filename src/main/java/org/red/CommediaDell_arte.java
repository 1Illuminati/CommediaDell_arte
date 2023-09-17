package org.red;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.red.a_.A_Manager;
import org.red.a_.vault.A_Economy;
import org.red.event.listener.block.BlockBreakListener;
import org.red.event.listener.block.BlockPlaceListener;
import org.red.event.listener.entity.EntityDamageByEntityListener;
import org.red.event.listener.entity.EntityDamageListener;
import org.red.event.listener.entity.EntityMountListener;
import org.red.event.listener.inventory.InventoryClickListener;
import org.red.event.listener.inventory.InventoryCloseListener;
import org.red.event.listener.inventory.InventoryOpenListener;
import org.red.event.listener.player.*;
import org.red.library.A_;

import java.io.File;

public final class CommediaDell_arte extends A_ {
    private static CommediaDell_arte plugin;

    public static CommediaDell_arte getPlugin() {
        return CommediaDell_arte.plugin;
    }

    private static boolean debug = true;

    public static void sendLog(Object message) {
        Bukkit.getConsoleSender().sendMessage("§c§l[ §cCommediaDell_arte §c§l] §f" + message);
    }

    public static void sendDebugLog(Object message) {
        if (debug)
            Bukkit.getConsoleSender().sendMessage("§c§l[ §cCommediaDell_arte Debug §c§l] §f" + message);
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
        this.createFile();
        A_Manager.INSTANCE.allLoad();

        if (checkSoftPlugin("Vault")) A_Economy.setEconomy();
    }

    @Override
    public void onDisable() {
        A_Manager.INSTANCE.allSave();
    }

    private boolean checkSoftPlugin(String pluginName) {
        return Bukkit.getPluginManager().getPlugin(pluginName) != null;
    }

    private void registerEvent(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, this);
    }

    private void setEvent() {
        this.registerEvent(new InventoryClickListener());
        this.registerEvent(new InventoryCloseListener());
        this.registerEvent(new InventoryOpenListener());

        this.registerEvent(new EntityDamageByEntityListener());
        this.registerEvent(new EntityDamageListener());
        this.registerEvent(new EntityMountListener());

        this.registerEvent(new PlayerCommandPreProcessListener());
        this.registerEvent(new PlayerDropItemListener());
        this.registerEvent(new PlayerInteractListener());
        this.registerEvent(new PlayerFishListener());
        this.registerEvent(new PlayerQuitListener());
        this.registerEvent(new PlayerJoinListener());
        this.registerEvent(new PlayerMoveListener());
        this.registerEvent(new AsyncPlayerChatListener());
        this.registerEvent(new RunEventItemListener());
        this.registerEvent(new PlayerSwapHandItemsListener());

        this.registerEvent(new BlockBreakListener());
        this.registerEvent(new BlockPlaceListener());
    }

    private void createFile() {
        boolean value1 = new File("plugins/Dell_arte").mkdir();
        boolean value2 = new File("plugins/Dell_arte/playerData").mkdir();
        boolean value3 = new File("plugins/Dell_arte/npcData").mkdir();
        boolean value4 = new File("plugins/Dell_arte/worldData").mkdir();

        sendLog(value1 ? "§aCreate Folder: Dell_arte" : "§aChecked Folder: Dell_arte");
        sendLog(value2 ? "§aCreate Folder: playerData" : "§aChecked Folder: playerData");
        sendLog(value3 ? "§aCreate Folder: npcData" : "§aChecked Folder: npcData");
        sendLog(value4 ? "§aCreate Folder: worldData" : "§aChecked Folder: worldData");
    }
}
