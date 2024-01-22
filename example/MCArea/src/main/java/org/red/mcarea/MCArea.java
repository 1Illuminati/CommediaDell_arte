package org.red.mcarea;

import org.bukkit.plugin.java.JavaPlugin;
import org.red.mcarea.command.TestCommand;

public final class MCArea extends JavaPlugin {

    public static MCArea instance;

    @Override
    public void onEnable() {
        MCArea.instance = this;
        this.getCommand("mcareatest").setExecutor(new TestCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
