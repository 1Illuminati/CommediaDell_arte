package org.red.a_.world;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.util.BoundingBox;
import org.jetbrains.annotations.NotNull;
import org.red.CommediaDell_arte;
import org.red.library.A_;
import org.red.library.a_.world.A_World;
import org.red.library.util.map.DataMap;
import org.red.library.world.Area;
import org.red.library.world.rule.RuleMap;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class A_Area extends Area implements ConfigurationSerializable {
    private static final Map<String, A_Area> areaMap = new HashMap<>();
    public static void registerArea(A_Area area) {
        areaMap.put(area.getName(), area);
        area.getAWorld().putArea(area);
    }

    public static void removeArea(A_Area area) {
        areaMap.remove(area.getName());
        area.getAWorld().removeArea(area);
    }

    public static A_Area getArea(String key) {
        return areaMap.getOrDefault(key, null);
    }

    public static Collection<A_Area> getAreas() {
        return areaMap.values();
    }

    public static void saveArea() {
        areaMap.forEach((s, a_area) -> {
            A_World a_world = a_area.getAWorld();
            DataMap dataMap = a_world.getDataMap().getDataMap("A_Area");
            DataMap areaDataMap = dataMap.getDataMap(a_area.getName());
            areaDataMap.put("area", a_area);
        });
    }

    public static void loadArea() {
        Bukkit.getWorlds().forEach(world -> {
            A_World a_world = A_.getAWorld(world);
            DataMap dataMap = a_world.getDataMap().getDataMap("A_Area");

            dataMap.keySet().forEach(s -> {
                A_Area area = dataMap.getClass("area", A_Area.class);
                if (area != null) {
                    registerArea(area);
                }
            });
        });
    }

    private final String name;
    public A_Area(World world, BoundingBox boundingBox, String name) {
        super(world, boundingBox, new NamespacedKey(CommediaDell_arte.getPlugin(), name));
        this.name = name;
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
        A_Area area = new A_Area(
                Bukkit.getWorld((String) map.get("world")),
                (BoundingBox) map.get("box"),
                (String) map.get("name")
        );

        area.ruleMap.copy((RuleMap) map.get("ruleMap"));
        area.priority = Area.RulePriority.valueOf((String) map.get("rulePriority"));
        Map<String, Object> potionEffectMapStr = (Map<String, Object>) map.get("potionEffectMap");
        potionEffectMapStr.forEach((s, o) -> area.potionEffectMap.put(org.bukkit.potion.PotionEffectType.getByName(s), (Integer) o));

        return area;
    }
}
