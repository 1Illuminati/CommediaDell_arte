package org.red.library.entity.player;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.red.library.CommediaDell_arte;
import org.red.library.util.map.CoolTime;
import org.red.library.util.map.DataMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PlayerData {
    private final OfflinePlayer player;
    private final DataMap dataMap;
    private final CoolTime coolTime;
    private final String path;
    public PlayerData(OfflinePlayer player, DataMap dataMap, CoolTime coolTime) {
        this.player = player;
        this.dataMap = dataMap;
        this.coolTime = coolTime;
        this.path = "plugins/Dell_arte/playerData/" + player.getUniqueId() + ".yml";
    }

    public OfflinePlayer getPlayer() {
        return player;
    }

    public DataMap getDataMap() {
        return dataMap;
    }

    public CoolTime getCoolTime() {
        return coolTime;
    }

    public void save() {
        FileConfiguration fileConfiguration = new YamlConfiguration();
        fileConfiguration.set("dataMap", this.dataMap);
        fileConfiguration.set("coolTime", this.coolTime);


        File file = new File(this.path);

        try {
            fileConfiguration.save(file);
            CommediaDell_arte.sendLog("§aSave PlayerData: " + this.player.getUniqueId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        FileConfiguration fileConfiguration = new YamlConfiguration();
        File file = new File(this.path);

        try {
            fileConfiguration.load(file);
            CommediaDell_arte.sendLog("§aLoad PlayerData: " + this.player.getUniqueId());
        }  catch (IOException | InvalidConfigurationException e) {
            if (e instanceof FileNotFoundException) CommediaDell_arte.sendLog("§cNot Found PlayerData: " + this.player.getUniqueId());
            else e.printStackTrace();

            return;
        }

        DataMap dataMap = (DataMap) fileConfiguration.get("dataMap");
        if (dataMap != null) this.dataMap.copy(dataMap);

        CoolTime coolTime = (CoolTime) fileConfiguration.get("coolTime");
        if (coolTime != null) this.coolTime.copy(coolTime);
    }
}
