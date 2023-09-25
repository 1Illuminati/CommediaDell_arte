package org.red.item.event;

import org.bukkit.NamespacedKey;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.red.CommediaDell_arte;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.item.ItemBuilder;
import org.red.library.item.event.EventItem;
import org.red.library.item.event.EventItemAnnotation;
import org.red.library.util.map.NameSpaceMap;
import org.red.library.util.persistent.NameSpaceKeyPersistentDataType;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public final class EventItemInfo {
    private static final NameSpaceMap<EventItemInfo> map = new NameSpaceMap<>();
    private static final NamespacedKey key = new NamespacedKey(CommediaDell_arte.getPlugin(), "RedKillerLibrary_EventItem");

    public static void registerEventItem(EventItem eventItem) {
        NamespacedKey key = eventItem.getKey();
        CommediaDell_arte.sendDebugLog("Register EventItem: " + key.getKey());
        map.put(key, new EventItemInfo(eventItem));
    }

    public static void setEventItemInItem(ItemStack itemStack, EventItem eventItem) {
        NamespacedKey key = eventItem.getKey();
        if (!map.containsKey(key))
            EventItemInfo.registerEventItem(eventItem);

        EventItemInfo manager = map.get(key);
        manager.setEventInItem(itemStack);
    }

    @Nullable
    public static EventItem getEventItemByKey(NamespacedKey key) {
        if (!map.containsKey(key))
            return null;

        return map.get(key).getEventItem();
    }

    public static boolean hasEventItem(ItemStack itemStack) {
        return itemStack != null && itemStack.getItemMeta() != null && itemStack.getItemMeta().getPersistentDataContainer().has(key, NameSpaceKeyPersistentDataType.INSTANCE);
    }

    @Nullable
    public static NamespacedKey getKeyByItem(ItemStack itemStack) {
        if (!hasEventItem(itemStack))
            return null;

        return itemStack.getItemMeta().getPersistentDataContainer().get(key, NameSpaceKeyPersistentDataType.INSTANCE);
    }

    @Nullable
    public static EventItem getEventItemByItem(ItemStack itemStack) {
        NamespacedKey key = getKeyByItem(itemStack);
        if (key == null)
            return null;

        return getEventItemByKey(key);
    }

    public static void runItemEvent(A_Player player, ItemStack itemStack, EventItemAnnotation.Act act, Event event) {
        if (!hasEventItem(itemStack))
            return;

        EventItem eventItem = getEventItemByItem(itemStack);
        EventItemInfo manager = map.get(eventItem.getKey());
        manager.runEvent(event, act, player.isSneaking());
    }

    private final EventItem eventItem;
    private final Map<EventItemAnnotation.Act, EventMethod> methods = new HashMap<>();

    private EventItemInfo(EventItem eventItem) {
        this.eventItem = eventItem;

        for (Method method : eventItem.getClass().getMethods())
            putMethod(method);
    }

    private EventItem getEventItem() {
        return eventItem;
    }

    private void runEvent(Event event, EventItemAnnotation.Act act, boolean shift) {
        EventMethod method = methods.getOrDefault(act, null);

        if (method == null)
            return;

        try {
            method.getMethod(shift).invoke(this.eventItem, event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void putMethod(Method method) {
        if (!method.isAnnotationPresent(EventItemAnnotation.class))
            return;

        EventItemAnnotation.Act act = method.getAnnotation(EventItemAnnotation.class).act();
        boolean shift = method.getAnnotation(EventItemAnnotation.class).shift();
        Class<?>[] classes = method.getParameterTypes();

        if (classes.length != 1)
            return;

        Class<?> clazz = classes[0];
        if ((clazz.isAssignableFrom(PlayerSwapHandItemsEvent.class) && act == EventItemAnnotation.Act.SWAP_HAND) ||
                (clazz.isAssignableFrom(PlayerDropItemEvent.class) && act == EventItemAnnotation.Act.DROP) ||
                (clazz.isAssignableFrom(EntityDamageByEntityEvent.class) && act == EventItemAnnotation.Act.HIT) ||
                (clazz.isAssignableFrom(BlockBreakEvent.class) && act == EventItemAnnotation.Act.BREAK) ||
                (clazz.isAssignableFrom(PlayerFishEvent.class) && act == EventItemAnnotation.Act.FISHING) ||
                (clazz.isAssignableFrom(PlayerInteractEvent.class))) {

            if (this.methods.containsKey(act)) {
                EventMethod eventMethod = this.methods.get(act);

                if (shift) {
                    eventMethod.setPressedMethod(method);
                } else {
                    eventMethod.setUnPressedMethod(method);
                }
            } else {
                this.methods.put(act, new EventMethod(act, shift ? method : null, shift ? null : method));
            }
        }
    }

    private void setEventInItem(ItemStack itemStack) {
        new ItemBuilder(itemStack).setPersistentDataContainer(key, NameSpaceKeyPersistentDataType.INSTANCE, eventItem.getKey()).build();
    }

    private static class EventMethod {
        private final EventItemAnnotation.Act act;
        private Method pressedMethod;
        private Method unPressedMethod;

        private EventMethod(EventItemAnnotation.Act act, Method pressedMethod, Method unPressedMethod) {
            this.act = act;
            this.pressedMethod = pressedMethod;
            this.unPressedMethod = unPressedMethod;
        }

        public void setPressedMethod(Method pressedMethod) {
            this.pressedMethod = pressedMethod;
        }

        public void setUnPressedMethod(Method unPressedMethod) {
            this.unPressedMethod = unPressedMethod;
        }

        public EventItemAnnotation.Act getAct() {
            return act;
        }

        public Method getMethod(boolean shift) {
            return shift ? pressedMethod : unPressedMethod;
        }
    }
}
