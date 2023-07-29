package org.red.library.game.demo.tag;

import org.bukkit.Location;
import org.red.library.game.setting.Setting;
import org.red.library.game.setting.SettingGui;

public final class TagGameSetting extends Setting {
    public static final Setting.Properties<Boolean> SAME_SKIN = new Setting.Properties<>("같은 스킨", false);
    public static final Setting.Properties<Boolean> HIDE_NAME = new Setting.Properties<>("이름 숨기기", false);
    public static final Setting.Properties<Boolean> DISABLE_CHAT = new Setting.Properties<>("채팅 비활성화", false);
    public static final Setting.Properties<Boolean> DISABLE_COMMAND = new Setting.Properties<>("커맨드 비활성화", false);
    public static final Setting.Properties<Boolean> MAINTAIN_INVENTORY = new Setting.Properties<>("인벤토리 보존", false);
    public static final Setting.Properties<Boolean> ABILITY = new Setting.Properties<>("능력", false);
    public static final Setting.Properties<Double> CHASER_SPEED = new Setting.Properties<>("술래 속도", 2.2D);
    public static final Setting.Properties<Double> RUNNER_SPEED = new Setting.Properties<>("도망자 속도", 2.0D);
    public static final Setting.Properties<Integer> MAX_PLAYER = new Setting.Properties<>("최대 참여자 수", 10);
    public static final Setting.Properties<Integer> CHASER_NUM = new Setting.Properties<>("술래 수", 1);
    public static final Setting.Properties<Integer> GAME_TIME = new Setting.Properties<>("타이머", 300);
    public static final Setting.Properties<Location> SPAWN_LOCATION = new Setting.Properties<>("스폰 위치", null);

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
