package org.red.library.skill.passive;

import org.red.library.a_.entity.A_Entity;

public interface Passive {
    void run(A_Entity caster, Object... args);
}
