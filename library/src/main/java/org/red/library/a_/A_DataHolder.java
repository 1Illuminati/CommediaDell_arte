package org.red.library.a_;

import org.bukkit.plugin.Plugin;
import org.red.library.vault.EconomyAccount;
import org.red.library.util.map.CoolTime;
import org.red.library.util.map.DataMap;

public interface A_DataHolder {
    default DataMap getDataMap(Plugin plugin) {
        return getAData().getDataMap(plugin);
    }

    default CoolTime getCoolTime(Plugin plugin) {
        return getAData().getCoolTime(plugin);
    }

    default EconomyAccount getEconomyAccount() {
        return getAData().getEconomyAccount();
    }

    DataMap getDataMap();

    CoolTime getCoolTime();

    A_Data getAData();
}
