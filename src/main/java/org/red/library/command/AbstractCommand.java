package org.red.library.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class AbstractCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        return onCommand(sender, label, args);
    }

    @NotNull
    public abstract String getName();

    public abstract boolean onCommand(@NotNull CommandSender sender, @NotNull String label, String[] args);

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args)  {
        return onTabComplete(sender, label, args);
    }

    public abstract List<String> onTabComplete(@NotNull CommandSender sender, @NotNull String label, String[] args);
}
