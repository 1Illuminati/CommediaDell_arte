package org.red.library.game.demo.tag;

import org.bukkit.*;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.a_.entity.player.offline.A_OfflinePlayer;
import org.red.library.game.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TagGame extends Game {

    private static final TagGameSetting setting = new TagGameSetting();
    private final List<A_OfflinePlayer> runnerPlayers = new ArrayList<>();
    private final List<A_OfflinePlayer> chaserPlayers = new ArrayList<>();
    private final List<A_OfflinePlayer> deadPlayers = new ArrayList<>();
    //private final Timer timer = new Ti(new NamespacedKey(this.getPlugin(), "술래잡기"), setting.getValue(TagGameSetting.GAME_TIME) * 20 + 80);
    private final KeyedBossBar bossBar = Bukkit.createBossBar(new NamespacedKey(this.getPlugin(), "술래잡기"), "술래잡기", BarColor.RED, BarStyle.SOLID);
    @Override
    public String gameDisplayName() {
        return null;
    }

    @Override
    public Plugin getPlugin() {
        return null;
    }

    @Override
    public String getName() {
        return "술래잡기";
    }

    @Override
    public void start() {
        TagGameSetting setting = getSetting();
        Location spawnLocation = setting.getValue(TagGameSetting.SPAWN_LOCATION);
        int chaserNum = setting.getValue(TagGameSetting.CHASER_NUM);
        boolean cantStart = false;

        if (getJoinPlayers().size() <= 1) {
            this.sendMessage(ChatColor.RED + "게임에 참여한 플레이어가 2명 이상이어야 합니다.");
            cantStart = true;
        }

        if (spawnLocation == null) {
            this.sendMessage(ChatColor.RED + "스폰 위치가 설정되지 않았습니다.");
            cantStart = true;
        }

        if (cantStart) {
            this.stop();
            return;
        }

        this.getJoinPlayers().forEach(aOfflinePlayer -> {
            A_Player aPlayer = aOfflinePlayer.getAPlayer();

            if (aPlayer == null) {
                this.sendMessage(ChatColor.RED + aOfflinePlayer.getName() + "님이 오프라인 상태임으로 게임에서 제외됩니다.");
                return;
            }

            int rand = new Random().nextInt(getJoinPlayers().size() - runnerPlayers.size());
            boolean isChaser = rand < chaserNum - chaserPlayers.size();

            aPlayer.setGameMode(GameMode.SPECTATOR);
            bossBar.addPlayer(aPlayer.getPlayer());

            for (int i = 0; i < 3; i++) {
                int temp = i;
                Bukkit.getScheduler().runTaskLater(this.getPlugin(), () -> {
                    aPlayer.sendTitle(ChatColor.YELLOW + Integer.toString(temp) + "초 뒤에 게임이 시작됩니다!", ChatColor.GRAY + "술래는 누구?");
                    aPlayer.playSound(aPlayer.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
                }, 60 - i * 20);
            }

            Bukkit.getScheduler().runTaskLater(this.getPlugin(), () -> {
                aPlayer.teleport(spawnLocation);
                aPlayer.playSound(aPlayer.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
                aPlayer.setGameMode(GameMode.ADVENTURE);

                if (isChaser) {
                    chaserPlayers.add(aOfflinePlayer);
                    aPlayer.sendTitle(ChatColor.RED + "당신은 술래입니다!", ChatColor.GRAY + "도망자들을 제한시간안에 모두 사냥해야합니다!");
                    aPlayer.setWalkSpeed(setting.getValue(TagGameSetting.CHASER_SPEED).floatValue());
                    aPlayer.addPotionEffect(PotionEffectType.BLINDNESS, 400, 5);
                    aPlayer.addPotionEffect(PotionEffectType.JUMP, 400, 130);
                    aPlayer.addPotionEffect(PotionEffectType.SLOW, 400, 5);
                    aPlayer.addPotionEffect(PotionEffectType.GLOWING, 400, 5);
                    aPlayer.addPotionEffect(PotionEffectType.WEAKNESS, 400, 5);
                    aPlayer.addPotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100000, 4);
                } else {
                    runnerPlayers.add(aOfflinePlayer);
                    aPlayer.sendTitle(ChatColor.GREEN + "당신은 도망자입니다!", ChatColor.GRAY + "술래로부터 제한시간동안 살아남아야 합니다!");
                    aPlayer.setWalkSpeed(setting.getValue(TagGameSetting.RUNNER_SPEED).floatValue());
                }
            }, 80);
        });
    }

    @Override
    public void stop() {
        this.getJoinPlayers().forEach(aOfflinePlayer -> {
            A_Player aPlayer = aOfflinePlayer.getAPlayer();

            if (aPlayer == null) {
                return;
            }

            aPlayer.setWalkSpeed(0.2f);
            aPlayer.getActivePotionEffects().forEach(potionEffect -> aPlayer.removePotionEffect(potionEffect.getType()));
        });
    }

    @Override
    public TagGameSetting getSetting() {
        return setting;
    }

    public KeyedBossBar getBossBar() {
        return this.bossBar;
    }
}
