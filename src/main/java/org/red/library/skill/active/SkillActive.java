package org.red.library.skill.active;

import org.red.library.a_.entity.A_Entity;

public interface SkillActive {
    void run(A_Entity caster, Object... args);
}
