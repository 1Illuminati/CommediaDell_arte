package org.red.a_.admin;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.red.CommediaDell_arte;
import org.red.library.item.ItemBuilder;

public class AdminAxe extends AdminSameAbility{
    public static final ItemStack ADMIN_AXE = new ItemBuilder(Material.NETHERITE_AXE).setInteractiveItem(new AdminAxe()).setDisplayName("§f관리자 도끼").build();
    public static void load() {
        //do nothing
    }
    @NotNull
    @Override
    public NamespacedKey getKey() {
        return new NamespacedKey(CommediaDell_arte.getPlugin(), "admin_axe");
    }
}
