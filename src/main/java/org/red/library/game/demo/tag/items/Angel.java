package org.red.library.game.demo.tag.items;

import org.bukkit.event.player.PlayerInteractEvent;
import org.red.library.a_.entity.A_Entity;
import org.red.library.game.demo.tag.TagGame;
import org.red.library.game.demo.tag.TagGameSkill;
import org.red.library.interactive.item.InteractiveItem;

public abstract class Angel implements InteractiveItem {

    public void rightClickAir(PlayerInteractEvent event) {
        this.rightClickBlock(event);
    }

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

        public void run(A_Entity caster, Object... args) {

        }
    }
}
