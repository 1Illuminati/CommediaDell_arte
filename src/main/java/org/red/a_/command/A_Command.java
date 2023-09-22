package org.red.a_.command;

import org.apache.commons.lang.NotImplementedException;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.red.library.A_;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.command.AbstractPlayerCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class A_Command extends AbstractPlayerCommand {
    @Override
    public @NotNull String getName() {
        return "a_";
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull String label, String[] args) {
        switch (args[0]) {
            case "economy":
                A_Player player = A_.getAPlayer(args[1]);

                switch (args[2]) {
                    case "get":
                        sender.sendMessage(player.getName() + ": " + player.getEconomyAccount().getBalance());
                    break;
                    case "set":
                        player.getEconomyAccount().setBalance(Integer.parseInt(args[3]));
                        sender.sendMessage("설정 완료");
                    break;
                    case "add":
                        player.getEconomyAccount().addBalance(Integer.parseInt(args[3]));
                        sender.sendMessage("설정 완료");
                    break;
                    default: return false;
                }
            return true;
            case "data":
                throw new NotImplementedException(); // TODO
            default: return false;
        }
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull String label, String[] args) {
        if (args.length == 1) return Arrays.asList("economy", "data");
        if (args[0].equals("economy")) {
            if (args.length == 2) return null;
            if (args.length == 3) return Arrays.asList("get", "set", "add");
            if (args.length == 4) return Collections.singletonList("[INT]");
        }

        return new ArrayList<>();
    }
}
