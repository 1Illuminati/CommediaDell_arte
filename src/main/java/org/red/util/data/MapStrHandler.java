package org.red.util.data;

import java.util.Map;

public class MapStrHandler implements DataStrHandler<Map<String, ?>> {
    private final Map<String, ?> map;
    public MapStrHandler(Map<String, ?> map) {
        this.map = map;
    }

    @Override
    public Object strToNextObject(String key) {
        return map.getOrDefault(key, null);
    }

    @Override
    public String dataToStr() {
        return map.toString();
    }

    @Override
    public Map<String, ?> originData() {
        return map;
    }
}
