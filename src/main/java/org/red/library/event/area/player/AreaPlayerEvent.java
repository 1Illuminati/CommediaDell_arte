package org.red.library.event.area.player;

import org.bukkit.event.player.PlayerEvent;
import org.red.library.A_;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.event.area.AreaEvent;
import org.red.library.world.Area;

public abstract class AreaPlayerEvent<T extends PlayerEvent> extends AreaEvent<T> {
    private final A_Player player;
    public AreaPlayerEvent(Area area, T event) {
        super(area, event);
        this.player = A_.getAPlayer(event.getPlayer());
    }

    public A_Player getPlayer() {
        return player;
    }
}
