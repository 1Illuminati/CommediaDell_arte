package org.red.library.event;

import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;
import org.red.library.a_.entity.player.A_Player;

public abstract class A_PlayerEvent extends PlayerEvent {
    private final A_Player who;
    public A_PlayerEvent(@NotNull A_Player who) {
        super(who.getPlayer());
        this.who = who;
    }

    public A_Player getAPlayer() {
        return this.who;
    }
}
