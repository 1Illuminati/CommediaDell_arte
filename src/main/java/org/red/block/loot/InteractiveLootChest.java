package org.red.block.loot;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;
import org.red.CommediaDell_arte;
import org.red.library.A_;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.block.loot.LootChest;
import org.red.library.interactive.InteractiveAnnotation;
import org.red.library.interactive.block.InteractiveTile;
import org.red.library.interactive.block.InteractiveTileAct;

public final class InteractiveLootChest implements InteractiveTile {
    private final LootChest lootChest;
    public InteractiveLootChest(LootChest lootChest) {
        this.lootChest = lootChest;
    }
    @NotNull
    @Override
    public NamespacedKey getKey() {
        return lootChest.getKey();
    }

    public LootChest getLootChest() {
        return lootChest;
    }

    @InteractiveAnnotation(act = InteractiveTileAct.RIGHT_CLICK_BLOCK.class)
    public void rightClickBlock(PlayerInteractEvent event) {
        A_Player player = A_.getAPlayer(event.getPlayer());
        if (!player.isOp() || player.getInventory().getItemInMainHand().getType() != Material.COMMAND_BLOCK) {
            event.setCancelled(true);

            if (lootChest.canOpen(player)) {
                this.lootChest.setCoolTime(player, this.lootChest.getCoolTime());
                player.openInventory(new LootChestGUI(lootChest));
            } else {
                player.sendMessage("열지 못합니다!");
            }
        }
    }

    @InteractiveAnnotation(act = InteractiveTileAct.RIGHT_CLICK_BLOCK.class, shift = true)
    public void shiftRightClickBlock(PlayerInteractEvent event) {
        this.rightClickBlock(event);
    }


    @InteractiveAnnotation(act = InteractiveTileAct.BREAK.class)
    public void breakBlock(BlockBreakEvent event) {
        A_Player player = A_.getAPlayer(event.getPlayer());
        if (!player.isOp() || player.getInventory().getItemInMainHand().getType() != Material.COMMAND_BLOCK) {
            event.setCancelled(true);
        }
    }

    @InteractiveAnnotation(act = InteractiveTileAct.BREAK.class, shift = true)
    public void shiftBreakBlock(BlockBreakEvent event) {
        this.breakBlock(event);
    }
}
