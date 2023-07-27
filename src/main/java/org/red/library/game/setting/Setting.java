package org.red.library.game.setting;

import org.bukkit.NamespacedKey;

public class Setting<T> {
    private final NamespacedKey key;
    private final T defaultValue;
    public Setting(NamespacedKey key, T defaultValue) {
        this.key = key;
        this.defaultValue = defaultValue;
    }

    public NamespacedKey getKey() {
        return key;
    }

    public T getDefaultValue() {
        return defaultValue;
    }
}
