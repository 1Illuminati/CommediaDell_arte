package org.red.library.skill;

import org.bukkit.NamespacedKey;
import org.red.library.entity.a_.A_Entity;

public interface Skill {
    NamespacedKey key();

    void run(A_Entity caster);
}
