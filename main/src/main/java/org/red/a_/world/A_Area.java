package org.red.a_.world;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.util.BoundingBox;
import org.jetbrains.annotations.NotNull;
import org.red.CommediaDell_arte;
import org.red.a_.A_ManagerImpl;
import org.red.library.world.Area;
import org.red.library.world.rule.RuleMap;

import java.util.HashMap;
import java.util.Map;

public class A_Area extends Area implements ConfigurationSerializable {
    private final String name;
    private final A_ManagerImpl.A_Version aVersion;
    public A_Area(World world, BoundingBox boundingBox, String name, A_ManagerImpl.A_Version aVersion) {
        super(world, boundingBox, new NamespacedKey(CommediaDell_arte.getPlugin(), name));
        this.name = name;
        this.aVersion = aVersion;
    }

    public String getName() {
        return name;
    }

    @NotNull
    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();

        map.put("world", this.world.getName());
        map.put("box", this.boundingBox);
        map.put("name", this.key.getKey());
        map.put("ruleMap", this.ruleMap);

        Map<String, Object> potionEffectMapStr = new HashMap<>();
        this.potionEffectMap.forEach((potionEffectType, integer) -> potionEffectMapStr.put(potionEffectType.getName(), integer));
        map.put("potionEffectMap", potionEffectMapStr);
        map.put("rulePriority", this.priority.name());

        return map;
    }

    @NotNull
    public static A_Area deserialize(Map<String, Object> map) {
        A_Area area = CommediaDell_arte.getDell_arteManager().createAArea(Bukkit.getWorld((String) map.get("world")), (BoundingBox) map.get("box"), (String) map.get("name"));
        area.ruleMap.copy((RuleMap) map.get("ruleMap"));
        area.priority = RulePriority.valueOf((String) map.get("rulePriority"));
        Map<String, Object> potionEffectMapStr = (Map<String, Object>) map.get("potionEffectMap");
        potionEffectMapStr.forEach((s, o) -> area.potionEffectMap.put(org.bukkit.potion.PotionEffectType.getByName(s), (Integer) o));

        return area;
    }
}
