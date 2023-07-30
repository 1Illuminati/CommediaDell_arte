package org.red.library.util.map;

import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.Map;

public class PersistentDataMap {
    private PersistentDataContainer dataContainer;
    private final Plugin plugin;
    public PersistentDataMap(PersistentDataContainer dataContainer, Plugin plugin) {
        this.dataContainer = dataContainer;
        this.plugin = plugin;
        PersistentDataType
    }

    public void copy(PersistentDataMap dataMap) {
        dataContainer = dataMap.dataContainer;
    }

    public void copy(PersistentDataContainer dataContainer) {
        this.dataContainer = dataContainer;
    }
}
