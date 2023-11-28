package org.red.library.entity.player;

import org.bukkit.OfflinePlayer;

public interface SkinManager {
    void setSkin(String skin, String signature);
    void setSkin(OfflinePlayer player);
    boolean isChangedSkin();
    void resetSkin();
}
