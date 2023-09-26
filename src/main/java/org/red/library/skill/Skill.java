package org.red.library.skill;

import org.bukkit.Keyed;
import org.red.library.a_.entity.A_Entity;

public interface Skill extends Keyed {

    void run(A_Entity caster);
}
