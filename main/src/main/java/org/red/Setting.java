package org.red;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.Arrays;

public enum Setting {
    CANT_OPEN_LOOT_CHEST_MESSAGE(),
    DEBUG();

    private Object value;

    public String asStringValue() {
        return (String) value;
    }

    public int asIntValue() {
        return (int) value;
    }

    public double asDoubleValue() {
        return (double) value;
    }

    public boolean asBooleanValue() {
        return (boolean) value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public static void loadNewConfig(FileConfiguration config) {
        Arrays.stream(Setting.values()).forEach(setting -> setting.setValue(config.get(setting.name().toLowerCase())));
    }
}
