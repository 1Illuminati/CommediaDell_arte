package org.red.library.event;

import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.item.event.EventItem;
import org.red.library.item.event.EventItemAnnotation;

public class RunEventItemEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList HANDLERS_LIST = new HandlerList();
    private boolean isCancelled = false;
    private final EventItem eventItem;
    private final A_Player player;
    private final ItemStack mainHand;
    private final EventItemAnnotation.Act act;

    public RunEventItemEvent(EventItem eventItem, A_Player player, ItemStack mainHand, EventItemAnnotation.Act act) {
        super(player.getPlayer());
        this.eventItem = eventItem;
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

    public EventItem getEventItem() {
        return eventItem;
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
