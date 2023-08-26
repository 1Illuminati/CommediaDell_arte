package org.red.library.item.event;

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
import org.red.library.CommediaDell_arte;
import org.red.library.entity.a_.player.A_Player;
import org.red.library.item.ItemBuilder;
import org.red.library.util.map.persistent.NameSpaceKeyPersistentDataType;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public final class EventItemManager {
    private static final Map<NamespacedKey, EventItemManager> map = new HashMap<>();
    private static final NamespacedKey key = new NamespacedKey(CommediaDell_arte.getPlugin(), "RedKillerLibrary_EventItem");

    public static void registerEventItem(EventItem eventItem) {
        NamespacedKey key = eventItem.getKey();
        CommediaDell_arte.sendDebugLog("Register EventItem: " + key.getKey());
        map.put(key, new EventItemManager(eventItem));
    }

    public static void setEventItemInItem(ItemStack itemStack, EventItem eventItem) {
        NamespacedKey key = eventItem.getKey();
        if (!map.containsKey(key))
            EventItemManager.registerEventItem(eventItem);

        EventItemManager manager = map.get(key);
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
        EventItemManager manager = map.get(eventItem.getKey());
        manager.runEvent(event, act, player.isSneaking() ? EventItemAnnotation.Shift.PRESSED : EventItemAnnotation.Shift.NOT_PRESSED);
    }

    private final EventItem eventItem;
    private final Map<EventItemAnnotation.Act, EventMethod> methods = new HashMap<>();

    private EventItemManager(EventItem eventItem) {
        this.eventItem = eventItem;

        for (Method method : eventItem.getClass().getMethods())
            putMethod(method);
    }

    private EventItem getEventItem() {
        return eventItem;
    }

    private void runEvent(Event event, EventItemAnnotation.Act act, EventItemAnnotation.Shift shift) {
        EventMethod method = methods.getOrDefault(act, null);

        if (method == null)
            return;

        EventItemAnnotation.Shift methodShift = method.getShift();
        if (methodShift == EventItemAnnotation.Shift.BOTH || methodShift == shift) {
            try {
                method.getMethod().invoke(event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void putMethod(Method method) {
        if (!method.isAnnotationPresent(EventItemAnnotation.class))
            return;

        EventItemAnnotation.Act act = method.getAnnotation(EventItemAnnotation.class).act();
        EventItemAnnotation.Shift shift = method.getAnnotation(EventItemAnnotation.class).shift();
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
            methods.put(act, new EventMethod(shift, act, method));
        }
    }

    private void setEventInItem(ItemStack itemStack) {
        new ItemBuilder(itemStack).setPersistentDataContainer(key, NameSpaceKeyPersistentDataType.INSTANCE, eventItem.getKey());
    }

    private static class EventMethod {
        private final EventItemAnnotation.Shift shift;
        private final EventItemAnnotation.Act act;
        private final Method method;

        private EventMethod(EventItemAnnotation.Shift shift, EventItemAnnotation.Act act, Method method) {
            this.shift = shift;
            this.act = act;
            this.method = method;
        }

        public EventItemAnnotation.Shift getShift() {
            return shift;
        }

        public EventItemAnnotation.Act getAct() {
            return act;
        }

        public Method getMethod() {
            return method;
        }
    }
}
