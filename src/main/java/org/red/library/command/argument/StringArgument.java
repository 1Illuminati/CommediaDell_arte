package org.red.library.command.argument;

import org.red.library.command.NewCommand;

import java.util.HashMap;
import java.util.Map;

public class StringArgument implements Argument {
    Map<String, NewCommand.Node> next_map = new HashMap<>();

    @Override
    public NewCommand.Node next(String arg) {
        return next_map.getOrDefault(arg, null);
    }

    @Override
    public void setNextArg(String arg, NewCommand.Node next) {
        next_map.put(arg, next);
    }

    @Override
    public void setNextArgAll(NewCommand.Node next) {
        next_map.keySet().forEach(key -> next_map.put(key, next));
    }
}
