package org.red.item.shop;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.item.ItemBuilder;
import org.red.library.item.shop.ShopItem;
import org.red.library.item.shop.price.Price;

import java.util.ArrayList;
import java.util.List;

public class ShopItemImpl implements ShopItem {
    private final ItemStack originItem;
    private final ItemStack guiItem;
    private final Price buyPrice;
    private final boolean canBuy;
    private final Price sellPrice;
    private final boolean canSell;
    public ShopItemImpl(ItemStack itemStack, boolean canBuy, Price buyPrice, boolean canSell, Price sellPrice) {
        this.originItem = itemStack;
        this.guiItem = this.createGuiItem();
        this.canBuy = canBuy;
        this.buyPrice = buyPrice;
        this.canSell = canSell;
        this.sellPrice = sellPrice;
    }
    @Override
    public ItemStack originItem() {
        return this.originItem;
    }

    @Override
    public boolean isBuyShopItem() {
        return canBuy;
    }

    @Override
    public ItemStack guiItem() {
        return this.guiItem;
    }

    @Override
    public boolean canBuy(A_Player player) {
        return this.canBuy;
    }

    @Override
    public Price buyPrice(A_Player player) {
        return this.buyPrice;
    }

    @Override
    public boolean isSellShopItem() {
        return canSell;
    }

    @Override
    public boolean canSell(A_Player player) {
        return this.canSell;
    }

    @Override
    public Price sellPrice(A_Player player) {
        return this.sellPrice;
    }

    private ItemStack createGuiItem() {
        ItemMeta meta = originItem.getItemMeta();
        List<String> lore = meta.hasLore() ? meta.getLore() : new ArrayList<>();

        if (meta.hasLore()) lore.add("");

        lore.add(canBuy ? "§a구매 가격: §f" + buyPrice.getBuyDescription(1) : "§c구매가 불가능한 품목");
        lore.add(canSell ? "§c판매 가격: §f" + sellPrice.getSellDescription(1) : "§c판매가 불가능한 품목");
        return new ItemBuilder(originItem.clone()).setLore(lore).build();
    }
}
