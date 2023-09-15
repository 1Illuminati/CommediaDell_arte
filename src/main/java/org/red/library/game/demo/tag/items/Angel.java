package org.red.library.game.demo.tag.items;

import org.bukkit.event.player.PlayerInteractEvent;
import org.red.library.a_.entity.A_Entity;
import org.red.library.game.demo.tag.TagGame;
import org.red.library.game.demo.tag.TagGameSkill;
import org.red.library.item.event.EventItem;
import org.red.library.item.event.EventItemAnnotation;

public abstract class Angel implements EventItem {

    @EventItemAnnotation(act = EventItemAnnotation.Act.RIGHT_CLICK_AIR, shift = EventItemAnnotation.Shift.BOTH)
    public void rightClickAir(PlayerInteractEvent event) {
        this.rightClickBlock(event);
    }

    @EventItemAnnotation(act = EventItemAnnotation.Act.RIGHT_CLICK_BLOCK, shift = EventItemAnnotation.Shift.BOTH)
    public void rightClickBlock(PlayerInteractEvent event) {

    }

    public static class AngelSkill extends TagGameSkill {
        protected AngelSkill(TagGame tagGame) {
            super(tagGame);
        }

        @Override
        public void onSkill(A_Entity caster) {

        }

        @Override
        public String skillName() {
            return null;
        }

        @Override
        public double cooldown() {
            return 0;
        }
    }
}
