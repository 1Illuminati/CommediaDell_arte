package org.red.library.a_;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.red.library.A_;
import org.red.library.util.map.CoolTime;
import org.red.library.util.map.DataMap;
import org.red.library.vault.EconomyAccount;

import java.util.HashMap;
import java.util.Map;

public final class A_Data implements ConfigurationSerializable {
    private final Map<String, DataMap> dataMaps;
    private final Map<String, CoolTime> coolTimes;
    private final EconomyAccount economyAccount;
    public A_Data(Map<String, DataMap> dataMaps, Map<String, CoolTime> coolTimes, EconomyAccount economyAccount) {
        this.dataMaps = dataMaps;
        this.coolTimes = coolTimes;
        this.economyAccount = economyAccount;
    }

    public DataMap getDataMap(Plugin plugin) {
        return dataMaps.computeIfAbsent(plugin.getName(), k -> new DataMap());
    }

    public Map<String, DataMap> getDataMaps() {
        return dataMaps;
    }

    public CoolTime getCoolTime(Plugin plugin) {
        return coolTimes.computeIfAbsent(plugin.getName(), k -> new CoolTime());
    }

    public Map<String, CoolTime> getCoolTimes() {
        return coolTimes;
    }

    public EconomyAccount getEconomyAccount() {
        return economyAccount;
    }

    public void copy(A_Data aData) {
        this.dataMaps.clear();
        this.dataMaps.putAll(aData.getDataMaps());
        this.coolTimes.clear();
        this.coolTimes.putAll(aData.getCoolTimes());
        this.economyAccount.copy(aData.getEconomyAccount());
    }

    @NotNull
    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("dataMaps", dataMaps);
        map.put("coolTimes", coolTimes);
        map.put("economyAccount", economyAccount);
        return map;
    }

    @NotNull
    public static A_Data deserialize(Map<String, Object> map) {
        return new A_Data((HashMap<String, DataMap>) map.get("dataMap"), (HashMap<String, CoolTime>) map.get("coolTime"), (EconomyAccount) map.get("economyAccount"));
    }

    public static A_Data newAData() {
        return new A_Data(new HashMap<>(), new HashMap<>(), A_.createEmptyEconomyAccount());
    }
}
