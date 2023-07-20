package org.red.library.skill;

import org.bukkit.entity.Entity;

public interface Skill extends Runnable {
    Entity caster();
}
