package org.red.a_.entity;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.red.CommediaDell_arte;
import org.red.library.a_.A_Data;
import org.red.a_.A_ManagerImpl;
import org.red.library.a_.entity.player.npc.A_NPC;
import org.red.library.a_.entity.player.offline.A_OfflinePlayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class A_NPCImpl extends A_PlayerImpl implements A_NPC {
    public A_NPCImpl(Player player, A_ManagerImpl.A_Version aVersion) {
        super(player, new A_OfflinePlayerImpl(player, aVersion), aVersion);
    }

    @Override
    public A_OfflinePlayer getAOfflinePlayer() {
        return null;
    }

    @Override
    public boolean isNPC() {
        return true;
    }

    @Override
    public void aDataSave() {
        FileConfiguration fileConfiguration = new YamlConfiguration();
        fileConfiguration.set("aData", this.getAData());


        File file = new File("plugins/Dell_arte/npcData/" + this.getUniqueId() + ".yml");

        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        CommediaDell_arte.sendDebugLog("§aSave NPCData: " + this.getUniqueId());
    }

    @Override
    public void aDataLoad() {
        FileConfiguration fileConfiguration = new YamlConfiguration();
        File file = new File("plugins/Dell_arte/npcData/" + this.getUniqueId() + ".yml");

        try {
            fileConfiguration.load(file);
        }  catch (IOException | InvalidConfigurationException e) {
            if (e instanceof FileNotFoundException) CommediaDell_arte.sendDebugLog("§cNot Found NPCData: " + this.getUniqueId());
            else e.printStackTrace();

            return;
        }

        A_Data aData = (A_Data) fileConfiguration.get("aData");
        if (aData != null) this.getAData().copy(aData);

        CommediaDell_arte.sendDebugLog("§aLoad NPCData: " + this.getUniqueId());
    }
}
