package org.red.library.command.argument;

import org.bukkit.command.CommandSender;
import org.red.library.command.CommandNode;

import java.util.List;

public interface Argument<T> extends CommandNode<T> {
    Argument<T> setNextArg(T arg, CommandNode<?> next);
    CommandNode<?> getDefaultNext();
    List<String> getTabComplete(CommandSender sender, String[] args);
}
