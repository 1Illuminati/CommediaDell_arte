package org.red.library.entity;

import org.bukkit.*;
import org.bukkit.block.BlockFace;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;
import org.red.library.CommediaDell_arte;
import org.red.library.entity.player.NewPlayer;
import org.red.library.util.SaveLoad;
import org.red.library.util.map.CoolTime;
import org.red.library.util.map.DataMap;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class NewEntity implements SaveLoad {
    private static final Map<UUID, NewEntity> newEntityMap = new HashMap<>();

    public static NewEntity getNewEntity(Entity entity) {
        if (entity instanceof HumanEntity) {
            return NewPlayer.getNewPlayer(entity.getUniqueId());
        }

        return newEntityMap.computeIfAbsent(entity.getUniqueId(), uuid -> {
            if (entity instanceof LivingEntity) return new NewLivingEntity((LivingEntity) entity);
            else return new NewEntity(entity);
        });
    }

    public static NewEntity getNewEntity(UUID entityUUID) {
        Entity entity = Bukkit.getEntity(entityUUID);

        if (entity == null)
            return null;

        return NewEntity.getNewEntity(entity);
    }

    private final Entity entity;
    private final DataMap dataMap;
    private final CoolTime coolTime;
    protected NewEntity(Entity entity) {
        this.entity = entity;
    }

    public DataMap getDataMap() {
        return dataMap;
    }

    public CoolTime getCoolTime() {
        return coolTime;
    }

    @Override
    public void load() {
        FileConfiguration fileConfiguration = new YamlConfiguration();
        fileConfiguration.set("dataMap", this.dataMap);
        fileConfiguration.set("coolTime", this.coolTime);


        File file = new File(this.path);

        try {
            fileConfiguration.save(file);
            CommediaDell_arte.sendLog("§aSave PlayerData: " + this.player.getUniqueId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save() {

    }

    public Entity getEntity() {
        return entity;
    }

    public Location getLocation() {
        return entity.getLocation();
    }

    public Location getLocation(Location location) {
        return entity.getLocation(location);
    }

    public void setVelocity(Vector vector) {
        entity.setVelocity(vector);
    }

    public Vector getVelocity() {
        return entity.getVelocity();
    }

    public double getHeight() {
        return entity.getHeight();
    }

    public double getWidth() {
        return entity.getWidth();
    }

    public BoundingBox getBoundingBox() {
        return entity.getBoundingBox();
    }

    public boolean isOnGround() {
        return entity.isOnGround();
    }

    public boolean isInWater() {
        return entity.isInWater();
    }

    public World getWorld() {
        return entity.getWorld();
    }

    public void setRotation(float v, float v1) {
        entity.setRotation(v, v1);
    }

    public boolean teleport(Location location) {
        return entity.teleport(location);
    }

    public boolean teleport(Location location, PlayerTeleportEvent.TeleportCause teleportCause) {
        return entity.teleport(location, teleportCause);
    }

    public boolean teleport(Entity entity) {
        return this.entity.teleport(entity);
    }

    public boolean teleport(Entity entity, PlayerTeleportEvent.TeleportCause teleportCause) {
        return this.entity.teleport(entity, teleportCause);
    }

    public List<Entity> getNearbyEntities(double v, double v1, double v2) {
        return entity.getNearbyEntities(v, v1, v2);
    }

    public int getEntityId() {
        return entity.getEntityId();
    }

    public int getFireTicks() {
        return entity.getFireTicks();
    }

    public int getMaxFireTicks() {
        return entity.getMaxFireTicks();
    }

    public void setFireTicks(int i) {
        entity.setFireTicks(i);
    }

    public void remove() {
        entity.remove();
    }

    public boolean isDead() {
        return entity.isDead();
    }

    public boolean isValid() {
        return entity.isValid();
    }

    public Server getServer() {
        return entity.getServer();
    }

    public boolean isPersistent() {
        return entity.isPersistent();
    }

    public void setPersistent(boolean b) {
        entity.setPersistent(b);
    }

    @Deprecated
    public Entity getPassenger() {
        return entity.getPassenger();
    }

    @Deprecated
    public boolean setPassenger(Entity entity) {
        return this.entity.setPassenger(entity);
    }

    public List<Entity> getPassengers() {
        return entity.getPassengers();
    }

    public boolean addPassenger(Entity entity) {
        return this.entity.addPassenger(entity);
    }

    public boolean removePassenger(Entity entity) {
        return this.entity.removePassenger(entity);
    }

    public boolean isEmpty() {
        return entity.isEmpty();
    }

    public boolean eject() {
        return entity.eject();
    }

    public float getFallDistance() {
        return entity.getFallDistance();
    }

    public void setFallDistance(float v) {
        entity.setFallDistance(v);
    }

    public void setLastDamageCause(EntityDamageEvent entityDamageEvent) {
        entity.setLastDamageCause(entityDamageEvent);
    }

    public EntityDamageEvent getLastDamageCause() {
        return entity.getLastDamageCause();
    }

    public UUID getUniqueId() {
        return entity.getUniqueId();
    }

    public int getTicksLived() {
        return entity.getTicksLived();
    }

    public void setTicksLived(int i) {
        entity.setTicksLived(i);
    }

    public void playEffect(EntityEffect entityEffect) {
        entity.playEffect(entityEffect);
    }

    public EntityType getType() {
        return entity.getType();
    }

    public boolean isInsideVehicle() {
        return entity.isInsideVehicle();
    }

    public boolean leaveVehicle() {
        return entity.leaveVehicle();
    }

    public Entity getVehicle() {
        return entity.getVehicle();
    }

    public void setCustomNameVisible(boolean b) {
        entity.setCustomNameVisible(b);
    }

    public boolean isCustomNameVisible() {
        return entity.isCustomNameVisible();
    }

    public void setGlowing(boolean b) {
        entity.setGlowing(b);
    }

    public boolean isGlowing() {
        return entity.isGlowing();
    }

    public void setInvulnerable(boolean b) {
        entity.setInvulnerable(b);
    }

    public boolean isInvulnerable() {
        return entity.isInvulnerable();
    }

    public boolean isSilent() {
        return entity.isSilent();
    }

    public void setSilent(boolean b) {
        entity.setSilent(b);
    }

    public boolean hasGravity() {
        return entity.hasGravity();
    }

    public void setGravity(boolean b) {
        entity.setGravity(b);
    }

    public int getPortalCooldown() {
        return entity.getPortalCooldown();
    }

    public void setPortalCooldown(int i) {
        entity.setPortalCooldown(i);
    }

    public Set<String> getScoreboardTags() {
        return entity.getScoreboardTags();
    }

    public boolean addScoreboardTag(String s) {
        return entity.addScoreboardTag(s);
    }

    public boolean removeScoreboardTag(String s) {
        return entity.removeScoreboardTag(s);
    }

    public PistonMoveReaction getPistonMoveReaction() {
        return entity.getPistonMoveReaction();
    }

    public BlockFace getFacing() {
        return entity.getFacing();
    }

    public Pose getPose() {
        return entity.getPose();
    }

    public Entity.Spigot spigot() {
        return entity.spigot();
    }

    public void setMetadata(String s, MetadataValue metadataValue) {
        entity.setMetadata(s, metadataValue);
    }

    public List<MetadataValue> getMetadata(String s) {
        return entity.getMetadata(s);
    }

    public boolean hasMetadata(String s) {
        return entity.hasMetadata(s);
    }

    public void removeMetadata(String s, Plugin plugin) {
        entity.removeMetadata(s, plugin);
    }

    public void sendMessage(String s) {
        entity.sendMessage(s);
    }

    public void sendMessage(String[] strings) {
        entity.sendMessage(strings);
    }

    public void sendMessage(UUID uuid, String s) {
        entity.sendMessage(uuid, s);
    }

    public void sendMessage(UUID uuid, String[] strings) {
        entity.sendMessage(uuid, strings);
    }

    public String getName() {
        return entity.getName();
    }

    public boolean isPermissionSet(String s) {
        return entity.isPermissionSet(s);
    }

    public boolean isPermissionSet(Permission permission) {
        return entity.isPermissionSet(permission);
    }

    public boolean hasPermission(String s) {
        return entity.hasPermission(s);
    }

    public boolean hasPermission(Permission permission) {
        return entity.hasPermission(permission);
    }

    public PermissionAttachment addAttachment(Plugin plugin, String s, boolean b) {
        return entity.addAttachment(plugin, s, b);
    }

    public PermissionAttachment addAttachment(Plugin plugin) {
        return entity.addAttachment(plugin);
    }

    public PermissionAttachment addAttachment(Plugin plugin, String s, boolean b, int i) {
        return entity.addAttachment(plugin, s, b, i);
    }

    public PermissionAttachment addAttachment(Plugin plugin, int i) {
        return entity.addAttachment(plugin, i);
    }

    public void removeAttachment(PermissionAttachment permissionAttachment) {
        entity.removeAttachment(permissionAttachment);
    }

    public void recalculatePermissions() {
        entity.recalculatePermissions();
    }

    public Set<PermissionAttachmentInfo> getEffectivePermissions() {
        return entity.getEffectivePermissions();
    }

    public boolean isOp() {
        return entity.isOp();
    }

    public void setOp(boolean b) {
        entity.setOp(b);
    }

    public String getCustomName() {
        return entity.getCustomName();
    }

    public void setCustomName(String s) {
        entity.setCustomName(s);
    }

    public PersistentDataContainer getPersistentDataContainer() {
        return entity.getPersistentDataContainer();
    }
}
