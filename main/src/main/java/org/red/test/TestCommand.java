package org.red.test;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.red.library.A_;
import org.red.library.a_.entity.player.A_Player;

public class TestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        A_Player player = A_.getAPlayer((Player) commandSender);
        switch (strings[0]) {
            case "skin":
            break;
            case "hide":
                for (Entity entity : player.getWorld().getEntities()) {
                    if (player.getLocation().distance(entity.getLocation()) < 10) {
                        player.hideEntity(entity);
                    }
                }
            break;
            case "show":
                for (Entity entity : player.getWorld().getEntities()) {
                    if (player.getLocation().distance(entity.getLocation()) < 10) {
                        player.showEntity(entity);
                    }
                }
            break;
        }
        return false;
    }
}
