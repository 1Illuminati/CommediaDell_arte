package org.red.library.util.map;

import org.bukkit.NamespacedKey;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.HashMap;
import java.util.Map;

public class NameSpaceMap<T> extends HashMap<NamespacedKey, T> implements ConfigurationSerializable {

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> result = new HashMap<>();
        for (Entry<NamespacedKey, T> entry : this.entrySet()) {
            String namespace = entry.getKey().getNamespace();
            String key = entry.getKey().getKey();
            T value = entry.getValue();

            Map<String, Object> map = (Map<String, Object>) result.computeIfAbsent(namespace, k -> new HashMap<>());
            map.put(key, value);
        }


        return result;
    }

    public static <T> NameSpaceMap<T> deserialize(Map<String, Object> map) {
        NameSpaceMap<T> result = new NameSpaceMap<>();
        for (Entry<String, Object> entry : map.entrySet()) {
            String namespace = entry.getKey();
            Map<String, Object> value = (Map<String, Object>) entry.getValue();

            for (Entry<String, Object> entry1 : value.entrySet()) {
                String key = entry1.getKey();
                T value1 = (T) entry1.getValue();

                result.put(new NamespacedKey(namespace, key), value1);
            }
        }

        return result;
    }
}
