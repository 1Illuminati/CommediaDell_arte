package org.red.library.game.demo.tag;

import org.bukkit.*;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.red.library.CommediaDell_arte;
import org.red.library.entity.player.NewPlayer;
import org.red.library.entity.player.offline.NewOfflinePlayer;
import org.red.library.game.Game;
import org.red.library.game.GameTimer;
import org.red.library.game.setting.Setting;
import org.red.library.util.Timer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TagGame extends Game implements GameTimer {
    private static final TagGameSetting setting = new TagGameSetting();
    private final List<NewOfflinePlayer> runnerPlayers = new ArrayList<>();
    private final List<NewOfflinePlayer> chaserPlayers = new ArrayList<>();
    private final List<NewOfflinePlayer> deadPlayers = new ArrayList<>();
    private final Timer timer = new Timer(new NamespacedKey(this.getPlugin(), "술래잡기"), setting.getValue(TagGameSetting.GAME_TIME) * 20 + 80);
    private final KeyedBossBar bossBar = Bukkit.createBossBar(new NamespacedKey(this.getPlugin(), "술래잡기"), "술래잡기", BarColor.RED, BarStyle.SOLID);
    @Override
    public String gameDisplayName() {
        return null;
    }

    @Override
    public Plugin getPlugin() {
        return CommediaDell_arte.getPlugin();
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

        this.getJoinPlayers().forEach(newOfflinePlayer -> {
            NewPlayer newPlayer = newOfflinePlayer.getNewPlayer();

            if (newPlayer == null) {
                this.sendMessage(ChatColor.RED + newOfflinePlayer.getName() + "님이 오프라인 상태임으로 게임에서 제외됩니다.");
                return;
            }

            int rand = new Random().nextInt(getJoinPlayers().size() - runnerPlayers.size());
            boolean isChaser = rand < chaserNum - chaserPlayers.size();

            newPlayer.setGameMode(GameMode.SPECTATOR);
            bossBar.addPlayer(newPlayer.getPlayer());

            for (int i = 0; i < 3; i++) {
                int temp = i;
                Bukkit.getScheduler().runTaskLater(this.getPlugin(), () -> {
                    newPlayer.sendTitle(ChatColor.YELLOW + Integer.toString(temp) + "초 뒤에 게임이 시작됩니다!", ChatColor.GRAY + "술래는 누구?");
                    newPlayer.playSound(newPlayer.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
                }, 60 - i * 20);
            }

            Bukkit.getScheduler().runTaskLater(this.getPlugin(), () -> {
                newPlayer.teleport(spawnLocation);
                newPlayer.playSound(newPlayer.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
                newPlayer.setGameMode(GameMode.ADVENTURE);

                if (isChaser) {
                    chaserPlayers.add(newOfflinePlayer);
                    newPlayer.sendTitle(ChatColor.RED + "당신은 술래입니다!", ChatColor.GRAY + "도망자들을 제한시간안에 모두 사냥해야합니다!");
                    newPlayer.setWalkSpeed(setting.getValue(TagGameSetting.CHASER_SPEED).floatValue());
                    newPlayer.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 400, 5));
                    newPlayer.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 400, 130));
                    newPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 400, 5));
                    newPlayer.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 400, 5));
                    newPlayer.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 400, 5));
                    newPlayer.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100000, 4));
                } else {
                    runnerPlayers.add(newOfflinePlayer);
                    newPlayer.sendTitle(ChatColor.GREEN + "당신은 도망자입니다!", ChatColor.GRAY + "술래로부터 제한시간동안 살아남아야 합니다!");
                    newPlayer.setWalkSpeed(setting.getValue(TagGameSetting.RUNNER_SPEED).floatValue());
                }
            }, 80);
        });
    }

    @Override
    public void stop() {
        this.getJoinPlayers().forEach(newOfflinePlayer -> {
            NewPlayer newPlayer = newOfflinePlayer.getNewPlayer();

            if (newPlayer == null) {
                return;
            }

            newPlayer.setWalkSpeed(0.2f);
            newPlayer.getActivePotionEffects().forEach(potionEffect -> newPlayer.removePotionEffect(potionEffect.getType()));
        });
    }

    @Override
    public TagGameSetting getSetting() {
        return setting;
    }

    @Override
    public Timer getTimer() {
        return this.timer;
    }

    @Override
    public KeyedBossBar getBossBar() {
        return this.bossBar;
    }
}
