package org.red.core;

import org.bukkit.Location;

public class Core {
    public final Location coreLoc;
    public int coreHealth = 500;

    private Core(Location coreLoc) {
        this.coreLoc = coreLoc;
    }
}
