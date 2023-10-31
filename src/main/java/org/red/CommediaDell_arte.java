package org.red;

import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.red.a_.A_ManagerImpl;
import org.red.a_.admin.AdminAxe;
import org.red.a_.command.A_Command;
import org.red.a_.placeholder.A_PlaceHolderPlayer;
import org.red.a_.vault.A_Economy;
import org.red.a_.vault.A_EconomyAccount;
import org.red.a_.world.A_Area;
import org.red.block.loot.LootChestCommand;
import org.red.block.loot.LootChestImpl;
import org.red.event.listener.AbstractListener;
import org.red.event.listener.block.BlockBreakListener;
import org.red.event.listener.block.BlockPlaceListener;
import org.red.event.listener.entity.EntityDamageByEntityListener;
import org.red.event.listener.entity.EntityDamageListener;
import org.red.event.listener.entity.EntityMountListener;
import org.red.event.listener.entity.EntitySpawnListener;
import org.red.event.listener.inventory.CraftItemListener;
import org.red.event.listener.inventory.InventoryClickListener;
import org.red.event.listener.inventory.InventoryCloseListener;
import org.red.event.listener.inventory.InventoryOpenListener;
import org.red.event.listener.player.*;
import org.red.item.material.BanMaterial;
import org.red.item.material.BanMaterialCommand;
import org.red.library.A_;
import org.red.library.a_.A_Data;
import org.red.library.command.AbstractCommand;
import org.red.library.util.map.CoolTime;
import org.red.library.util.map.DataMap;
import org.red.library.util.map.NameSpaceMap;
import org.red.library.world.rule.RuleMap;
import org.red.world.rule.RuleSettingCommand;

import java.io.File;

public final class CommediaDell_arte extends JavaPlugin {
    private static A_ManagerImpl PLUGIN_MANAGER;

    public static Plugin getPlugin() {
        return PLUGIN_MANAGER.getPlugin();
    }

    public static void sendLog(Object message) {
        Bukkit.getConsoleSender().sendMessage("§c[ CommediaDell_arte Log ] §f" + message);
    }

    public static void sendDebugLog(Object message) {
        if (Setting.DEBUG.asBooleanValue())
            Bukkit.getConsoleSender().sendMessage("§c[ CommediaDell_arte Debug ] §f" + message);
    }

    public static void sendErrorLog(Object message) {
        Bukkit.getConsoleSender().sendMessage("§c[ CommediaDell_arte Error ] §f" + message);
    }

    @Override
    public void onEnable() {
        this.settingConfig();
        this.configurationSerializationSetting();
        this.checkedFile();
        PLUGIN_MANAGER = new A_ManagerImpl(this);
        this.setCommand();
        this.setEvent();
        setSoftPlugin();

        Bukkit.getScheduler().runTaskLater(this, () -> {
            PLUGIN_MANAGER.allLoad();
            A_.setA_Plugin(PLUGIN_MANAGER);
            A_Area.loadArea();
            AdminAxe.load();
            intermediateStorage();
        }, 20);
    }

    @Override
    public void onDisable() {
        A_Area.saveArea();
        PLUGIN_MANAGER.allSave();
    }

    private void setSoftPlugin() {
        if(softPluginCheck("Vault")) A_Economy.setEconomy();
        if(softPluginCheck("PlaceholderAPI")) A_PlaceHolderPlayer.setPlaceHolder();
    }

    private boolean softPluginCheck(String plName) {
        return Bukkit.getPluginManager().getPlugin(plName) != null;
    }

    private void intermediateStorage() {
        new BukkitRunnable() {
            @Override
            public void run() {
                PLUGIN_MANAGER.allSave();
                CommediaDell_arte.sendLog("§aAll Data Intermediate Save");
            }
        }.runTaskTimer(this, 6000, 6000);
    }

    private void registerCommand(AbstractCommand command) {
        PluginCommand cmd = this.getCommand(command.getName());
        if (cmd == null) throw new NullPointerException("Command is null");
        cmd.setExecutor(command);
        cmd.setTabCompleter(command);
        sendDebugLog("Register Command: " + command.getName());
    }

    private void setCommand() {
        this.registerCommand(new A_Command());
        this.registerCommand(new BanMaterialCommand());
        this.registerCommand(new LootChestCommand());
        this.registerCommand(new RuleSettingCommand());
        sendLog("Setting All Command");
    }

    private void registerEvent(AbstractListener<?> listener) {
        Bukkit.getPluginManager().registerEvents(listener, this);
        sendDebugLog("Register Event: " + listener.getClass().getSimpleName());
    }

    private void setEvent() {
        this.registerEvent(new InventoryClickListener());
        this.registerEvent(new InventoryCloseListener());
        this.registerEvent(new InventoryOpenListener());

        this.registerEvent(new CraftItemListener());
        this.registerEvent(new EntityDamageByEntityListener());
        this.registerEvent(new EntityDamageListener());
        this.registerEvent(new EntityMountListener());
        this.registerEvent(new EntitySpawnListener());

        this.registerEvent(new PlayerCommandPreProcessListener());
        this.registerEvent(new PlayerDropItemListener());
        this.registerEvent(new PlayerInteractListener());
        this.registerEvent(new PlayerFishListener());
        this.registerEvent(new PlayerQuitListener());
        this.registerEvent(new PlayerJoinListener());
        this.registerEvent(new PlayerMoveListener());
        this.registerEvent(new AsyncPlayerChatListener());
        this.registerEvent(new InteractiveRunListener());
        this.registerEvent(new PlayerSwapHandItemsListener());
        this.registerEvent(new LootChestOpenListener());

        this.registerEvent(new BlockBreakListener());
        this.registerEvent(new BlockPlaceListener());
        sendLog("Setting All Event");
    }

    private void checkedFile() {
        boolean value1 = new File("plugins/Dell_arte").mkdir();
        boolean value2 = new File("plugins/Dell_arte/playerData").mkdir();
        boolean value3 = new File("plugins/Dell_arte/npcData").mkdir();

        sendDebugLog(value1 ? "§aCreate Folder: Dell_arte" : "§aChecked Folder: Dell_arte");
        sendDebugLog(value2 ? "§aCreate Folder: playerData" : "§aChecked Folder: playerData");
        sendDebugLog(value3 ? "§aCreate Folder: npcData" : "§aChecked Folder: npcData");
        sendLog("Checked All Folder");
    }

    private void configurationSerializationSetting() {
        ConfigurationSerialization.registerClass(DataMap.class);
        ConfigurationSerialization.registerClass(CoolTime.class);
        ConfigurationSerialization.registerClass(RuleMap.class);
        ConfigurationSerialization.registerClass(A_EconomyAccount.class);
        ConfigurationSerialization.registerClass(A_Data.class);
        ConfigurationSerialization.registerClass(LootChestImpl.class);
        ConfigurationSerialization.registerClass(NameSpaceMap.class);
        ConfigurationSerialization.registerClass(BanMaterial.class);
        ConfigurationSerialization.registerClass(A_Area.class);
        sendLog("Setting All ConfigurationSerialization");
    }

    private void settingConfig() {
        File file = new File("plugins/Dell_arte/config.yml");

        if (file.exists()) {
            this.saveDefaultConfig();
            CommediaDell_arte.sendDebugLog("§aCreate config.yml");
        }

        Setting.loadNewConfig(this.getConfig());
        sendLog("Setting All Config");
    }
}
