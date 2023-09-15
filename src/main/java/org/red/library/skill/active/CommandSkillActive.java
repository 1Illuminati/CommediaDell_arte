package org.red.library.skill.active;

import org.red.library.entity.a_.A_Entity;

public interface CommandSkillActive extends SkillActive {
    String command();
    void runCommand(A_Entity caster, String... args);
}
