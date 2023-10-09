package org.red.util.data.minecraft;

import org.bukkit.Material;
import org.red.util.data.DataStrHandler;

public class MaterialStrHandler implements DataStrHandler<Material> {
    private final Material material;
    public MaterialStrHandler(Material material) {
        this.material = material;
    }

    @Override
    public Object strToNextObject(String key) {
        switch (key) {
            case "Id":
            return material.getId();
            case "MaxDurability":
            return material.getMaxDurability();
            case "MaxStackSize":
            return material.getMaxStackSize();
            case "Key":
            return material.getKey();
        }
        return null;
    }

    @Override
    public String dataToStr() {
        return material.name();
    }

    @Override
    public Material originData() {
        return material;
    }
}
