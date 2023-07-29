package org.red.library.game.setting;

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

    public abstract SettingGui getInventory();

    public <T> T getValue(Properties<T> properties) {
        for (int i = 0; i < this.properties.length; i++) {
            if (this.properties[i].getName().equals(properties.getName())) {
                return (T) values[i];
            }
        }

        throw new IllegalArgumentException("No such properties");
    }

    public <T> void setValue(Properties<T> properties, T value) {
        for (int i = 0; i < this.properties.length; i++) {
            if (this.properties[i].getName().equals(properties.getName())) {
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
        private final String name;
        private final T defaultValue;
        public Properties(String name, T defaultValue) {
            this.name = name;
            this.defaultValue = defaultValue;
        }

        public String getName() {
            return name;
        }

        public T getDefaultValue() {
            return defaultValue;
        }

        public Class<T> getClassType() {
            return (Class<T>) defaultValue.getClass();
        }
    }
}
