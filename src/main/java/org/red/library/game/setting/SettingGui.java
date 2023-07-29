package org.red.library.game.setting;

import org.bukkit.Material;
import org.bukkit.event.inventory.ClickType;
import org.red.library.inventory.CustomGui;
import org.red.library.item.ItemBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class SettingGui extends CustomGui {
    private final Setting setting;
    public SettingGui(String title, int size, Setting setting) {
        super(title, size);
        this.setting = setting;
    }

    public SettingGui(Setting setting) {
        this("게임 설정", 27, setting);
    }

    public Setting getSetting() {
        return this.setting;
    }

    protected void setResetButton(int slot, Material material) {
        this.setItem(slot, new ItemBuilder(material).setDisplayName("§f초기화").setLore("§f모든 설정을 초기화합니다.").build());
    }

    protected void setBooleanPropertiesButton(int slot, Material material, Setting.Properties<Boolean> properties) {
        this.setItem(slot, new ItemBuilder(material).setDisplayName(properties.getName()).
                setLore(setting.getValue(properties) ? "§aTrue" : "§cFalse").build());

        this.setButton(slot, event -> {
            event.setCancelled(true);
            setting.setValue(properties, !setting.getValue(properties));
        });
    }

    protected void setIntegerPropertiesButton(int slot, Material material, Setting.Properties<Integer> properties, Map<ClickType, Integer> clickTypeValue, Integer max, Integer min) {
        List<String> lore = new ArrayList<>();
        lore.add("§f현재 값: " + setting.getValue(properties) + " (최대 " + (max == null ? "없음" : max) + ", 최소 " + (min == null ? "없음" : min) + ")");
        for (ClickType type : clickTypeValue.keySet()) {
            int value = clickTypeValue.get(type);
            lore.add("§f" + type.name() + (value < 0 ? value : " +" + value));
        }

        this.setItem(slot, new ItemBuilder(material).setDisplayName(properties.getName()).setLore(lore).build());

        this.setButton(slot, event -> {
            event.setCancelled(true);
            setting.setValue(properties, setting.getValue(properties) + clickTypeValue.get(event.getClick()));
            if (max != null && setting.getValue(properties) > max) setting.setValue(properties, max);
            if (min != null && setting.getValue(properties) < min) setting.setValue(properties, min);
        });
    }

    protected void setDoublePropertiesButton(int slot, Material material, Setting.Properties<Double> properties, Map<ClickType, Double> clickTypeValue, Double max, Double min) {
        List<String> lore = new ArrayList<>();
        lore.add("§f현재 값: " + setting.getValue(properties) + " (최대 " + (max == null ? "없음" : max) + ", 최소 " + (min == null ? "없음" : min) + ")");
        for (ClickType type : clickTypeValue.keySet()) {
            Double value = clickTypeValue.get(type);
            lore.add("§f" + type.name() + (value < 0 ? value : " +" + value));
        }

        this.setItem(slot, new ItemBuilder(material).setDisplayName(properties.getName()).setLore(lore).build());

        this.setButton(slot, event -> {
            event.setCancelled(true);
            setting.setValue(properties, setting.getValue(properties) + clickTypeValue.get(event.getClick()));
            if (max != null && setting.getValue(properties) > max) setting.setValue(properties, max);
            if (min != null && setting.getValue(properties) < min) setting.setValue(properties, min);
        });
    }
}
