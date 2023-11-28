package org.red.item.shop;

import org.jetbrains.annotations.Nullable;
import org.red.library.inventory.CustomGui;
import org.red.library.item.shop.GuiItem;
import org.red.library.item.shop.Shop;
import org.red.library.item.shop.ShopItem;

import java.util.HashMap;

public class ShopImpl implements Shop {
    private final HashMap<Integer, GuiItem> guis = new HashMap<>();
    private String shopName;
    private int size;
    @Override
    public void getShopName(String shopName) {
        this.shopName = shopName;
    }

    @Override
    public @Nullable String shopName() {
        return shopName;
    }

    @Override
    public HashMap<Integer, GuiItem> getGuis() {
        return guis;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setSize(int size) {
        if (size % 9 != 0) throw new IllegalArgumentException("size must be a multiple of 9");
        this.size = size;
    }

    @Override
    public void addGui(GuiItem item) {
        for (int i = 0; i < size; i++) {
            if (!guis.containsKey(i)) {
                guis.put(i, item);
                return;
            }
        }
    }

    @Override
    public void setGui(int slot, GuiItem item) {
        if (slot >= size) throw new IllegalArgumentException("slot num must be less than size");

        this.guis.put(slot, item);
    }

    @Override
    @Nullable
    public GuiItem getGuiItem(int slot) {
        return this.guis.getOrDefault(slot, null);
    }

    @Override
    public CustomGui getShopGui(int page) {
        CustomGui customGui = new CustomGui(this.shopName, this.size);
        for (int i = 0; i < 45; i++) {
            int num = i + (page * 45);

            if (num >= this.size) break;
            GuiItem guiItem = this.guis.getOrDefault(num, null);
            if (guiItem != null) {
                customGui.setItem(i, guiItem.guiItem(), guiItem instanceof ShopItem ? event -> {
                    event.setCancelled(true);
                    ShopItem shopItem = (ShopItem) guiItem;
                    switch (event.getClick()) {
                        case LEFT:
                        case SHIFT_LEFT:
                        break;
                        case SHIFT_RIGHT:
                        break;
                    }
                } : event -> event.setCancelled(true));
            }
        }
        return customGui;
    }
}
