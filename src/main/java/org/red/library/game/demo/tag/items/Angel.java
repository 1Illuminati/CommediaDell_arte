package org.red.library.game.demo.tag.items;

import org.bukkit.event.player.PlayerInteractEvent;
import org.red.library.a_.entity.A_Entity;
import org.red.library.game.demo.tag.TagGame;
import org.red.library.game.demo.tag.TagGameSkill;
import org.red.library.interactive.item.InteractiveItem;
import org.red.library.interactive.item.InteractiveItemAnnotation;

public abstract class Angel implements InteractiveItem {

    @InteractiveItemAnnotation(act = InteractiveItemAnnotation.Act.RIGHT_CLICK_AIR)
    public void rightClickAir(PlayerInteractEvent event) {
        this.rightClickBlock(event);
    }

    @InteractiveItemAnnotation(act = InteractiveItemAnnotation.Act.RIGHT_CLICK_BLOCK, shift = true)
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
        public void run(A_Entity caster, Object... args) {

        }
    }
}
