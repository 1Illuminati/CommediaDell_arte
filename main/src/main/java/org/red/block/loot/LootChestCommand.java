package org.red.block.loot;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.red.library.A_;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.a_.world.A_World;
import org.red.library.command.AbstractPlayerCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LootChestCommand extends AbstractPlayerCommand {
    @Override
    public @NotNull String getName() {
        return "lootChest";
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull String label, String[] args) {
        A_Player player = A_.getAPlayer((Player) sender);
        A_World world = A_.getAWorld(player.getLocation().getWorld());
        switch (args[0]) {
            case "create":
                Block block = player.getLocation().getBlock();
                block.setType(Material.CHEST);
                Chest chest = (Chest) block.getState();
                LootChestImpl lootChest = new LootChestImpl(args[1], chest, Double.parseDouble(args[2]));
                world.registeredLootChest(lootChest);
                player.sendMessage("§a설치 완료!");
            break;
            case "delete":
                world.removeLootChest(args[1]);
                player.sendMessage("§a제거 완료!");
            break;
            case "info":
                player.sendMessage(world.getLootChest(args[1]).getChest().toString());
            break;
            case "reset":
                world.getLootChest(args[1]).setCoolTime(A_.getAPlayer(Bukkit.getPlayer(args[2])), 0);
                player.sendMessage("§a초기화 완료!");
            break;
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull String label, String[] args) {
        int length = args.length;
        if (length == 1) return Arrays.asList("create", "delete", "info", "reset");
        else if (args[0].equals("create")) {
            return length == 2 ? Collections.singletonList("[ name ]") : Collections.singletonList("[ coolTime ]");
        } else if (length == 2) {
            A_Player player = A_.getAPlayer((Player) sender);
            List<String> result = new ArrayList<>();
            player.getAWorld().getLootChests().forEach(lootChest -> {
                result.add(lootChest.name());
            });
            return result;
        } else if (args[0].equals("reset")) {
            return length == 3 ? Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList()) : Collections.emptyList();
        }

        return Collections.emptyList();
    }
}
