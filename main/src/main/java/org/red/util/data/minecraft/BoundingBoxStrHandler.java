package org.red.util.data.minecraft;

import org.bukkit.util.BoundingBox;
import org.red.util.data.DataStrHandler;

public class BoundingBoxStrHandler implements DataStrHandler<BoundingBox> {
    private final BoundingBox boundingBox;
    public BoundingBoxStrHandler(BoundingBox boundingBox) {
        this.boundingBox = boundingBox;
    }

    @Override
    public Object strToNextObject(String key) {
        switch (key) {
            case "MinX":
            return boundingBox.getMinX();
            case "MinY":
            return boundingBox.getMinY();
            case "MinZ":
            return boundingBox.getMinZ();
            case "MaxX":
            return boundingBox.getMaxX();
            case "MaxY":
            return boundingBox.getMaxY();
            case "MaxZ":
            return boundingBox.getMaxZ();
        }
        return null;
    }

    @Override
    public String dataToStr() {
        return boundingBox.toString();
    }

    @Override
    public BoundingBox originData() {
        return boundingBox;
    }
}
