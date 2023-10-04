package org.red.library.skill.active;

import org.red.library.a_.entity.A_Entity;

public interface Active {
    void run(A_Entity caster, Object... args);
}
