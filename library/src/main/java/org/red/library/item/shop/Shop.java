package org.red.library.item.shop;

import org.jetbrains.annotations.Nullable;
import org.red.library.inventory.CustomGui;

import java.util.HashMap;

public interface Shop {
    void getShopName(String shopName);
    @Nullable
    String shopName();
    HashMap<Integer, GuiItem> getGuis();
    int getSize();
    void setSize(int size);
    void addGui(GuiItem item);
    void setGui(int slot, GuiItem item);
    @Nullable
    GuiItem getGuiItem(int slot);
    CustomGui getShopGui(int page);
}
