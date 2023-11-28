package org.red.library.game.demo.tag;

import org.bukkit.GameMode;
import org.bukkit.NamespacedKey;
import org.red.library.a_.entity.A_Entity;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.game.Game;

public abstract class TagGameSkill {
    private final NamespacedKey key;
    private final TagGame tagGame;
    protected TagGameSkill(TagGame tagGame) {
        this.key = new NamespacedKey("TagGame" + tagGame.getGameID(), this.skillName());
        this.tagGame = tagGame;
    }

    public void run(A_Entity caster) {
        if (tagGame.getGameStatus() != Game.GameStatus.RUNNING) {
            caster.sendMessage("게임이 진행중이 아닙니다.");
            return;
        }

        if (caster instanceof A_Player) {
            A_Player player = (A_Player) caster;
            if (!tagGame.getJoinPlayers().contains(player.getAOfflinePlayer())) {
                player.sendMessage("게임에 참여하지 않았습니다.");
                return;
            }

            if (player.getGameMode() == GameMode.SPECTATOR) {
                player.sendMessage("관전자는 스킬을 사용할 수 없습니다.");
                return;
            }
        }

        this.onSkill(caster);
    }

    public NamespacedKey key() {
        return key;
    }

    public abstract void onSkill(A_Entity caster);

    public abstract String skillName();
}
