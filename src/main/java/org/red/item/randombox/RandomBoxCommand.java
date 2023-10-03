package org.red.item.randombox;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.red.library.command.AbstractPlayerCommand;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RandomBoxCommand extends AbstractPlayerCommand {
    @Override
    public @NotNull String getName() {
        return "randomBox";
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull String label, String[] args) {
        return false;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull String label, String[] args) {
        int length = args.length;
        if (length == 1) return Arrays.asList("create", "delete", "set");

        return Collections.emptyList();
    }
}
