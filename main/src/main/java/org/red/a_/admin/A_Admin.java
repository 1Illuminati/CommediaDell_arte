package org.red.a_.admin;

import java.util.UUID;

public enum A_Admin {
    RED_KILLER(UUID.fromString("a9f022ea-c7b0-4b13-8543-e6ed24e8396f")),
    LASTDICE(UUID.fromString("8b8d99d0-b102-4d5a-82eb-844dcf0ca7d4")),
    ARLECCHINO(UUID.fromString("5652f272-bced-4a09-8785-3e5bf260a3f9"));
    private final UUID uuid;
    A_Admin(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }
}
