package org.red.library.item.material;

import org.bukkit.Material;
public interface HasMaterialBan {
    boolean isActAllowed(Material material, MaterialAct act);

    void setActAllowed(Material material, MaterialAct act, boolean allowed);
}
