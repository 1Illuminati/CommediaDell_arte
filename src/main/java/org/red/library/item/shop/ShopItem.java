package org.red.library.item.shop;

import org.bukkit.inventory.ItemStack;
import org.red.library.item.shop.price.Price;

public interface ShopItem {
    ItemStack originItem();

    ItemStack guiItem();

    boolean canBuy();

    Price buyPrice();

    boolean canSell();

    Price sellPrice();
}
