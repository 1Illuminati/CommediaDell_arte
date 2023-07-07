package org.red.library.world;

import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.util.BoundingBox;
import org.red.library.world.rule.HasRule;

public interface Area extends HasRule {
    BoundingBox getBoundingBox();
    World getWorld();
    NamespacedKey getKey();

    /**
     * area안에 loc가 포함되어있는지 확인
     * @param loc 확인할 위치
     * @return 포함되어있으면 true, 아니면 false
     */
    boolean contain(Location loc);

    /**
     * area안에 boundingBox가 포함되어있는지 확인
     * @param boundingBox 확인할 위치
     * @return 포함되어있으면 true, 아니면 false
     */
    boolean contain(BoundingBox boundingBox);
    /**
     * area안에 interfaceArea가 포함되어있는지 확인
     * @param interfaceArea 확인할 위치
     * @return 포함되어있으면 true, 아니면 false
     */
    boolean contain(Area interfaceArea);

    /**
     * area안에 interfaceArea가 겹치는 구역이 있는지 확인
     * @param interfaceArea 확인할 위치
     * @return 겹치는 공간이 존재하면 true, 아니면 false
     */
    boolean overlap(Area interfaceArea);

    /**
     * area안에 boundingBox가 겹치는 구역이 있는지 확인
     * @param boundingBox 확인할 위치
     * @return 겹치는 공간이 존재하면 true, 아니면 false
     */
    boolean overlap(BoundingBox boundingBox);

    default String asString() {
        return String.format("Area{world=%s, key=%s, boundingBox=%s}", getWorld().getName(), getKey().toString(), getBoundingBox().toString());
    }
}
