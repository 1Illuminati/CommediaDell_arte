package org.red.core.stat;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.red.core.player.GamePlayer;
import org.red.library.A_;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.inventory.CustomGui;
import org.red.library.item.ItemBuilder;

public class StatInv extends CustomGui {
    private final A_Player player;
    public StatInv(A_Player player) {
        super("StatInv", 27);
        this.player = player;
        this.setItem();
    }

    public void setItem() {
        GamePlayer gamePlayer = new GamePlayer(player);

        int str = gamePlayer.getStr();
        int agi = gamePlayer.getAgi();
        int hel = gamePlayer.getHel();
        this.setItem(10, new ItemBuilder(Material.BONE).setDisplayName("§c§l힘").setCustomModelData(0).setLore(
                "§fFor Each Level",
                "§7 +1% 공격력",
                "",
                "§f10 Level Bonus",
                "§7 +1 공격력",
                "",
                "§fAll Level Bonus",
                "§7 +" + str + "% 공격력",
                "§7 +" + (str / 10) + " 공격력",
                "",
                "§fLevel: " + str + " / 100"
        ).build());
        this.setItem(13, new ItemBuilder(Material.BONE).setDisplayName("§a§l민첩").setCustomModelData(1).setLore(
                "§fFor Each Level",
                "§7 +0.5% 이동속도",
                "",
                "§f10 Level Bonus",
                "§7 +4% 회피 확률",
                "",
                "§fAll Level Bonus",
                "§7 +" + (Math.round(agi * 0.5 * 10) / 10) + "% 이동속도",
                "§7 +" + (agi * 4)  + "% 회피 확률",
                "",
                "§fLevel: " + agi + " / 100"
        ).build());
        this.setItem(16, new ItemBuilder(Material.BONE).setDisplayName("§9§l체력").setCustomModelData(2).setLore(
                "§fFor Each Level",
                "§7 +0.4 체력",
                "",
                "§f10 Level Bonus",
                "§7 -3% 받는 피해량",
                "",
                "§fAll Level Bonus",
                "§7 +" + (Math.round(hel * 0.4 * 10) / 10) + " 체력",
                "§7 -" + (hel * 3) + "% 받는 피해량",
                "",
                "§fLevel: " + hel + " / 100"
        ).build());
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        event.setCancelled(true);
        int clickedSlot = event.getSlot();
        String stat;

        switch (clickedSlot) {
            case 10:
                stat = "str";
                break;
            case 13:
                stat = "agi";
                break;
            case 16:
                stat = "hel";
                break;
            default:
                return;
        }

        GamePlayer gamePlayer = new GamePlayer(player);
        int statPoint = gamePlayer.getStatPoint();
        if (statPoint <= 0) {
            player.sendMessage("§c스탯 포인트가 부족합니다.");
            player.closeInventory();
            return;
        }

        if (gamePlayer.getDataMap().getInt(stat) >= 100) {
            player.sendMessage("§c스탯을 더이상 올릴 수 없습니다.");
            player.closeInventory();
            return;
        }

        gamePlayer.setStatPoint(statPoint - 1);
        gamePlayer.getDataMap().addInt(stat, 1);
        this.setItem();
        player.updateInventory();
    }

    public void onClose(InventoryCloseEvent event) {
        A_Player player = A_.getAPlayer((Player) event.getPlayer());
        GamePlayer gamePlayer = new GamePlayer(player);
        int agi = gamePlayer.getAgi();
        int hel = gamePlayer.getHel();

        player.setWalkSpeed(0.2f + 0.2f * agi * 0.5f);
        player.setMaxHealth(20 + hel * 0.4);
    }
}
