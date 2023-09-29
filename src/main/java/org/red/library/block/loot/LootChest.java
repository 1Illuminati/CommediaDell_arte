package org.red.library.block.loot;

import org.bukkit.Keyed;
import org.bukkit.Location;
import org.bukkit.block.Chest;
import org.bukkit.inventory.ItemStack;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.a_.world.A_World;

/**
 * LootChest
 */
public interface LootChest extends Keyed {
    Chest getChest();
    ItemStack[] reward();
    Location getLocation();
    A_World getWorld();
    double getCoolTime();
    double getLessCoolTime(A_Player player);
    void setCoolTime(A_Player player, double time);
    boolean canOpen(A_Player player);
    void giveReward(A_Player player);
    String name();
}
