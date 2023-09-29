package org.red.a_.world;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.red.library.command.AbstractPlayerCommand;

import java.util.List;

public class A_AreaCommand extends AbstractPlayerCommand {
    @Override
    public @NotNull String getName() {
        return "area";
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull String label, String[] args) {
        return false;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull String label, String[] args) {
        return null;
    }
}
