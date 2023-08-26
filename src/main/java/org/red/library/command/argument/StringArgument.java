package org.red.library.command.argument;

import org.bukkit.command.CommandSender;
import org.red.library.command.CommandNode;
import org.red.library.command.NewCommand;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringArgument implements Argument<String> {
    private final Map<String, CommandNode<?>> next_map = new HashMap<>();
    private final CommandNode<?> defaultNext;

    public StringArgument(CommandNode<?> defaultNext) {
        this.defaultNext = defaultNext;
    }

    public StringArgument(List<String> str, CommandNode<?> defaultNext) {
        this.defaultNext = defaultNext;
    }


    @Override
    public CommandNode<?> next(String arg) {
        return null;
    }

    @Override
    public Type getType() {
        return null;
    }

    @Override
    public StringArgument setNextArg(String arg, CommandNode<?> next) {
        return null;
    }

    @Override
    public CommandNode<?> getDefaultNext() {
        return this.defaultNext;
    }

    @Override
    public List<String> getTabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
