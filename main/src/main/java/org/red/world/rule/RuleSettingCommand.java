package org.red.world.rule;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.red.library.A_;
import org.red.library.a_.world.A_World;
import org.red.library.command.AbstractPlayerCommand;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RuleSettingCommand extends AbstractPlayerCommand {
    @Override
    public @NotNull String getName() {
        return "ruleSetting";
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull String label, String[] args) {
        A_World aWorld = A_.getAWorld(Bukkit.getWorld(args[0]));
        ((Player) sender).openInventory(new RuleSettingGui(aWorld).getInventory());
        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull String label, String[] args) {
        return args.length == 1 ? Bukkit.getWorlds().stream().map(World::getName).collect(Collectors.toList()) : Collections.emptyList();
    }
}
