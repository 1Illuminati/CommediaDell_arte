package org.red.library.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractPlayerCommand extends AbstractCommand{
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender instanceof Player) {
            return onCommand((Player) sender, label, args);
        }

        sender.sendMessage("§cYou must be a player to use this command.");
        return false;
    }
}
