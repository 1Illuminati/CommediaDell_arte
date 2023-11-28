package org.red.test;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.red.CommediaDell_arte;

public class TestEvent implements Listener {
    @EventHandler
    public void onTest(PlayerMoveEvent event) {
        Location to = event.getTo();
        CommediaDell_arte.sendLog(to != null ? "Pitch: " + to.getPitch() + ", Yaw: " + to.getYaw() : "loc is null");

        if (event.getPlayer().getGameMode() == GameMode.SPECTATOR) {
            event.setCancelled(true);
        }
    }
}
