package org.red.library.event;

import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.interactive.item.InteractiveItem;
import org.red.library.interactive.item.EventItemAnnotation;

public class RunEventItemEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList HANDLERS_LIST = new HandlerList();
    private boolean isCancelled = false;
    private final InteractiveItem interactiveItem;
    private final A_Player player;
    private final ItemStack mainHand;
    private final EventItemAnnotation.Act act;

    public RunEventItemEvent(InteractiveItem interactiveItem, A_Player player, ItemStack mainHand, EventItemAnnotation.Act act) {
        super(player.getPlayer());
        this.interactiveItem = interactiveItem;
        this.player = player;
        this.mainHand = mainHand;
        this.act = act;
    }

    public EventItemAnnotation.Act getAct() {
        return act;
    }

    public ItemStack getItemInMainHand() {
        return mainHand;
    }

    public InteractiveItem getEventItem() {
        return interactiveItem;
    }

    public A_Player getAPlayer() {
        return this.player;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.isCancelled = cancelled;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }
}
