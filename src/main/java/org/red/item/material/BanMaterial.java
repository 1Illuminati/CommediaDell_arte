package org.red.item.material;

import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.jetbrains.annotations.NotNull;
import org.red.CommediaDell_arte;
import org.red.library.item.material.MaterialAct;

import java.util.*;

public class BanMaterial implements ConfigurationSerializable {
    private final Map<Material, BanMaterialInfo> map = new LinkedHashMap<>();
    public boolean isActAllowed(Material material, MaterialAct act) {
        BanMaterialInfo banMaterialInfo = map.get(material);
        if (banMaterialInfo == null) {
            return true;
        }
        return banMaterialInfo.isActAllowed(act);
    }

    public void setActAllowed(Material material, MaterialAct act, boolean allowed) {
        BanMaterialInfo banMaterialInfo = map.computeIfAbsent(material, BanMaterialInfo::new);
        banMaterialInfo.setActAllowed(act, allowed);
    }

    public void createNewInfo(Material material) {
        map.put(material, new BanMaterialInfo(material));
    }

    public List<BanMaterialInfo> getBanMaterialInfos() {
        return new ArrayList<>(map.values());
    }

    public void copy(BanMaterial banMaterial) {
        map.clear();
        map.putAll(banMaterial.map);
    }

    @NotNull
    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> result = new LinkedHashMap<>();
        Map<String, Object> resultActMap = new HashMap<>();
        map.values().forEach(banMaterialInfo -> {
            Arrays.stream(MaterialAct.values()).forEach(act -> resultActMap.put(act.name(), banMaterialInfo.isActAllowed(act)));
            result.put(banMaterialInfo.material.name(), resultActMap);
        });
        return result;
    }

    @NotNull
    public static BanMaterial deserialize(Map<String, Object> map) {
        BanMaterial banMaterial = new BanMaterial();
        map.remove("==");
        map.forEach((materialName, actMap) -> {
            BanMaterialInfo banMaterialInfo = new BanMaterialInfo(Material.valueOf(materialName));
            ((Map<String, Object>) actMap).forEach((actName, allowed) -> banMaterialInfo.setActAllowed(MaterialAct.valueOf(actName), (boolean) allowed));
            banMaterial.map.put(banMaterialInfo.material, banMaterialInfo);
        });
        return banMaterial;
    }

    public static class BanMaterialInfo {
        private final Material material;
        private final Map<MaterialAct, Boolean> map = new EnumMap<>(MaterialAct.class);
        public BanMaterialInfo(Material material) {
            this.material = material;
        }

        public Material getMaterial() {
            return material;
        }

        public boolean isActAllowed(MaterialAct act) {
            return map.getOrDefault(act, true);
        }

        public void setActAllowed(MaterialAct act, boolean allowed) {
            map.put(act, allowed);
        }
    }
}
