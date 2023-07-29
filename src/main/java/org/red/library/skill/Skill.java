package org.red.library.skill;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.red.library.CommediaDell_arte;

public abstract class Skill implements Runnable {
    public abstract Entity caster();

    public abstract NamespacedKey key();

    protected abstract void skill();
    @Override
    public void run() {
        skill();
        CommediaDell_arte.sendDebugLog(caster().getUniqueId() + " use " + key().toString() + " Skill.");
    }
}
