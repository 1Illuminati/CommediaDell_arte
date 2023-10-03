package org.red.library.a_;

import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.jetbrains.annotations.NotNull;
import org.red.a_.vault.A_EconomyAccount;
import org.red.library.util.map.CoolTime;
import org.red.library.util.map.DataMap;

import java.util.HashMap;
import java.util.Map;

public final class A_Data implements ConfigurationSerializable {
    private final DataMap dataMap;
    private final CoolTime coolTime;
    private final A_EconomyAccount economyAccount;
    public A_Data(DataMap dataMap, CoolTime coolTime, A_EconomyAccount economyAccount) {
        this.dataMap = dataMap;
        this.coolTime = coolTime;
        this.economyAccount = economyAccount;
    }

    public DataMap getDataMap() {
        return dataMap;
    }

    public CoolTime getCoolTime() {
        return coolTime;
    }

    public A_EconomyAccount getEconomyAccount() {
        return economyAccount;
    }

    public void copy(A_Data aData) {
        this.dataMap.copy(aData.getDataMap());
        this.coolTime.copy(aData.getCoolTime());
        this.economyAccount.copy(aData.getEconomyAccount());
    }

    @NotNull
    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("dataMap", dataMap);
        map.put("coolTime", coolTime);
        map.put("economyAccount", economyAccount);
        return map;
    }

    public Object strToData(String str) {
        if (str.startsWith("data.")) {
            String[] split = str.split("\\.");
            DataMap dataMap = this.dataMap;
            for (int i = 1; i < split.length; i++) {
                if (!dataMap.containsKey(split[i]))
                    return null;
                Object obj = dataMap.get(split[i]);
                if (obj instanceof DataMap) {
                    dataMap = (DataMap) obj;
                } else {
                    return obj;
                }
            }
            return dataMap;
        } else if (str.startsWith("cooltime.")) {
            String[] split = str.split("\\.");
            return this.coolTime.getCoolTime(split[1]);
        } else if (str.startsWith("economy.")) {
            return this.economyAccount.getBalance();
        }
        return null;
    }

    @NotNull
    public static A_Data deserialize(Map<String, Object> map) {
        return new A_Data((DataMap) map.get("dataMap"), (CoolTime) map.get("coolTime"), (A_EconomyAccount) map.get("economyAccount"));
    }

    public static A_Data newAData() {
        return new A_Data(new DataMap(), new CoolTime(), new A_EconomyAccount());
    }
}
