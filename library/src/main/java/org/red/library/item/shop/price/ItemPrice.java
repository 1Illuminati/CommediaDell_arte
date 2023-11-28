package org.red.library.item.shop.price;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.red.library.a_.entity.player.A_Player;

import java.util.Arrays;
import java.util.List;

public class ItemPrice implements Price {
    private final List<ItemStack> itemStacks;
    public ItemPrice(List<ItemStack> itemStacks) {
        this.itemStacks = itemStacks;
    }

    public ItemPrice(ItemStack... itemStacks) {
        this.itemStacks = Arrays.asList(itemStacks);
    }

    @Override
    public boolean isCanBuy(A_Player player, int amount) {
        for (ItemStack itemStack : this.itemStacks) {
            if (!player.getInventory().containsAtLeast(itemStack, itemStack.getAmount() * amount)) return false;
        }

        return true;
    }

    @Override
    public boolean isCanSell(A_Player player, int amount) {
        return true;
    }

    @Override
    public void buyPriceRemove(A_Player player, int amount) {
        if (!this.isCanBuy(player, amount)) throw new IllegalArgumentException("Player can't buy this item");
        for (int i = 0; i < amount; i++) {
            for (ItemStack itemStack : this.itemStacks) {
                player.getInventory().removeItem(itemStack);
            }
        }
    }

    @Override
    public void sellPricePut(A_Player player, int amount) {
        for (int i = 0; i < amount; i++)  {
            for (ItemStack itemStack : this.itemStacks) {
                player.addItemNature(itemStack);
            }
        }
    }

    @Override
    public String getBuyDescription(int amount) {
        StringBuilder stringBuilder = new StringBuilder();
        itemStacks.forEach(itemStack -> {
            ItemMeta itemMeta = itemStack.getItemMeta();
            stringBuilder.append(itemMeta.hasDisplayName() ? itemMeta.getDisplayName() : itemMeta.getLocalizedName())
                    .append("x").append(itemStack.getAmount() * amount).append(" ");
        });
        return stringBuilder.toString();
    }

    @Override
    public String getSellDescription(int amount) {
        return this.getBuyDescription(amount);
    }
}
