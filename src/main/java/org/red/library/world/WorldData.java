package org.red.library.world;

import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.util.BoundingBox;
import org.red.library.CommediaDell_arte;
import org.red.library.util.ConfigData;
import org.red.library.util.map.CoolTime;
import org.red.library.util.map.DataMap;
import org.red.library.util.map.NameSpaceMap;
import org.red.library.world.rule.HasRule;
import org.red.library.world.rule.Rule;
import org.red.library.world.rule.RuleMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class WorldData implements ConfigData, HasRule {
    private static final Map<World, WorldData> worldDataMap = new HashMap<>();

    public static WorldData getWorldData(World world) {
        return worldDataMap.computeIfAbsent(world, WorldData::new);
    }

    private final World world;
    private final String path;
    private final DataMap dataMap = new DataMap();
    private final CoolTime coolTime = new CoolTime();
    private final NameSpaceMap<Area> areaMap = new NameSpaceMap<>();
    private final RuleMap ruleMap = new RuleMap();
    private WorldData(World world) {
        this.world = world;
        this.path = "plugins/Dell_arte/worldData/" + world.getName() + ".yml";
    }

    public World getWorld() {
        return world;
    }

    public DataMap getDataMap() {
        return dataMap;
    }

    public CoolTime getCoolTime() {
        return coolTime;
    }

    public Area getArea(NamespacedKey key) {
        return areaMap.get(key);
    }

    public void putArea(Area area) {
        areaMap.put(area.getKey(), area);
    }

    public void removeArea(NamespacedKey key) {
        areaMap.remove(key);
    }

    public boolean hasArea(NamespacedKey key) {
        return areaMap.containsKey(key);
    }

    public List<Area> getAreas() {
        return new ArrayList<>(areaMap.values());
    }

    public void clearAreas() {
        areaMap.clear();
    }

    public List<Area> getContainArea(Location loc) {
        List<Area> areas = new ArrayList<>();
        for (Area area : areaMap.values()) {
            if (area.contain(loc)) {
                areas.add(area);
            }
        }
        return areas;
    }

    public List<Area> getContainArea(BoundingBox box) {
        List<Area> areas = new ArrayList<>();
        for (Area area : areaMap.values()) {
            if (area.contain(box)) {
                areas.add(area);
            }
        }
        return areas;
    }

    public List<Area> getOverlapArea(BoundingBox box) {
        List<Area> areas = new ArrayList<>();
        for (Area area : areaMap.values()) {
            if (area.overlap(box)) {
                areas.add(area);
            }
        }
        return areas;
    }

    @Override
    public void load() {
        FileConfiguration fileConfiguration = new YamlConfiguration();
        File file = new File(this.path);

        try {
            fileConfiguration.load(file);
            CommediaDell_arte.sendLog("§aLoad WorldData: " + this.world.getName());
        }  catch (IOException | InvalidConfigurationException e) {
            if (e instanceof FileNotFoundException) CommediaDell_arte.sendLog("§cNot Found WorldData: " + this.world.getName());
            else e.printStackTrace();

            return;
        }

        DataMap dataMap = (DataMap) fileConfiguration.get("dataMap");
        if (dataMap != null) this.dataMap.copy(dataMap);

        CoolTime coolTime = (CoolTime) fileConfiguration.get("coolTime");
        if (coolTime != null) this.coolTime.copy(coolTime);

        RuleMap ruleMap = (RuleMap) fileConfiguration.get("ruleMap");
        if (ruleMap != null) this.ruleMap.copy(ruleMap);
    }

    @Override
    public void save() {
        FileConfiguration fileConfiguration = new YamlConfiguration();
        fileConfiguration.set("dataMap", this.dataMap);
        fileConfiguration.set("coolTime", this.coolTime);
        fileConfiguration.set("ruleMap", this.ruleMap);


        File file = new File(this.path);

        try {
            fileConfiguration.save(file);
            CommediaDell_arte.sendLog("§aSave WorldData: " + this.world.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public <T> T getRuleValue(Rule<T> rule, Location location) {
        T result = ruleMap.get(rule);
        int num = 6;
        for (Area area : getContainArea(location)) {
            int areaNum = area.getRulePriority().getNum();

            if (areaNum < num) {
                num = areaNum;
                result = area.getRuleValue(rule);
            }
        }

        return result;
    }

    public <T> T getRuleValue(Rule<T> rule, List<Location> locations) {
        T result = ruleMap.get(rule);
        int num = 6;
        List<Area> areas = new ArrayList<>();
        locations.forEach(loc -> areas.addAll(getContainArea(loc)));
        for (Area area : new HashSet<>(areas)) {
            int areaNum = area.getRulePriority().getNum();

            if (areaNum < num) {
                num = areaNum;
                result = area.getRuleValue(rule);
            }
        }

        return result;
    }

    @Override
    public <T> T getRuleValue(Rule<T> rule) {
        return ruleMap.get(rule);
    }

    @Override
    public <T> void setRuleValue(Rule<T> rule, T value) {
        ruleMap.set(rule, value);
    }
}
