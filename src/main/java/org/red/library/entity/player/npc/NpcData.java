package org.red.library.entity.player.npc;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.red.library.CommediaDell_arte;
import org.red.library.entity.player.PlayerData;
import org.red.library.util.map.CoolTime;
import org.red.library.util.map.DataMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class NpcData extends PlayerData {
    private final NpcPlayer player;
    private final String path;
    public NpcData(NpcPlayer player) {
        super(null);
        this.player = player;
        this.path = "plugins/Dell_arte/npcData/" + player.getUniqueId() + ".yml";
    }

    public NpcPlayer getNpcPlayer() {
        return this.player;
    }

    @Override
    public void save() {
        FileConfiguration fileConfiguration = new YamlConfiguration();
        fileConfiguration.set("dataMap", this.getDataMap());
        fileConfiguration.set("coolTime", this.getCoolTime());


        File file = new File(this.path);

        try {
            fileConfiguration.save(file);
            CommediaDell_arte.sendLog("§aSave NpcPlayerData: " + this.player.getUniqueId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void load() {
        FileConfiguration fileConfiguration = new YamlConfiguration();
        File file = new File(this.path);

        try {
            fileConfiguration.load(file);
            CommediaDell_arte.sendLog("§aLoad NpcPlayerData: " + this.player.getUniqueId());
        }  catch (IOException | InvalidConfigurationException e) {
            if (e instanceof FileNotFoundException) CommediaDell_arte.sendLog("§cNot Found NpcPlayerData: " + this.player.getUniqueId());
            else e.printStackTrace();

            return;
        }

        DataMap dataMap = (DataMap) fileConfiguration.get("dataMap");
        if (dataMap != null) this.getDataMap().copy(dataMap);

        CoolTime coolTime = (CoolTime) fileConfiguration.get("coolTime");
        if (coolTime != null) this.getCoolTime().copy(coolTime);
    }
}
