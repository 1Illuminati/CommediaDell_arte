package org.red.library.game.demo.tag;

import org.bukkit.Material;
import org.red.library.game.setting.Setting;
import org.red.library.game.setting.SettingInventory;
import org.red.library.inventory.CustomGui;
import org.red.library.item.ItemBuilder;

import java.util.Set;

public final class TagGameSetting extends Setting {
    private static final Setting.Properties<Boolean> sameSkin = new Setting.Properties<>("같은 스킨", false);
    private static final Setting.Properties<Boolean> hideName = new Setting.Properties<>("이름 숨기기", false);
    private static final Setting.Properties<Boolean> disableChat = new Setting.Properties<>("채팅 비활성화", false);
    private static final Setting.Properties<Boolean> disableCommand = new Setting.Properties<>("커맨드 비활성화", false);
    private static final Setting.Properties<Boolean> maintainInventory = new Setting.Properties<>("인벤토리 보존", false);
    private static final Setting.Properties<Boolean> ability = new Setting.Properties<>("능력", false);
    private static final Setting.Properties<Float> chaserSpeed = new Setting.Properties<>("술래 속도", 2.2F);
    private static final Setting.Properties<Float> runnerSpeed = new Setting.Properties<>("도망자 속도", 2.0F);
    private static final Setting.Properties<Integer> maxPlayer = new Setting.Properties<>("최대 참여자 수", 10);
    private static final Setting.Properties<Integer> chaserNum = new Setting.Properties<>("술래 수", 1);

    @Override
    public Properties[] getProperties() {
        return new Properties[] {
                sameSkin, hideName, disableChat, disableCommand, maintainInventory, ability, chaserSpeed, runnerSpeed, maxPlayer, chaserNum
        };
    }

    @Override
    public SettingInventory getInventory() {
        return null;
    }


    public static class SettingGui extends CustomGui {

        public SettingGui(TagGameSetting setting) {
            super("설정", 27);
        }

        private void booleanButton(int slot, String name, boolean value) {
            this.setItem(slot, new ItemBuilder(value ? Material.GREEN_STAINED_GLASS_PANE : Material.RED_STAINED_GLASS_PANE).setDisplayName("§f" + name)
                    .setLore(value ? "§a활성화" : "§c비활성화").build());

        }
    }
}
