package org.red;

import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.event.player.PlayerJoinEvent;
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
    private static CommediaDell_arte plugin;

    public static CommediaDell_arte getPlugin() {
        return CommediaDell_arte.plugin;
    }

    private static boolean debug = true;

    public static void sendLog(Object message) {
        Bukkit.getConsoleSender().sendMessage("§c[ CommediaDell_arte ] §f" + message);
    }

    public static void sendDebugLog(Object message) {
        if (debug)
            Bukkit.getConsoleSender().sendMessage("§c[ CommediaDell_arte Debug ] §f" + message);
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
        this.yamlSetting();
        this.setCommand();
        this.setEvent();
        this.createFile();
        A_ManagerImpl.INSTANCE.allLoad();
        A_.setA_Plugin(A_ManagerImpl.INSTANCE);
        A_Area.loadArea();
        AdminAxe.load();
        Bukkit.getOnlinePlayers().forEach(player -> Bukkit.getPluginManager().callEvent(new PlayerJoinEvent(player, null)));
        setSoftPlugin();
        intermediateStorage();
    }

    @Override
    public void onDisable() {
        A_Area.saveArea();
        A_ManagerImpl.INSTANCE.allSave();
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
                A_ManagerImpl.INSTANCE.allSave();
                CommediaDell_arte.sendLog("§aAll Data Intermediate Save");
            }
        }.runTaskTimer(this, 6000, 6000);
    }

    private void registerCommand(AbstractCommand command) {
        PluginCommand cmd = this.getCommand(command.getName());
        if (cmd == null) throw new NullPointerException("Command is null");
        cmd.setExecutor(command);
        cmd.setTabCompleter(command);
    }

    private void setCommand() {
        this.registerCommand(new A_Command());
        this.registerCommand(new BanMaterialCommand());
        this.registerCommand(new LootChestCommand());
        this.registerCommand(new RuleSettingCommand());
    }

    private void registerEvent(AbstractListener<?> listener) {
        Bukkit.getPluginManager().registerEvents(listener, this);
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
    }

    private void createFile() {
        boolean value1 = new File("plugins/Dell_arte").mkdir();
        boolean value2 = new File("plugins/Dell_arte/playerData").mkdir();
        boolean value3 = new File("plugins/Dell_arte/npcData").mkdir();

        sendLog(value1 ? "§aCreate Folder: Dell_arte" : "§aChecked Folder: Dell_arte");
        sendLog(value2 ? "§aCreate Folder: playerData" : "§aChecked Folder: playerData");
        sendLog(value3 ? "§aCreate Folder: npcData" : "§aChecked Folder: npcData");
    }

    private void yamlSetting() {
        ConfigurationSerialization.registerClass(DataMap.class);
        ConfigurationSerialization.registerClass(CoolTime.class);
        ConfigurationSerialization.registerClass(RuleMap.class);
        ConfigurationSerialization.registerClass(A_EconomyAccount.class);
        ConfigurationSerialization.registerClass(A_Data.class);
        ConfigurationSerialization.registerClass(LootChestImpl.class);
        ConfigurationSerialization.registerClass(NameSpaceMap.class);
        ConfigurationSerialization.registerClass(BanMaterial.class);
        ConfigurationSerialization.registerClass(A_Area.class);
    }
}
