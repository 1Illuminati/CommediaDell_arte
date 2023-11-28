package org.red.util.data;

public class PrimitiveStrHandler<T> implements DataStrHandler<T> {
    protected final T value;
    public PrimitiveStrHandler(T value) {
        this.value = value;
    }

    @Override
    public PrimitiveStrHandler<?> strToNextObject(String key) {
        return null;
    }

    @Override
    public T originData() {
        return value;
    }

    @Override
    public String dataToStr() {
        return value.toString();
    }
}
