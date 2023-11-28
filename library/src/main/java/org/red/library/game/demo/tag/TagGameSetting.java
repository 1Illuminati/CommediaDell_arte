package org.red.library.game.demo.tag;

import org.bukkit.Location;
import org.red.library.game.setting.Setting;
import org.red.library.game.setting.SettingGui;

public final class TagGameSetting extends Setting {
    public static final Properties<Boolean> SAME_SKIN = new Properties<>("같은 스킨", false);
    public static final Properties<Boolean> HIDE_NAME = new Properties<>("이름 숨기기", false);
    public static final Properties<Boolean> DISABLE_CHAT = new Properties<>("채팅 비활성화", false);
    public static final Properties<Boolean> DISABLE_COMMAND = new Properties<>("커맨드 비활성화", false);
    public static final Properties<Boolean> MAINTAIN_INVENTORY = new Properties<>("인벤토리 보존", false);
    public static final Properties<Boolean> ABILITY = new Properties<>("능력", false);
    public static final Properties<Double> CHASER_SPEED = new Properties<>("술래 속도", 2.2D);
    public static final Properties<Double> RUNNER_SPEED = new Properties<>("도망자 속도", 2.0D);
    public static final Properties<Integer> MAX_PLAYER = new Properties<>("최대 참여자 수", 10);
    public static final Properties<Integer> CHASER_NUM = new Properties<>("술래 수", 1);
    public static final Properties<Integer> GAME_TIME = new Properties<>("타이머", 300);
    public static final Properties<Location> SPAWN_LOCATION = new Properties<>("스폰 위치", null);

    @Override
    public Properties[] getProperties() {
        return new Properties[] {
                SAME_SKIN,
                HIDE_NAME,
                DISABLE_CHAT,
                DISABLE_COMMAND,
                MAINTAIN_INVENTORY,
                ABILITY,
                CHASER_SPEED,
                RUNNER_SPEED,
                MAX_PLAYER,
                CHASER_NUM,
                GAME_TIME,
                SPAWN_LOCATION
        };
    }

    @Override
    public SettingGui getInventory() {
        return new TagGameSettingGui(this);
    }


    public static class TagGameSettingGui extends SettingGui {

        public TagGameSettingGui(TagGameSetting setting) {
            super("설정", 27, setting);
        }
    }
}
