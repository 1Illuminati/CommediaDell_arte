package org.red.library.util.map.persistent;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public abstract class ConfigSerializePersistentDataType<T extends ConfigurationSerializable> implements PersistentDataType<PersistentDataContainer, T> {
    @NotNull
    @Override
    public Class<PersistentDataContainer> getPrimitiveType() {
        return PersistentDataContainer.class;
    }

    @NotNull
    @Override
    public PersistentDataContainer toPrimitive(@NotNull T configurationSerializable, @NotNull PersistentDataAdapterContext persistentDataAdapterContext) {
        Map<String, Object> map = configurationSerializable.serialize();
        return null;
    }

    @NotNull
    @Override
    public T fromPrimitive(@NotNull PersistentDataContainer dataContainer, @NotNull PersistentDataAdapterContext persistentDataAdapterContext) {
        return null;
    }
}
