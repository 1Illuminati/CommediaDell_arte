package org.red.library.a_;

import org.bukkit.plugin.Plugin;
import org.red.library.vault.EconomyAccount;
import org.red.library.util.map.CoolTime;
import org.red.library.util.map.DataMap;

public interface A_DataHolder {

    default EconomyAccount getEconomyAccount() {
        return getAData().getEconomyAccount();
    }

    DataMap getDataMap();

    DataMap getDataMap(Plugin plugin);

    CoolTime getCoolTime();

    CoolTime getCoolTime(Plugin plugin);

    A_Data getAData();
}
