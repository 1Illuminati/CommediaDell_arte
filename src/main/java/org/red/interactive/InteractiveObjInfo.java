package org.red.interactive;

import org.bukkit.Bukkit;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.red.CommediaDell_arte;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.event.InteractiveRunEvent;
import org.red.library.interactive.InteractiveAct;
import org.red.library.interactive.InteractiveActAnnotation;
import org.red.library.interactive.InteractiveAnnotation;
import org.red.library.interactive.InteractiveObj;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public abstract class InteractiveObjInfo<T> implements Keyed {
    public static final NamespacedKey INTERACTIVE_KEY = new NamespacedKey(CommediaDell_arte.getPlugin(), "interactive");
    private final InteractiveObj<T> obj;
    private final Map<Class<? extends InteractiveAct>, MethodInfo> methodInfos = new HashMap<>();

    public InteractiveObjInfo(InteractiveObj<T> obj) {
        this.obj = obj;
        this.settingInteractiveObj();
    }

    public abstract void setEventInObj(T obj);

    public void runMethod(Class<? extends InteractiveAct> act, A_Player player, Event event) {
        if (!act.isAnnotationPresent(InteractiveActAnnotation.class))
            throw new IllegalArgumentException("The act class must be annotated with InteractiveActAnnotation");
        InteractiveActAnnotation actAnnotation = act.getAnnotation(InteractiveActAnnotation.class);
        Class<? extends Event> eventClass = actAnnotation.event();

        if (eventClass != event.getClass())
            throw new IllegalArgumentException("The event class must be the same as the event class in the act annotation");
        MethodInfo methodInfo = methodInfos.getOrDefault(act, null);
        if (methodInfo == null) return;
        Method method = methodInfo.getMethod(player.isSneaking());
        if (method == null) return;
        InteractiveRunEvent interactiveRunEvent = new InteractiveRunEvent(obj, player, act);
        Bukkit.getPluginManager().callEvent(interactiveRunEvent);
        if (interactiveRunEvent.isCancelled()) return;
        try {
            method.invoke(obj, event);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public InteractiveObj<T> getObj() {
        return obj;
    }

    public void settingInteractiveObj() {
        Arrays.stream(this.getObj().getClass().getMethods()).forEach(this::setMethod);
    }

    protected void setMethod(Method method) {
        if (!method.isAnnotationPresent(InteractiveAnnotation.class))
            return;

        InteractiveAnnotation annotation = method.getAnnotation(InteractiveAnnotation.class);
        Class<? extends InteractiveAct> act = annotation.act();
        boolean shift = annotation.shift();

        if (!act.isAnnotationPresent(InteractiveActAnnotation.class))
            throw new IllegalArgumentException("The act class must be annotated with InteractiveActAnnotation");

        InteractiveActAnnotation actAnnotation = act.getAnnotation(InteractiveActAnnotation.class);
        Class<? extends Event> event = actAnnotation.event();

        Class<?>[] classes = method.getParameterTypes();

        if (classes.length != 1)
            return;

        Class<?> clazz = classes[0];
        if (clazz.isAssignableFrom(event)) {
            MethodInfo methodInfo = this.methodInfos.computeIfAbsent(act, k -> new MethodInfo(null, null));
            methodInfo.setMethod(method, shift);
        }
    }

    @NotNull
    public NamespacedKey getKey() {
        return obj.getKey();
    }
    public static class MethodInfo {
        private Method shiftMethod;
        private Method noShiftMethod;
        public MethodInfo(Method shiftMethod, Method noShiftMethod) {
            this.shiftMethod = shiftMethod;
            this.noShiftMethod = noShiftMethod;
        }

        public Method getMethod(boolean shift) {
            return shift ? shiftMethod : noShiftMethod;
        }

        public boolean hasMethod(boolean shift) {
            return shift ? shiftMethod != null : noShiftMethod != null;
        }

        public void setMethod(Method method, boolean shift) {
            if (shift)
                shiftMethod = method;
            else
                noShiftMethod = method;
        }

        public void removeMethod(boolean shift) {
            if (shift)
                shiftMethod = null;
            else
                noShiftMethod = null;
        }

        public boolean hasBothMethods() {
            return shiftMethod != null && noShiftMethod != null;
        }
    }
}
