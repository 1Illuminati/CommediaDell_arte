package org.red.item.material;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.red.library.command.AbstractPlayerCommand;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BanMaterialCommand extends AbstractPlayerCommand {
    @Override
    public @NotNull String getName() {
        return "banItem";
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull String label, String[] args) {
        ((Player) sender).openInventory(new BanMaterialGui(Bukkit.getWorld(args[0])).getInventory());
        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull String label, String[] args) {
        return args.length == 1 ? Bukkit.getWorlds().stream().map(World::getName).collect(Collectors.toList()) : Collections.emptyList();
    }
}
