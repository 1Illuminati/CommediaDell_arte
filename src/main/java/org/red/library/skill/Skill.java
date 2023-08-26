package org.red.library.skill;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.red.library.CommediaDell_arte;
import org.red.library.entity.a_.A_Entity;

public interface Skill extends Runnable {
    A_Entity caster();

    NamespacedKey key();
}
