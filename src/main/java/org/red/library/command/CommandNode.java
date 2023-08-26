package org.red.library.command;

public interface CommandNode<T> {

    CommandNode<?> next(T arg);

    Type getType();

    enum Type {
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
        RUN,
        CUSTOM
    }
}
