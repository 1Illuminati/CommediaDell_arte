package org.red.library.command;

import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class NewCommand {
    private final String name;
    private final List<ArgumentType> list = new ArrayList<>();

    public NewCommand(String name) {
        this.name = name;
    }

    public NewCommand addArgument(ArgumentType type) {
        list.add(type);
        return this;
    }

    public void runCommand(RunCommand runCommand) {

    }

    public interface RunCommand {
        void runCommand(CommandSender sender, Object[] args);
    }

    enum ArgumentType {
        PLAYER,
        OFFLINE_PLAYER,
        PLAYER_UUID,
        UUID,
        INT,
        STRING,
        BOOLEAN,
        DOUBLE,
        LOCATION,
        VECTOR3,
        VECTOR2,
        WAY,
        CUSTOM
    }
}
