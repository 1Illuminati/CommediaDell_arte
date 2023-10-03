package org.red.item.shop;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.red.CommediaDell_arte;
import org.red.library.A_;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.inventory.CustomGui;
import org.red.library.item.ItemBuilder;
import org.red.library.item.shop.Shop;
import org.red.library.item.shop.ShopItem;

public class ShopItemGui extends CustomGui {
    private final ShopItem shopItem;
    private final Shop shop;
    private final A_Player player;
    public ShopItemGui(A_Player player, ShopItem shopItem, Shop shop) {
        super("", 9);
        this.shopItem = shopItem;
        this.shop = shop;
        this.player = player;

        /*ItemStack sell_icon = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemStack buy_icon = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
        for (int i = 0; i < 4; i++) {
            int amount = (int) (64 / Math.pow(4, i));
            this.setItem(i, new ItemBuilder(sell_icon).setLore(shopItem.canSell(player) ? "§a판매 가격: §f" + shopItem.getSellPrice().getSellDescription(amount) :
                    "§c판매가 불가능한 품목").setAmount(amount).build());
            this.setItem(8 - i, new ItemBuilder(buy_icon).setLore(shopItem.canBuy(player) ? "§c구매 가격: §f" + shopItem.getBuyPrice().getBuyDescription(amount) :
                    "§c구매가 불가능한 품목").setAmount(amount).build());

            this.setButton(i, event -> {
                event.setCancelled(true);
                A_Player player = A_.getAPlayer((Player) event.getWhoClicked());

                if (!shopItem.isCanSell()) return;
                if (!player.getInventory().containsAtLeast(shopItem.getOriginItem(), amount))  {
                    player.sendMessage("§c판매하려는 아이템이 존재하지 않습니다!");
                    player.closeInventoryIgnoreEvent();
                } else {
                    shopItem.getSellPrice().sellPricePut(player, amount);
                    player.getInventory().removeItem(new ItemBuilder(shopItem.getOriginItem().clone()).setAmount(amount).build());
                    CommediaDell_arte.sendLog(player.getName() + "님이 " + shopItem.getOriginItem().getType() + "(" + Util.getNameItemStack(shopItem.getOriginItem()) + ") " + amount + "개를 판매하셨습니다.");
                }
            });
            this.setButton(8 - i, event -> {
                event.setCancelled(true);
                A_Player player = A_.getAPlayer((Player) event.getWhoClicked());

                if (!shopItem.isCanBuy()) return;
                if (!shopItem.getBuyPrice().isCanBuy(player, amount)) {
                    player.sendMessage("§c구매하기 위한 재화가 부족합니다!");
                    player.closeInventoryIgnoreEvent();
                } else {
                    shopItem.getBuyPrice().buyPriceRemove(player, amount);
                    Util.giveItemNature(player, shopItem.getOriginItem(), amount);
                    CommediaDell_arte.sendLog(player.getName() + "님이 " + shopItem.getOriginItem().getType() + "(" + Util.getNameItemStack(shopItem.getOriginItem()) + ") " + amount + "개를 구매하셨습니다.");
                }
            });
        }
        this.setItem(4, shopItem.getGuiItem(), inventoryClickEvent -> inventoryClickEvent.setCancelled(true));*/
    }
}
