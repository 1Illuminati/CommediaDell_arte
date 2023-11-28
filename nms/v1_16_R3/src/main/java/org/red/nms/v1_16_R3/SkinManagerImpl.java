package org.red.nms.v1_16_R3;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.entity.player.SkinManager;

public class SkinManagerImpl implements SkinManager {
    private final CraftPlayer player;
    private final GameProfile gameProfile;
    private boolean skinChanged = false;
    public SkinManagerImpl(A_Player player) {
        this.player = (CraftPlayer) player.getPlayer();
        this.gameProfile = this.player.getHandle().getProfile();
    }

    @Override
    public void setSkin(String skin, String signature) {
        skinChanged = true;
        gameProfile.getProperties().put("textures", new Property("textures", skin, signature));
    }

    @Override
    public void setSkin(OfflinePlayer player) {

    }

    @Override
    public boolean isChangedSkin() {
        return skinChanged;
    }

    @Override
    public void resetSkin() {
        skinChanged = false;
    }
}
