package org.red.library.command.argument;

import org.bukkit.command.CommandSender;
import org.red.library.command.CommandNode;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntegerArgument implements Argument<Integer> {
    private final Map<Integer, CommandNode<?>> next_map = new HashMap<>();
    private final CommandNode<?> defaultNext;

    public IntegerArgument(CommandNode<?> defaultNext) {
        this.defaultNext = defaultNext;
    }

    @Override
    public CommandNode<?> next(Integer arg) {
        return next_map.getOrDefault(arg, defaultNext);
    }

    @Override
    public Type getType() {
        return Type.INT;
    }

    @Override
    public IntegerArgument setNextArg(Integer arg, CommandNode<?> next) {
        next_map.put(arg, next);
        return this;
    }

    @Override
    public CommandNode<?> getDefaultNext() {
        return defaultNext;
    }

    @Override
    public List<String> getTabComplete(CommandSender sender, String[] args) {
        return Collections.singletonList("<int>");
    }
}
