package org.red.library.game.demo.tag.items;

import org.bukkit.entity.Entity;
import org.bukkit.event.player.PlayerInteractEvent;
import org.red.library.entity.player.NewPlayer;
import org.red.library.game.demo.tag.TagGame;
import org.red.library.game.demo.tag.TagGameSkill;
import org.red.library.item.event.EventItem;
import org.red.library.item.event.EventItemAnnotation;
import org.red.library.skill.Skill;

public abstract class Angel implements EventItem {

    @EventItemAnnotation(act = EventItemAnnotation.Act.SHIFT_LEFT_CLICK_BLOCK)
    public void shiftRightClickBlock(PlayerInteractEvent event) {
        this.rightClickBlock(event);
    }

    @EventItemAnnotation(act = EventItemAnnotation.Act.SHIFT_RIGHT_CLICK_AIR)
    public void shiftRightClickAir(PlayerInteractEvent event) {
        this.rightClickAir(event);
    }

    @EventItemAnnotation(act = EventItemAnnotation.Act.RIGHT_CLICK_AIR)
    public void rightClickAir(PlayerInteractEvent event) {
        this.rightClickBlock(event);
    }

    @EventItemAnnotation(act = EventItemAnnotation.Act.RIGHT_CLICK_BLOCK)
    public void rightClickBlock(PlayerInteractEvent event) {

    }

    public static class AngelSkill extends TagGameSkill {
        public AngelSkill(NewPlayer caster, TagGame game) {
            super(game, caster);
        }

        @Override
        public void onSkill() {

        }
    }
}
