package org.red.util.data.minecraft;

import org.bukkit.NamespacedKey;
import org.red.util.data.DataStrHandler;

public class NamespaceKeyStrHandler implements DataStrHandler<NamespacedKey> {
    private final NamespacedKey namespacedKey;
    public NamespaceKeyStrHandler(NamespacedKey namespacedKey) {
        this.namespacedKey = namespacedKey;
    }
    @Override
    public Object strToNextObject(String key) {
        switch (key) {
            case "Namespace":
            return namespacedKey.getNamespace();
            case "Key":
            return namespacedKey.getKey();
        }
        return null;
    }

    @Override
    public String dataToStr() {
        return namespacedKey.toString();
    }

    @Override
    public NamespacedKey originData() {
        return namespacedKey;
    }
}
