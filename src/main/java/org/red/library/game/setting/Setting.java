package org.red.library.game.setting;

import org.bukkit.NamespacedKey;
import org.bukkit.event.inventory.ClickType;

public abstract class Setting {
    private final Properties[] properties;
    private final Object[] values;

    public Setting() {
        this.properties = getProperties();
        this.values = new Object[properties.length];

        for (int i = 0; i < properties.length; i++) {
            values[i] = properties[i].getDefaultValue();
        }
    }

    public abstract Properties[] getProperties();

    public abstract SettingInventory getInventory();

    public <T> T getValue(Properties<T> properties) {
        for (int i = 0; i < this.properties.length; i++) {
            if (this.properties[i].getKey().equals(properties.getKey())) {
                return (T) values[i];
            }
        }

        throw new IllegalArgumentException("No such properties");
    }

    public <T> void setValue(Properties<T> properties, T value) {
        for (int i = 0; i < this.properties.length; i++) {
            if (this.properties[i].getKey().equals(properties.getKey())) {
                values[i] = value;
                return;
            }
        }

        throw new IllegalArgumentException("No such properties");
    }

    public void resetSetting() {
        for (int i = 0; i < properties.length; i++) {
            values[i] = properties[i].getDefaultValue();
        }
    }

    public static class Properties<T> {
        private final NamespacedKey key;
        private final T defaultValue;
        protected Properties(NamespacedKey key, T defaultValue) {
            this.key = key;
            this.defaultValue = defaultValue;
        }

        public NamespacedKey getKey() {
            return key;
        }

        public T getDefaultValue() {
            return defaultValue;
        }

        public Class<T> getClassType() {
            return (Class<T>) defaultValue.getClass();
        }
    }
}
