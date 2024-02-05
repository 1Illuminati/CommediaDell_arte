package org.red.work.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.red.library.command.AbstractPlayerCommand;
import org.red.work.Gui;

import java.util.ArrayList;
import java.util.List;

public class Command extends AbstractPlayerCommand {
    @Override
    public @NotNull String getName() {
        return "spawnerset";
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull String label, String[] args) {
        Player player = (Player) sender;
        player.openInventory(new Gui(player.getWorld()).getInventory());
        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull String label, String[] args) {
        return new ArrayList<>();
    }
}
