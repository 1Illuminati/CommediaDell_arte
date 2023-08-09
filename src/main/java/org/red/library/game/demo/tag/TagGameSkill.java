package org.red.library.game.demo.tag;

import org.bukkit.entity.Entity;
import org.red.library.entity.player.APlayer;
import org.red.library.skill.Skill;

public abstract class TagGameSkill extends Skill {
    private final TagGame game;
    private final APlayer caster;

    public TagGameSkill(TagGame game, APlayer caster) {
        this.game = game;
        this.caster = caster;
    }

    public abstract void onSkill();

    public TagGame game() {
        return game;
    }

    @Override
    public Entity caster() {
        return caster.getPlayer();
    }

    @Override
    public void run() {
        if (!game.getJoinPlayers().contains(caster.getNewOfflinePlayer())) {
            caster.sendMessage("게임을 플레이 할 경우에만 사용이 가능합니다.");
            return;
        }

        onSkill();
    }
}
