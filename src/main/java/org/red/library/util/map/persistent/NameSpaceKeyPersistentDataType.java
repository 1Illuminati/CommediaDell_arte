package org.red.library.util.map.persistent;

import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class NameSpaceKeyPersistentDataType implements PersistentDataType<String, NamespacedKey> {
    public static final NameSpaceKeyPersistentDataType INSTANCE = new NameSpaceKeyPersistentDataType();
    @NotNull
    @Override
    public Class<String> getPrimitiveType() {
        return String.class;
    }

    @NotNull
    @Override
    public Class<NamespacedKey> getComplexType() {
        return NamespacedKey.class;
    }

    @NotNull
    @Override
    public String toPrimitive(@NotNull NamespacedKey namespacedKey, @NotNull PersistentDataAdapterContext persistentDataAdapterContext) {
        return namespacedKey.toString();
    }

    @NotNull
    @Override
    public NamespacedKey fromPrimitive(@NotNull String s, @NotNull PersistentDataAdapterContext persistentDataAdapterContext) {
        return NamespacedKey.fromString(s);
    }
}
