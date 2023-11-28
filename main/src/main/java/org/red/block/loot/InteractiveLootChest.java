package org.red.block.loot;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;
import org.red.Setting;
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
        Material material = player.getInventory().getItemInMainHand().getType();
        if (!player.isOp() || (material != Material.COMMAND_BLOCK && material != Material.DEBUG_STICK)) {
            event.setCancelled(true);

            if (lootChest.canOpen(player)) {
                this.lootChest.setCoolTime(player, this.lootChest.getCoolTime());
                player.openInventory(new LootChestGUI(lootChest));
            } else {
                double coolTime = this.getLootChest().getLessCoolTime(player);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', Setting.CANT_OPEN_LOOT_CHEST_MESSAGE.asStringValue().replaceAll("%time%", "" + coolTime)));
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
