package org.red.game.skill;

import org.bukkit.attribute.Attribute;
import org.red.game.skill.active.Active;
import org.red.library.a_.entity.A_Entity;

import java.util.ArrayList;
import java.util.List;

public abstract class Skill {

    private final List<Active> actives = new ArrayList<>();

    public void run(A_Entity entity) {
        for (Active active : actives) {
            active.run();
        }
    }
}
