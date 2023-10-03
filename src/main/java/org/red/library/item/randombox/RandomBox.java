package org.red.library.item.randombox;

import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface RandomBox {
    List<ItemStack> getItems();

    String getName();
}
