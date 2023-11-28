package org.red.nms.v1_16_R3;

import net.minecraft.server.v1_16_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_16_R3.PacketPlayOutSpawnEntity;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.entity.player.EntityHideManager;

import java.util.List;

public class EntityHideManagerImpl implements EntityHideManager {
    private final List<String> hiddenEntities;
    private final CraftPlayer player;
    public EntityHideManagerImpl(A_Player player) {
        hiddenEntities = player.getDataMap().getList("hiddenEntities");
        this.player = (CraftPlayer) player.getPlayer();
    }
    @Override
    public void hide(Entity entity) {
        if (isHidden(entity)) return;
        player.getHandle().playerConnection.sendPacket(new PacketPlayOutEntityDestroy(entity.getEntityId()));
        hiddenEntities.add(entity.getUniqueId().toString());
    }

    @Override
    public boolean isHidden(Entity entity) {
        return hiddenEntities.contains(entity.getUniqueId().toString());
    }

    @Override
    public void show(Entity entity) {
        if (!isHidden(entity)) return;
        player.getHandle().playerConnection.sendPacket(new PacketPlayOutSpawnEntity(((CraftEntity) entity).getHandle()));
        hiddenEntities.remove(entity.getUniqueId().toString());
    }
}
