package org.red.util.data.minecraft;

import org.bukkit.inventory.ItemStack;
import org.red.util.data.DataStrHandler;

public class ItemStackStrHandler implements DataStrHandler<ItemStack> {
    private final ItemStack itemStack;
    public ItemStackStrHandler(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    @Override
    public Object strToNextObject(String key) {
        switch (key) {
            case "Amount":
            return itemStack.getAmount();
            case "Type":
            return itemStack.getType();
            case "Durability":
            return itemStack.getDurability();
        }
        return null;
    }

    @Override
    public String dataToStr() {
        return itemStack.toString();
    }

    @Override
    public ItemStack originData() {
        return itemStack;
    }
}
