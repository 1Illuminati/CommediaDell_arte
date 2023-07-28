package org.red.library.game.setting;

import org.bukkit.Material;
import org.bukkit.event.inventory.ClickType;
import org.red.library.inventory.CustomGui;
import org.red.library.item.ItemBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class SettingInventory extends CustomGui {
    private final Setting setting;
    public SettingInventory(String title, int size, Setting setting) {
        super(title, size);
        this.setting = setting;
    }

    public SettingInventory(Setting setting) {
        this("게임 설정", 27, setting);
    }

    public Setting getSetting() {
        return this.setting;
    }

    protected void setResetButton(int slot, Material material) {
        this.setItem(slot, new ItemBuilder(material).setDisplayName("§f초기화").setLore("§f모든 설정을 초기화합니다.").build());
    }

    protected void setBooleanPropertiesButton(int slot, Material material, Setting.Properties<Boolean> properties) {
        this.setItem(slot, new ItemBuilder(material).setDisplayName(properties.getKey().getKey()).
                setLore(setting.getValue(properties) ? "§aTrue" : "§cFalse").build());

        this.setButton(slot, event -> {
            event.setCancelled(true);
            setting.setValue(properties, !setting.getValue(properties));
        });
    }

    protected <T extends Number> void setIntegerPropertiesButton(int slot, Material material, Setting.Properties<T> properties, Map<ClickType, T> clickTypeValue, T max, T min) {
        List<String> lore = new ArrayList<>();
        lore.add("§f현재 값: " + setting.getValue(properties));
        for (ClickType type : clickTypeValue.keySet()) {
            lore.add("§f" + type.name() + " +" + clickTypeValue.get(type));
        }

        this.setItem(slot, new ItemBuilder(material).setDisplayName(properties.getKey().getKey()).setLore(lore).build());

        this.setButton(slot, event -> {
            event.setCancelled(true);

            if (properties.getClassType() == Integer.class) {
                Number value = setting.getValue(properties).intValue() + clickTypeValue.get(event.getClick()).intValue();
                this.setting.setValue(properties, (T) value);
            }
        });
    }
}
