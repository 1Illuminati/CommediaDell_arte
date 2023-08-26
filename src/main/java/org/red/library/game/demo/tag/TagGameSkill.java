package org.red.library.game.demo.tag;

import org.bukkit.entity.Entity;
import org.red.library.entity.a_.A_Entity;
import org.red.library.entity.a_.player.A_Player;
import org.red.library.skill.Skill;

public abstract class TagGameSkill implements Skill {
    private final TagGame game;
    private final A_Entity caster;

    public TagGameSkill(TagGame game, A_Entity caster) {
        this.game = game;
        this.caster = caster;
    }

    public abstract void onSkill();

    public TagGame game() {
        return game;
    }

    @Override
    public A_Entity caster() {
        return caster;
    }

    @Override
    public void run() {
        if (!game.getJoinPlayers().contains(caster.getAOfflinePlayer())) {
            caster.sendMessage("게임을 플레이 할 경우에만 사용이 가능합니다.");
            return;
        }

        onSkill();
    }
}
