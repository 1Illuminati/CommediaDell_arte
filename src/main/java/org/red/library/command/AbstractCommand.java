package org.red.library.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;

public abstract class AbstractCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return onCommand(sender, label, args);
    }

    public abstract String getName();

    public abstract boolean onCommand(CommandSender sender, String label, String[] args);

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args)  {
        return onTabComplete(sender, label, args);
    }

    public abstract List<String> onTabComplete(CommandSender sender, String label, String[] args);
}
