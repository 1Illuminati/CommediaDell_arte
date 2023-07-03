package org.red.library.util;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.red.library.util.map.CoolTime;
import org.red.library.util.map.DataMap;

import java.lang.reflect.Field;

public abstract class ConfigData {
    private final DataMap dataMap;
    private final CoolTime coolTime;

    protected ConfigData(DataMap dataMap, CoolTime coolTime) {
        this.dataMap = dataMap;
        this.coolTime = coolTime;
    }

    protected boolean ignoreCantSave() {
        return false;
    }

    private boolean isCanSave(Object object) {
        if (object instanceof Integer || object instanceof String || object instanceof) {


        }
    }

    public void copy(ConfigData configData) {
        this.dataMap.copy(configData.dataMap);
        this.coolTime.copy(configData.coolTime);
    }

    public void save() {
        Class<? extends ConfigData> clazz = this.getClass();
        FileConfiguration fileConfiguration = new YamlConfiguration();

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);

            try {
                Object object = field.get(this);
                fileConfiguration.set(field.getName(), object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public void load() {

    }
}
