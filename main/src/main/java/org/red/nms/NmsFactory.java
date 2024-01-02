package org.red.nms;

import org.red.CommediaDell_arte;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.entity.player.EntityHideManager;
import org.red.library.entity.player.SkinManager;
import org.red.library.item.AbstractItemManager;

public final class NmsFactory {
    private NmsFactory() {
        throw new UnsupportedOperationException();
    }

    public static SkinManager createSkinManager(A_Player player) {
        switch (CommediaDell_arte.getServerVersion()) {
            case v1_16_R3:
                return new org.red.nms.v1_16_R3.SkinManagerImpl(player);
            default:
                throw new UnsupportedOperationException("Unsupported server version");
        }
    }

    public static EntityHideManager createEntityHideManager(A_Player player) {
        switch (CommediaDell_arte.getServerVersion()) {
            case v1_16_R3:
                return new org.red.nms.v1_16_R3.EntityHideManagerImpl(player);
            default:
                throw new UnsupportedOperationException("Unsupported server version");
        }
    }

    public static AbstractItemManager createItemManager() {
        switch (CommediaDell_arte.getServerVersion()) {
            case v1_16_R3:
                return new org.red.nms.v1_16_R3.ItemManager();
            case v1_17_R1:
                return new org.red.nms.v1_17_R1.ItemManager();
            case v1_18_R2:
                return new org.red.nms.v1_18_R2.ItemManager();
            case v1_19_R3:
                return new org.red.nms.v1_19_R4.ItemManager();
            case v1_20_R1:
                return new org.red.nms.v1_20_R1.ItemManager();
            default:
                throw new UnsupportedOperationException("Unsupported server version");
        }
    }
}
