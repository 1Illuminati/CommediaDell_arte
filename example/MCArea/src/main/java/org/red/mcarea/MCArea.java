package org.red.mcarea;

import org.bukkit.plugin.java.JavaPlugin;
import org.red.mcarea.command.TestCommand;

public final class MCArea extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getCommand("mcareatest").setExecutor(new TestCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
