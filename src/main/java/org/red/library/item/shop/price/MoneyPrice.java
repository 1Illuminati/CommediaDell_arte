package org.red.library.item.shop.price;

import org.red.library.a_.entity.player.A_Player;

public class MoneyPrice implements Price {
    private final double price;

    public MoneyPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean isCanBuy(A_Player player, int amount) {
        return player.getEconomyAccount().hasBalance(this.price * amount);
    }

    @Override
    public boolean isCanSell(A_Player player, int amount) {
        return true;
    }

    @Override
    public void buyPriceRemove(A_Player player, int amount) {
        if (!this.isCanBuy(player, amount)) throw new IllegalArgumentException("Player can't buy this item");
        player.getEconomyAccount().addBalance(-this.price * amount);
    }

    @Override
    public void sellPricePut(A_Player player, int amount) {
        player.getEconomyAccount().addBalance(this.price * amount);
    }

    @Override
    public String getBuyDescription(int amount) {
        return String.format("%d", (int) price * amount);
    }

    @Override
    public String getSellDescription(int amount) {
        return this.getBuyDescription(amount);
    }
}
