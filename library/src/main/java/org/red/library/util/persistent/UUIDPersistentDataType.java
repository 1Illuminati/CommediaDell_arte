package org.red.library.util.persistent;

import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class UUIDPersistentDataType implements PersistentDataType<String, UUID> {
    public static final UUIDPersistentDataType INSTANCE = new UUIDPersistentDataType();
    @NotNull
    @Override
    public Class<String> getPrimitiveType() {
        return String.class;
    }

    @NotNull
    @Override
    public Class<UUID> getComplexType() {
        return UUID.class;
    }

    @NotNull
    @Override
    public String toPrimitive(@NotNull UUID uuid, @NotNull PersistentDataAdapterContext persistentDataAdapterContext) {
        return uuid.toString();
    }

    @NotNull
    @Override
    public UUID fromPrimitive(@NotNull String str, @NotNull PersistentDataAdapterContext persistentDataAdapterContext) {
        return UUID.fromString(str);
    }
}
