package org.red.library.world;

import org.bukkit.Keyed;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.red.library.world.rule.HasRule;
import org.red.library.world.rule.Rule;
import org.red.library.world.rule.RuleMap;

import java.util.*;

public class Area implements HasRule, Keyed {
    private final World world;
    private final BoundingBox boundingBox;
    private final NamespacedKey key;
    private final RuleMap ruleMap = new RuleMap();
    private final Map<PotionEffectType, Integer> potionEffectMap = new HashMap<>();
    private RulePriority priority = RulePriority.NORMAL;

    public Area(World world, BoundingBox boundingBox, NamespacedKey key) {
        this.world = world;
        this.boundingBox = boundingBox;
        this.key = key;
    }
    public BoundingBox getBoundingBox() {
        return boundingBox;
    }
    public World getWorld() {
        return world;
    }
    @NotNull
    public NamespacedKey getKey() {
        return key;
    }

    public boolean contain(Vector vec) {
        return boundingBox.contains(vec);
    }

    /**
     * area안에 loc가 포함되어있는지 확인
     * @param loc 확인할 위치
     * @return 포함되어있으면 true, 아니면 false
     */
    public boolean contain(Location loc) {
        return this.contain(loc.toVector());
    }

    /**
     * area안에 boundingBox가 포함되어있는지 확인
     * @param boundingBox 확인할 위치
     * @return 포함되어있으면 true, 아니면 false
     */
    public boolean contain(BoundingBox boundingBox) {
        return this.boundingBox.contains(boundingBox);
    }

    /**
     * area안에 interfaceArea가 포함되어있는지 확인
     * @param area 확인할 위치
     * @return 포함되어있으면 true, 아니면 false
     */
    public boolean contain(Area area) {
        return boundingBox.contains(area.boundingBox);
    }

    public boolean contain(Vector start, Vector end) {
        return boundingBox.contains(start, end);
    }

    public boolean contain(Location start, Location end) {
        return boundingBox.contains(start.toVector(), end.toVector());
    }

    /**
     * area안에 interfaceArea가 겹치는 구역이 있는지 확인
     * @param area 확인할 위치
     * @return 겹치는 공간이 존재하면 true, 아니면 false
     */
    public boolean overlap(Area area) {
        return boundingBox.overlaps(area.boundingBox);
    }

    /**
     * area안에 boundingBox가 겹치는 구역이 있는지 확인
     * @param boundingBox 확인할 위치
     * @return 겹치는 공간이 존재하면 true, 아니면 false
     */
    public boolean overlap(BoundingBox boundingBox) {
        return this.boundingBox.overlaps(boundingBox);
    }

    public boolean overlap(Vector start, Vector end) {
        return boundingBox.overlaps(start, end);
    }

    public boolean overlap(Location start, Location end) {
        return boundingBox.overlaps(start.toVector(), end.toVector());
    }

    public void setPotionEffectInArea(PotionEffectType type, int level) {
        potionEffectMap.put(type, level);
    }

    public void removePotionEffectInArea(PotionEffectType type) {
        potionEffectMap.remove(type);
    }

    public Map<PotionEffectType, Integer> getPotionEffectMap() {
        return potionEffectMap;
    }

    public Collection<Entity> getEntities() {
        return world.getNearbyEntities(this.boundingBox);
    }

    @Override
    public <T> T getRuleValue(Rule<T> rule) {
        return ruleMap.get(rule);
    }

    @Override
    public <T> void setRuleValue(Rule<T> rule, T value) {
        ruleMap.set(rule, value);
    }

    public void setRulePriority(RulePriority priority) {
        this.priority = priority;
    }

    public RulePriority getRulePriority() {
        return this.priority;
    }

    public enum RulePriority {
        /**
         * 솔직히 마크 EventPriority 랑 똑같은데 왜 만들었는지 모르겠음
         * 일단 서로 겹칠경우 우선순위 정해줄려고 만듬 ㅋㅋ
         * 만약 우선순위도 같으면 등록된 순서대로 일듯?
         */
        HIGHEST(5),
        HIGH(4),
        NORMAL(3),
        LOW(2),
        LOWEST(1);

        private final int num;
        RulePriority(int num) {
            this.num = num;
        }

        public int getNum() {
            return num;
        }
    }
}
