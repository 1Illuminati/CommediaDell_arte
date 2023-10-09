package org.red.util.data;

import org.bukkit.inventory.ItemStack;
import org.red.library.util.map.DataMap;
import org.red.util.data.minecraft.ItemStackStrHandler;

import java.util.List;
import java.util.Map;

public interface DataStrHandler<T> {
    Object strToNextObject(String key);
    String dataToStr();
    T originData();

    static DataStrHandler<?> objToDataStrHandler(Object object) {
        if (object instanceof DataStrHandler) {
            return (DataStrHandler<?>) object;
        } else if (object instanceof Map) {
            return new MapStrHandler((Map<String, ?>) object);
        } else if (object instanceof DataMap) {
            return new DataMapStrHandler((DataMap) object);
        } else if (object instanceof List) {
            return new ListStrHandler((List<?>) object);
        } else if (object instanceof ItemStack) {
            return new ItemStackStrHandler((ItemStack) object);
        }

        return null;
    }
}
