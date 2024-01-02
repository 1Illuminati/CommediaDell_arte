package org.red.mcarea.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.red.library.A_;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.command.AbstractPlayerCommand;
import org.red.mcarea.item.EquipmentUpgradeInv;
import org.red.mcarea.item.Items;

import java.util.Collections;
import java.util.List;

public class TestCommand extends AbstractPlayerCommand {
    @Override
    public @NotNull String getName() {
        return "mcareatest";
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull String label, String[] args) {
        A_Player aPlayer = A_.getAPlayer((Player) sender);
        switch (args[0]) {
            case "upgrade":
                aPlayer.openInventory(new EquipmentUpgradeInv());
            break;
            case "defaultEquipment":
                switch (args[1]) {
                    case "sword":
                        aPlayer.addItem(Items.defaultSword);
                    break;
                    case "axe":
                        aPlayer.addItem(Items.defaultAxe);
                    break;
                    case "trident":
                        aPlayer.addItem(Items.defaultTrident);
                    break;
                    case "scythe":
                        aPlayer.addItem(Items.defaultScythe);
                    break;
                    case "bow":
                        aPlayer.addItem(Items.defaultBow);
                    break;
                    case "crossbow":
                        aPlayer.addItem(Items.defaultCrossBow);
                    break;
                    case "shield":
                        aPlayer.addItem(Items.defaultShield);
                    break;
                    case "helmet":
                        aPlayer.addItem(Items.defaultHelmet);
                    break;
                    case "chestplate":
                        aPlayer.addItem(Items.defaultChestplate);
                    break;
                    case "leggings":
                        aPlayer.addItem(Items.defaultLeggings);
                    break;
                    case "boots":
                        aPlayer.addItem(Items.defaultBoots);
                    break;
                }
            break;
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull String label, String[] args) {
        return Collections.emptyList();
    }
}
