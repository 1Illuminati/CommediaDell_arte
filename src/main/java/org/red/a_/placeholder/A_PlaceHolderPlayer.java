package org.red.a_.placeholder;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.Particle;
import org.jetbrains.annotations.NotNull;
import org.red.CommediaDell_arte;
import org.red.block.loot.LootChestImpl;
import org.red.library.A_;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.a_.entity.player.offline.A_OfflinePlayer;
import org.red.library.a_.world.A_World;
import org.red.library.util.map.DataMap;
import org.red.library.world.Area;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class A_PlaceHolderPlayer extends PlaceholderExpansion {
    public static void setPlaceHolder() {
        CommediaDell_arte.sendLog("PlaceholderAPI Plugin Checked");
        new A_PlaceHolderPlayer().register();
    }
    @Override
    public @NotNull String getIdentifier() {
        return "aplayer";
    }

    @Override
    public @NotNull String getAuthor() {
        return "RedKiller";
    }

    @Override
    public @NotNull String getVersion() {
        return "2.0";
    }

    @Override
    public String onRequest(OfflinePlayer player, @NotNull String identifier) {
        A_OfflinePlayer aOfflinePlayer = A_.getAOfflinePlayer(player);
        CommediaDell_arte.sendLog(identifier);
        String[] split = identifier.replace("aplayer", "").split("\\.");
        CommediaDell_arte.sendLog(split);
        if (split.length == 0) return null;
        switch (split[0]) {
            case "cooltime": return aOfflinePlayer.getCoolTime().getLessCoolTime(identifier.replace("cooltime.", "")) + "";
            case "data": return aOfflinePlayer.getDataMap().getObjByStr(identifier.replace("data.", "")).toString();
            case "economy": return aOfflinePlayer.getEconomyAccount().getBalance() + "";
        }

        if (!aOfflinePlayer.isOnline()) return "PLAYER_IS_NOT_ONLINE";
        A_Player aPlayer = A_.getAPlayer(aOfflinePlayer.getUniqueId());

        if (split[0].startsWith("world:")) {
            if (split.length <= 2) return null;
            String[] worldSplit = split[0].split(":");
            A_World aWorld = A_.getAWorld(worldSplit[1]);
            if (aWorld == null) return "WORLD IS NULL";

            if (split[1].startsWith("area:")) {
                String[] areaSplit = split[1].split(":");

                if (areaSplit.length != 3) return null;

                NamespacedKey namespacedKey = new NamespacedKey(areaSplit[1], areaSplit[2]);
                Area area = aWorld.getArea(namespacedKey);

                if (area == null) return "AREA IS NULL";

                switch (split[2]) {
                    case "in": return area.contain(aPlayer.getLocation()) + "";
                }
            } else if (split[1].startsWith("loot:")) {

            }
        }
        return null;
    }
}
