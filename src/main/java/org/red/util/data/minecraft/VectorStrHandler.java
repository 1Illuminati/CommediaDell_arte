package org.red.util.data.minecraft;

import org.bukkit.util.Vector;
import org.red.util.data.DataStrHandler;

public class VectorStrHandler implements DataStrHandler {
    private final Vector vector;
    public VectorStrHandler(Vector vector) {
        this.vector = vector;
    }

    @Override
    public Object strToNextData(String key) {
        switch (key) {
            case "X":
            return vector.getX();
            case "Y":
            return vector.getY();
            case "Z":
            return vector.getZ();
        }

        return null;
    }

    @Override
    public String dataToStr() {
        return vector.toString();
    }
}
