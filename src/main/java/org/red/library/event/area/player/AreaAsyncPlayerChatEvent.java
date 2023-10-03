package org.red.library.event.area.player;

import org.bukkit.event.HandlerList;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.red.library.world.Area;

public class AreaAsyncPlayerChatEvent extends AreaPlayerEvent<AsyncPlayerChatEvent> {
    public AreaAsyncPlayerChatEvent(Area area, AsyncPlayerChatEvent event) {
        super(area, event);
    }

    public static HandlerList getHandlerList() {
        return handler_map.computeIfAbsent(AreaAsyncPlayerChatEvent.class, k -> new HandlerList());
    }
}
