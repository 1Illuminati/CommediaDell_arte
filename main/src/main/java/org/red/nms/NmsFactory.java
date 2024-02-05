package org.red.nms;

import org.red.CommediaDell_arte;
import org.red.ServerVersionEnum;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.entity.player.EntityHideManager;
import org.red.library.entity.player.SkinManager;
import org.red.library.item.AbstractItemManager;
import org.red.nms.v1_16_R3.SkinManagerImpl;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public final class NmsFactory {
    private NmsFactory() {
        throw new UnsupportedOperationException();
    }

    private static Object getNmsClass(ServerVersionEnum version, String classPath, Class[] constructorsClasses, Object[] constructors) {
        try {
            return Class.forName("org.red.nms." + version.name() + "." + classPath).getConstructor(constructorsClasses).newInstance(constructors);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException |
                 ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static SkinManager createSkinManager(A_Player player) {
        return (SkinManager) getNmsClass(CommediaDell_arte.getServerVersion(), "SkinManagerImpl",
                new Class[]{A_Player.class}, new Object[]{player});
    }

    public static EntityHideManager createEntityHideManager(A_Player player) {
        return (EntityHideManager) getNmsClass(CommediaDell_arte.getServerVersion(), "EntityHideManagerImpl",
                new Class[]{A_Player.class}, new Object[]{player});
    }

    public static AbstractItemManager createItemManager() {
        return (AbstractItemManager) getNmsClass(CommediaDell_arte.getServerVersion(), "ItemManager", new Class[]{}, new Object[]{});
    }
}
