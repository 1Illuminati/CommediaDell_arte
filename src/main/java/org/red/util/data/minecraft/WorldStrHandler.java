package org.red.util.data.minecraft;

import org.bukkit.World;
import org.red.util.data.DataStrHandler;

public class WorldStrHandler implements DataStrHandler<World> {
    private final World world;
    public WorldStrHandler(World world) {
        this.world = world;
    }


    @Override
    public Object strToNextObject(String key) {
        return null;
    }

    @Override
    public String dataToStr() {
        return world.getName();
    }

    @Override
    public World originData() {
        return this.world;
    }
}
