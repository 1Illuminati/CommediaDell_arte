package org.red.util.data;

import java.util.List;

public class ListStrHandler implements DataStrHandler {
    private final List<?> list;
    public ListStrHandler(List<?> list) {
        this.list = list;
    }

    @Override
    public DataStrHandler strToNextData(String key) {
        int a;

        try {
            a = Integer.parseInt(key);
        } catch (NumberFormatException e) {
            return null;
        }

        return a < list.size() ? list.get(a) : null;
    }

    @Override
    public String dataToStr() {
        return list.toString();
    }
}
