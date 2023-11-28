package org.red.library.entity.player;

import org.bukkit.entity.Entity;

public interface EntityHideManager {
    void hide(Entity entity);
    boolean isHidden(Entity entity);
    void show(Entity entity);
}
