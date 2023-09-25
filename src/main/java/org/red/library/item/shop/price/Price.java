package org.red.library.item.shop.price;

import org.red.library.a_.entity.player.A_Player;

public interface Price {
    boolean isCanBuy(A_Player player, int amount);

    boolean isCanSell(A_Player player, int amount);

    void buyPriceRemove(A_Player player, int amount);
    void sellPricePut(A_Player player, int amount);

    String getBuyDescription(int amount);
    String getSellDescription(int amount);
}
