package org.red.library.game.demo.tag;

import org.bukkit.Material;
import org.red.library.inventory.CustomGui;
import org.red.library.item.ItemBuilder;

public final class TagGameSetting {
    private boolean sameSkin = false;
    private boolean hideName = true;
    private boolean disableChat = true;
    private boolean disableCommand = true;
    private boolean maintainInventory = true;
    private boolean ability = true;
    private float chaserSpeed = 2.2f;
    private float runnerSpeed = 2.0f;
    private int maxPlayer = 10;
    private int murderNum = 1;

    public boolean isSameSkin() {
        return sameSkin;
    }

    public void setSameSkin(boolean sameSkin) {
        this.sameSkin = sameSkin;
    }

    public boolean isHideName() {
        return hideName;
    }

    public void setHideName(boolean hideName) {
        this.hideName = hideName;
    }

    public boolean isDisableChat() {
        return disableChat;
    }

    public void setDisableChat(boolean disableChat) {
        this.disableChat = disableChat;
    }

    public boolean isDisableCommand() {
        return disableCommand;
    }

    public void setDisableCommand(boolean disableCommand) {
        this.disableCommand = disableCommand;
    }

    public boolean isMaintainInventory() {
        return maintainInventory;
    }

    public void setMaintainInventory(boolean maintainInventory) {
        this.maintainInventory = maintainInventory;
    }

    public boolean isAbility() {
        return ability;
    }

    public void setAbility(boolean ability) {
        this.ability = ability;
    }

    public float getChaserSpeed() {
        return chaserSpeed;
    }

    public void setChaserSpeed(float chaserSpeed) {
        this.chaserSpeed = chaserSpeed;
    }

    public float getRunnerSpeed() {
        return runnerSpeed;
    }

    public void setRunnerSpeed(float runnerSpeed) {
        this.runnerSpeed = runnerSpeed;
    }

    public int getMaxPlayer() {
        return maxPlayer;
    }

    public void setMaxPlayer(int maxPlayer) {
        this.maxPlayer = maxPlayer;
    }

    public int getMurderNum() {
        return murderNum;
    }

    public void setMurderNum(int murderNum) {
        this.murderNum = murderNum;
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
