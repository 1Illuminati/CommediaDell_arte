package org.red.library.game.demo.tag;

import org.bukkit.plugin.Plugin;
import org.red.library.CommediaDell_arte;
import org.red.library.game.Game;

public class TagGame extends Game {
    @Override
    public String gameDisplayName() {
        return null;
    }

    @Override
    public Plugin getPlugin() {
        return CommediaDell_arte.getPlugin();
    }

    @Override
    public String getName() {
        return "술래잡기";
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
