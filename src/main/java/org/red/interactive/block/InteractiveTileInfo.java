package org.red.interactive.block;

import org.bukkit.block.TileState;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.red.interactive.InteractiveObjInfo;
import org.red.library.interactive.block.InteractiveTile;
import org.red.library.interactive.block.InteractiveTileAnnotation;
import org.red.library.util.persistent.NameSpaceKeyPersistentDataType;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public final class InteractiveTileInfo extends InteractiveObjInfo<TileState> {
    private final Map<InteractiveTileAnnotation.Act, MethodInfo> methodInfos = new HashMap<>();
    public InteractiveTileInfo(InteractiveTile obj) {
        super(obj);
    }

    @Override
    public void setEventInObj(TileState obj) {
        PersistentDataContainer persistentDataContainer = obj.getPersistentDataContainer();
        persistentDataContainer.set(INTERACTIVE_KEY, NameSpaceKeyPersistentDataType.INSTANCE, getKey());
        obj.update();
    }

    @Override
    public InteractiveTile getObj() {
        return (InteractiveTile) super.getObj();
    }

    @Override
    public void settingInteractiveObj() {
        for (Method method : this.getObj().getClass().getMethods()) {
            if (!method.isAnnotationPresent(InteractiveTileAnnotation.class))
                return;

            InteractiveTileAnnotation annotation = method.getAnnotation(InteractiveTileAnnotation.class);
            InteractiveTileAnnotation.Act act = annotation.act();
            boolean shift = annotation.shift();

            Class<?>[] classes = method.getParameterTypes();

            if (classes.length != 1)
                return;

            Class<?> clazz = classes[0];
            if ((clazz.isAssignableFrom(PlayerSwapHandItemsEvent.class) && act == InteractiveTileAnnotation.Act.BREAK) ||
                    (clazz.isAssignableFrom(PlayerInteractEvent.class))) {

                MethodInfo methodInfo = this.methodInfos.computeIfAbsent(act, k -> new MethodInfo(null, null));
                methodInfo.setMethod(method, shift);
            }
        }
    }
}
