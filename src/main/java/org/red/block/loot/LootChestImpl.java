package org.red.block.loot;

import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Chest;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.red.CommediaDell_arte;
import org.red.library.A_;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.a_.world.A_World;
import org.red.library.block.loot.LootChest;

import java.util.HashMap;
import java.util.Map;

public class LootChestImpl implements LootChest, ConfigurationSerializable {
    private final Chest chest;
    private final double coolTime;
    private final NamespacedKey key;
    public LootChestImpl(String name, Chest chest, double coolTime) {
        this.chest = chest;
        this.coolTime = coolTime;
        this.key = new NamespacedKey(CommediaDell_arte.getPlugin(), name);
    }


    @Override
    public Chest getChest() {
        return this.chest;
    }

    @Override
    public ItemStack[] reward() {
        return this.chest.getInventory().getStorageContents();
    }

    @Override
    public Location getLocation() {
        return this.chest.getLocation();
    }

    @Override
    public A_World getWorld() {
        return A_.getAWorld(this.getLocation().getWorld());
    }

    @Override
    public double getCoolTime() {
        return this.coolTime;
    }

    @Override
    public double getLessCoolTime(A_Player player) {
        return player.getCoolTime().getLessCoolTime("loot_chest_" + getKey());
    }

    @Override
    public void setCoolTime(A_Player player, double time) {
        player.getCoolTime().setCoolTime("loot_chest_" + getKey(), time);
    }

    @Override
    public boolean canOpen(A_Player player) {
        return player.getCoolTime().checkCoolTime("loot_chest_" + getKey());
    }

    @Override
    public BoxRestrictionType getType() {
        return null;
    }

    @Override
    public void giveReward(A_Player player) {
        this.setCoolTime(player, this.getCoolTime());
        player.openInventory(new LootChestGUI(this));
    }

    @Override
    public String name() {
        return this.key.getKey();
    }

    @NotNull
    @Override
    public NamespacedKey getKey() {
        return this.key;
    }

    @NotNull
    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("location", this.getLocation());
        map.put("coolTime", this.coolTime);
        map.put("name", this.key.getKey());
        return map;
    }

    @NotNull
    public static LootChestImpl deserialize(Map<String, Object> map) {
        return new LootChestImpl(
                (String) map.get("name"),
                (Chest) ((Location) map.get("location")).getBlock().getState(),
                (double) map.get("coolTime")
        );
    }
}
