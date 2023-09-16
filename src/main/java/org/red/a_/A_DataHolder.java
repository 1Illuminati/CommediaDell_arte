package org.red.a_;

import org.red.library.util.map.CoolTime;
import org.red.library.util.map.DataMap;
import org.red.library.vault.EconomyAccount;

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
