package org.red.work;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.red.library.A_;
import org.red.library.a_.world.A_World;
import org.red.work.command.Command;

import java.util.ArrayList;
import java.util.Random;

public final class SpawnerDropItemSet extends JavaPlugin implements Listener {
    public static SpawnerDropItemSet plugin;

    @Override
    public void onEnable() {
        plugin = this;
        Command command = new Command();
        Bukkit.getPluginCommand(command.getName()).setExecutor(command);
        Bukkit.getPluginCommand(command.getName()).setTabCompleter(command);
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void breakBlockEvent(BlockBreakEvent event) {
        Block breakBlock = event.getBlock();
        if (breakBlock.getType() == Material.SPAWNER) {
            event.setCancelled(true);
            A_World world = A_.getAWorld(breakBlock.getWorld());
            ArrayList<ItemStack> itemStacks = (ArrayList<ItemStack>) world.getDataMap().getList("spawner_drop_item_set", new ArrayList<ItemStack>());
            ItemStack result = itemStacks.get(new Random().nextInt(0, itemStacks.size()));

            breakBlock.setType(Material.CHEST);
            Chest chest = (Chest) breakBlock.getState();
            chest.getInventory().addItem(result);
        }
    }
}
