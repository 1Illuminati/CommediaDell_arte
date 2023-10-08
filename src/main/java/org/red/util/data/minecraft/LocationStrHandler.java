package org.red.util.data.minecraft;

import org.bukkit.Location;
import org.red.util.data.DataStrHandler;

public class LocationStrHandler implements DataStrHandler {
    private final Location location;
    public LocationStrHandler(Location location) {
        this.location = location;
    }

    @Override
    public Object strToNextData(String key) {
        switch (key) {
            case "X":
            return location.getX();
            case "Y":
            return location.getY();
            case "Z":
            return location.getZ();
            case "Yaw":
            return location.getYaw();
            case "Pitch":
            return location.getPitch();
            case "World":
            return location.getWorld();
        }

        return null;
    }

    @Override
    public String dataToStr() {
        return location.toString();
    }
}
