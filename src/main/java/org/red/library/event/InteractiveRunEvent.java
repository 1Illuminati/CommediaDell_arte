package org.red.library.event;

import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.interactive.InteractiveAct;
import org.red.library.interactive.InteractiveObj;

public class InteractiveRunEvent extends A_PlayerEvent implements Cancellable {
    private static final HandlerList HANDLERS_LIST = new HandlerList();
    private boolean isCancelled = false;
    private final InteractiveObj<?> interactiveObj;
    private final Class<? extends InteractiveAct> act;

    public <T> InteractiveRunEvent(InteractiveObj<T> interactiveObj, A_Player player, Class<? extends InteractiveAct> act) {
        super(player);
        this.interactiveObj = interactiveObj;
        this.act = act;
    }

    public Class<? extends InteractiveAct> getAct() {
        return act;
    }

    public InteractiveObj<?> getInteractiveObj() {
        return interactiveObj;
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
