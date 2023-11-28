package org.red.library.event;

import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.block.loot.LootChest;

public class LootChestOpenEvent extends A_PlayerEvent implements Cancellable {
    private static final HandlerList HANDLERS_LIST = new HandlerList();
    private final LootChest lootChest;
    private boolean isCancelled = false;

    public LootChestOpenEvent(LootChest lootChest, @NotNull A_Player who) {
        super(who);
        this.lootChest = lootChest;
    }

    public LootChest getLootChest() {
        return this.lootChest;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        this.isCancelled = b;
    }
}
