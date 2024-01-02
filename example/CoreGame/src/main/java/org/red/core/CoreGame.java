package org.red.core;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.red.core.player.GamePlayer;
import org.red.library.A_;
import org.red.library.a_.entity.player.A_Player;

public final class CoreGame extends JavaPlugin implements Listener {
    public static CoreGame plugin;

    @Override
    public void onEnable() {
        plugin = this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void deathEvent(PlayerDeathEvent event) {
        A_Player player = A_.getAPlayer(event.getEntity());

    }

    @EventHandler
    public void damageEvent(EntityDamageByEntityEvent event) {
        Entity entity = event.getEntity();
        Entity damager = event.getDamager();
        double damage = event.getDamage();

        if (damager instanceof Player) {
            A_Player damagerPlayer = A_.getAPlayer((Player) damager);
            GamePlayer gamePlayer = new GamePlayer(damagerPlayer);

            int str = gamePlayer.getStr();

            damage *= (1 + str * 0.01) + Math.round(str * 0.1);
        }

        if (entity instanceof Player) {
            A_Player entityPlayer = A_.getAPlayer((Player) entity);
            GamePlayer gamePlayer = new GamePlayer(entityPlayer);

            int agi = gamePlayer.getAgi();
            int hel = gamePlayer.getHel();

            if (Math.random() * 100 < agi * 4) {
                event.setCancelled(true);
                entityPlayer.sendMessage("§9공격을 회피하였습니다.");
                return;
            }

            damage *= (1 - hel * 0.03);
        }

        event.setDamage(damage);
    }
}
