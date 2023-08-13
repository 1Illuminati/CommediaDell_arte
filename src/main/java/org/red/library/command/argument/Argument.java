package org.red.library.command.argument;

import org.red.library.command.NewCommand;

public interface Argument extends NewCommand.Node {
    void setNextArg(String arg, NewCommand.Node next);
    void setNextArgAll(NewCommand.Node next);

    enum Type {
        STRING,
        INT,
        DOUBLE,
        BOOLEAN,
        PLAYER,
        OFFLINE_PLAYER,
        CUSTOM
    }
}
