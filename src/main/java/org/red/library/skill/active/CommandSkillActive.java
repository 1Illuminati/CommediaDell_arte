package org.red.library.skill.active;

import org.red.library.a_.entity.A_Entity;

public interface CommandSkillActive extends SkillActive {
    String command();
    void runCommand(A_Entity caster, String... args);
}
