package org.red.util.data;

import java.util.List;

public class ListStrHandler implements DataStrHandler<List<?>> {
    private final List<?> list;
    public ListStrHandler(List<?> list) {
        this.list = list;
    }

    @Override
    public Object strToNextObject(String key) {
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

    @Override
    public List<?> originData() {
        return list;
    }
}
