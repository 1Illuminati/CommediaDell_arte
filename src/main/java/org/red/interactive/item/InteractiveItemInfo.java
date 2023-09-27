package org.red.interactive.item;

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.red.interactive.InteractiveObjInfo;
import org.red.library.interactive.item.InteractiveItem;
import org.red.library.interactive.item.InteractiveItemAnnotation;
import org.red.library.item.ItemBuilder;
import org.red.library.util.persistent.NameSpaceKeyPersistentDataType;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public final class InteractiveItemInfo extends InteractiveObjInfo<ItemStack> {
    private final Map<InteractiveItemAnnotation.Act, MethodInfo> methodInfos = new HashMap<>();
    public InteractiveItemInfo(InteractiveItem obj) {
        super(obj);
    }

    @Override
    public Class<InteractiveItemAnnotation> getAnnotation() {
        return InteractiveItemAnnotation.class;
    }

    @Override
    public void setEventInObj(ItemStack obj) {
        new ItemBuilder(obj).setPersistentDataContainer(INTERACTIVE_KEY, NameSpaceKeyPersistentDataType.INSTANCE, getKey()).build();
    }

    @Override
    public InteractiveItem getObj() {
        return (InteractiveItem) super.getObj();
    }

    @Override
    public void settingInteractiveObj() {
        for (Method method : this.getObj().getClass().getMethods()) {
            if (!method.isAnnotationPresent(InteractiveItemAnnotation.class))
                return;

            InteractiveItemAnnotation.Act act = method.getAnnotation(InteractiveItemAnnotation.class).act();
            boolean shift = method.getAnnotation(InteractiveItemAnnotation.class).shift();
            Class<?>[] classes = method.getParameterTypes();

            if (classes.length != 1)
                return;

            Class<?> clazz = classes[0];
            if ((clazz.isAssignableFrom(PlayerSwapHandItemsEvent.class) && act == InteractiveItemAnnotation.Act.SWAP_HAND) ||
                    (clazz.isAssignableFrom(PlayerDropItemEvent.class) && act == InteractiveItemAnnotation.Act.DROP) ||
                    (clazz.isAssignableFrom(EntityDamageByEntityEvent.class) && act == InteractiveItemAnnotation.Act.HIT) ||
                    (clazz.isAssignableFrom(BlockBreakEvent.class) && act == InteractiveItemAnnotation.Act.BREAK) ||
                    (clazz.isAssignableFrom(PlayerFishEvent.class) && act == InteractiveItemAnnotation.Act.FISHING) ||
                    (clazz.isAssignableFrom(PlayerInteractEvent.class))) {

                MethodInfo methodInfo = this.methodInfos.computeIfAbsent(act, k -> new MethodInfo(null, null));
                methodInfo.setMethod(method, shift);
            }
        }
    }
}
