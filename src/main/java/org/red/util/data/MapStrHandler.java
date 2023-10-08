package org.red.util.data;

import java.util.Map;

public class MapStrHandler implements DataStrHandler {
    private final Map<String, ?> map;
    public MapStrHandler(Map<String, ?> map) {
        this.map = map;
    }

    @Override
    public Object strToNextData(String key) {
        return map.getOrDefault(key, null);
    }

    @Override
    public String dataToStr() {
        return map.toString();
    }
}
