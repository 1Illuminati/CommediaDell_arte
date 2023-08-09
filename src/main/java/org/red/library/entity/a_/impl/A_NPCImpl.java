package org.red.library.entity.a_.impl;

import org.bukkit.entity.Player;
import org.red.library.a_.A_Data;
import org.red.library.a_.A_Manager;
import org.red.library.entity.a_.player.npc.A_NPC;

public class A_NPCImpl extends A_PlayerImpl implements A_NPC {
    public A_NPCImpl(Player player, A_Data aData, A_Manager.A_Version aVersion) {
        super(player, aData, aVersion);
    }

    @Override
    public boolean isNPC() {
        return true;
    }

    @Override
    public void aDataSave() {

    }

    @Override
    public void aDataLoad() {

    }
}
