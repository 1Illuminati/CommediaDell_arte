package org.red.event.listener.player;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerEvent;
import org.red.event.listener.AbstractListener;
import org.red.library.A_;
import org.red.library.a_.world.A_World;

public abstract class AbstractPlayerListener<T extends PlayerEvent> extends AbstractListener<T> {
    protected void runAreaPlayerEvent(T event) {
        Player player = event.getPlayer();
        A_World world = A_.getAWorld(player.getWorld());
        runAreaEvent(event, world.getContainAreas(player.getLocation()));
    }
}
