package org.red.block.event;

import org.bukkit.block.BlockState;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.red.CommediaDell_arte;
import org.red.library.interactive.block.InteractiveTile;
import org.red.library.interactive.block.InteractiveTileAnnotation;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class EventBlockManager {
    private final InteractiveTile interactiveTile;
    private final Map<InteractiveTileAnnotation.Act, BlockEventMethod> methods = new HashMap<>();

    public static void registerEventBlock(InteractiveTile interactiveTile) {
    }

    private EventBlockManager(InteractiveTile interactiveTile) {
        this.interactiveTile = interactiveTile;

        for (Method method : interactiveTile.getClass().getMethods())
            putMethod(method);
    }

    private InteractiveTile getEventBlock() {
        return interactiveTile;
    }

    private void runEvent(Event event, InteractiveTileAnnotation.Act act, boolean shift) {
        EventBlockManager.BlockEventMethod method = methods.getOrDefault(act, null);

        if (method == null)
            return;

        try {
            method.getMethod(shift).invoke(this.interactiveTile, event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void putMethod(Method method) {
        if (!method.isAnnotationPresent(InteractiveTileAnnotation.class))
            return;

        InteractiveTileAnnotation.Act act = method.getAnnotation(InteractiveTileAnnotation.class).act();
        boolean shift = method.getAnnotation(InteractiveTileAnnotation.class).shift();
        Class<?>[] classes = method.getParameterTypes();

        if (classes.length != 1)
            return;

        Class<?> clazz = classes[0];
        if ((clazz.isAssignableFrom(BlockBreakEvent.class) && act == InteractiveTileAnnotation.Act.BREAK) ||
                (clazz.isAssignableFrom(PlayerInteractEvent.class))) {

            if (this.methods.containsKey(act)) {
                EventBlockManager.BlockEventMethod eventMethod = this.methods.get(act);

                if (shift) {
                    eventMethod.setPressedMethod(method);
                } else {
                    eventMethod.setUnPressedMethod(method);
                }
            } else {
                this.methods.put(act, new EventBlockManager.BlockEventMethod(act, shift ? method : null, shift ? null : method));
            }
        }
    }

    private void setEventInBlock(BlockState blockState) {
        blockState.setMetadata("a_block_event", new FixedMetadataValue(CommediaDell_arte.getPlugin(), this.interactiveTile.getKey().toString()));
    }


    private static class BlockEventMethod {
        private final InteractiveTileAnnotation.Act act;
        private Method pressedMethod;
        private Method unPressedMethod;

        private BlockEventMethod(InteractiveTileAnnotation.Act act, Method pressedMethod, Method unPressedMethod) {
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

        public InteractiveTileAnnotation.Act getAct() {
            return act;
        }

        public Method getMethod(boolean shift) {
            return shift ? pressedMethod : unPressedMethod;
        }
    }
}
