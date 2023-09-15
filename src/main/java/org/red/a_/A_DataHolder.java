package org.red.a_;

import org.red.library.util.map.CoolTime;
import org.red.library.util.map.DataMap;

public interface A_DataHolder {
    default DataMap getDataMap() {
        return getAData().getDataMap();
    }

    default CoolTime getCoolTime() {
        return getAData().getCoolTime();
    }

    A_Data getAData();
}
