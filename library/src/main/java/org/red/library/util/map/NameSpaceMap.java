package org.red.library.util.map;

import org.bukkit.NamespacedKey;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class NameSpaceMap<T> implements ConfigurationSerializable {
    private final Map<NamespacedKey, T> map = new HashMap<>();

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public String toString()   {
        return map.toString();
    }

    public T get(Object key) {
        return map.get(key);
    }

    public T put(NamespacedKey key, T value) {
        return map.put(key, value);
    }

    public T remove(Object key) {
        return map.remove(key);
    }

    public void putAll(@NotNull Map<NamespacedKey, ? extends T> m) {
        map.putAll(m);
    }

    public void putAll(@NotNull NameSpaceMap<T> m) {
        map.putAll(m.map);
    }

    public void clear() {
        map.clear();
    }

    public Set<NamespacedKey> keySet() {
        return map.keySet();
    }

    public Collection<T> values() {
        return map.values();
    }

    public Set<Map.Entry<NamespacedKey, T>> entrySet() {
        return map.entrySet();
    }

    @Override
    public int hashCode() {
        return map.hashCode();
    }

    public T getOrDefault(Object key, T defaultValue) {
        return map.getOrDefault(key, defaultValue);
    }

    public void forEach(BiConsumer<? super NamespacedKey, ? super T> action) {
        map.forEach(action);
    }

    public void replaceAll(BiFunction<? super NamespacedKey, ? super T, ? extends T> function) {
        map.replaceAll(function);
    }

    public T putIfAbsent(NamespacedKey key, T value) {
        return map.putIfAbsent(key, value);
    }

    public boolean remove(Object key, Object value) {
        return map.remove(key, value);
    }

    public boolean replace(NamespacedKey key, T oldValue, T newValue) {
        return map.replace(key, oldValue, newValue);
    }

    public T replace(NamespacedKey key, T value) {
        return map.replace(key, value);
    }

    public T computeIfAbsent(NamespacedKey key, @NotNull Function<? super NamespacedKey, ? extends T> mappingFunction) {
        return map.computeIfAbsent(key, mappingFunction);
    }

    public T computeIfPresent(NamespacedKey key, @NotNull BiFunction<? super NamespacedKey, ? super T, ? extends T> remappingFunction) {
        return map.computeIfPresent(key, remappingFunction);
    }

    public T compute(NamespacedKey key, @NotNull BiFunction<? super NamespacedKey, ? super T, ? extends T> remappingFunction) {
        return map.compute(key, remappingFunction);
    }

    public T merge(NamespacedKey key, @NotNull T value, @NotNull BiFunction<? super T, ? super T, ? extends T> remappingFunction) {
        return map.merge(key, value, remappingFunction);
    }

    @Override
    public @NotNull Map<String, Object> serialize() {
        Map<String, Object> result = new HashMap<>();
        for (Map.Entry<NamespacedKey, T> entry : this.entrySet()) {
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
        map.remove("==");
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String namespace = entry.getKey();
            Map<String, Object> value = (Map<String, Object>) entry.getValue();
            for (Map.Entry<String, Object> entry1 : value.entrySet()) {
                String key = entry1.getKey();
                T value1 = (T) entry1.getValue();

                result.put(new NamespacedKey(namespace, key), value1);
            }
        }

        return result;
    }
}
