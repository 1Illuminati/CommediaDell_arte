package org.red.library.util.map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.BoundingBox;

import java.util.*;

public class DataMap implements ConfigurationSerializable {
    private Map<String, Object> map = new HashMap<>();

    public static DataMap deserialize(Map<String, Object> map) {
        DataMap dataMap = new DataMap();
        dataMap.copy(map);
        return dataMap;
    }

    public void copy(DataMap dataMap) {
        map = dataMap.getMap();
    }

    public void copy(Map<String, Object> map) {
        this.map = map;
    }

    public ItemStack getItemStack(String key) {
        return getItemStack(key, new ItemStack(Material.AIR));
    }

    public ItemStack getItemStack(String key, ItemStack nullValue) {
        return (ItemStack) this.get(key, nullValue);
    }

    public Location getLocation(String key) {
        return getLocation(key, new Location(Bukkit.getWorlds().get(0), 0, 0, 0));
    }

    public Location getLocation(String key, Location nullValue) {
        return (Location) this.get(key, nullValue);
    }

    public int getInt(String key) {
        return getInt(key, 0);
    }

    public int getInt(String key, int nullValue) {
        return (int) this.get(key, nullValue);
    }

    public void addInt(String key, int value) {
        put(key, getInt(key) + value);
    }

    public double getDouble(String key) {
        return getDouble(key, 0);
    }

    public double getDouble(String key, double nullValue) {
        return (double) this.get(key, nullValue);
    }

    public void addDouble(String key, double value) {
        put(key, getDouble(key) + value);
    }

    public String getString(String key) {
        return getString(key, "");
    }

    public String getString(String key, String nullValue) {
        return this.get(key, nullValue).toString();
    }

    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean nullValue) {
        return (boolean) this.get(key, nullValue);
    }

    public Long getLong(String key) {
        return getLong(key, 0L);
    }

    public Long getLong(String key, Long nullValue) {
        return (Long) this.get(key, nullValue);
    }

    public void addLong(String key, Long value) {
        put(key, getLong(key) + value);
    }

    public Object get(String key) {
        return this.get(key, null);
    }

    public Object get(String key, Object nullValue) {
        if (!map.containsKey(key))
            put(key, nullValue);

        return map.get(key);
    }

    public <T> List<T> getList(String key) {
        return this.getList(key, new ArrayList<>());
    }

    public <T> List<T> getList(String key, List<T> nullValue) {
        return (List<T>) this.get(key, nullValue);
    }

    public DataMap getDataMap(String key) {
        return this.getDataMap(key, new DataMap());
    }

    public DataMap getDataMap(String key, DataMap nullValue) {
        return (DataMap) this.get(key, nullValue);
    }

    public BoundingBox getBoundingBox(String key) {
        return this.getBoundingBox(key, new BoundingBox(0, 0, 0, 0, 0, 0));
    }

    public BoundingBox getBoundingBox(String key, BoundingBox nullValue) {
        return (BoundingBox) this.get(key, nullValue);
    }

    public UUID getUUID(String key) {
        return this.getUUID(key, UUID.randomUUID());
    }

    public UUID getUUID(String key, UUID nullValue) {
        return (UUID) this.get(key, nullValue);
    }

    public void put(String key, Object value) {
        map.put(key, value);
    }

    public DataMap set(String key, Object value) {
        map.put(key, value);
        return this;
    }

    public void remove(String key) {
        map.remove(key);
    }

    public boolean containsKey(String key) {
        return map.containsKey(key);
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public Set<String> keySet() {
        return map.keySet();
    }

    public Collection<Object> values() {
        return map.values();
    }

    public Set<Map.Entry<String, Object>> entrySet() {
        return map.entrySet();
    }

    public String toString() {
        return map.toString();
    }

    public void clear() {
        this.map.clear();
    }

    public void save() {
        throw new UnsupportedOperationException("DataMap No Supported this method plz extend and use");
    }

    public void load() {
        throw new UnsupportedOperationException("DataMap No Supported this method plz extend and use");
    }

    @Override
    public Map<String, Object> serialize() {
        return map;
    }
}
