package org.red.library.a_;

import org.red.library.vault.EconomyAccount;
import org.red.library.util.map.CoolTime;
import org.red.library.util.map.DataMap;

public interface A_DataHolder {
    default DataMap getDataMap() {
        return getAData().getDataMap();
    }

    default CoolTime getCoolTime() {
        return getAData().getCoolTime();
    }

    default EconomyAccount getEconomyAccount() {
        return getAData().getEconomyAccount();
    }

    A_Data getAData();
}
