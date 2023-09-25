package org.red.library.item.shop;

import org.bukkit.inventory.ItemStack;
import org.red.library.inventory.CustomGui;

public abstract class ShopGui extends CustomGui {
    public ShopGui(int slot) {
        super(slot);
    }

    public ItemStack setShopItem(int slot, ShopItem shopItem) {

        return this.setItem(slot, shopItem.guiItem());
    }
}
