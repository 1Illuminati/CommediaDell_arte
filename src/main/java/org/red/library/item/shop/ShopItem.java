package org.red.library.item.shop;

import org.bukkit.inventory.ItemStack;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.item.shop.price.Price;

public interface ShopItem extends GuiItem {
    ItemStack originItem();

    boolean isBuyShopItem();

    boolean canBuy(A_Player player);

    Price buyPrice(A_Player player);

    boolean isSellShopItem();

    boolean canSell(A_Player player);

    Price sellPrice(A_Player player);
}
