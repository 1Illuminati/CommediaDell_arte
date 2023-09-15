package org.red.a_.entity;

import org.bukkit.*;
import org.bukkit.block.BlockFace;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Pose;
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
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.red.a_.A_Data;
import org.red.a_.A_Manager;
import org.red.library.entity.a_.A_Entity;
import org.red.library.entity.a_.A_LivingEntity;

import java.util.*;

public class A_EntityImpl implements A_Entity {
    private final Entity entity;
    private final A_Data aData;
    private final A_Manager.A_Version version;

    public A_EntityImpl(Entity entity, A_Data aData, A_Manager.A_Version version) {
        this.entity = entity;
        this.aData = aData;
        this.version = version;
    }

    @Override
    public A_Data getAData() {
        return this.aData;
    }

    @Override
    @NotNull
    public Location getLocation() {
        return entity.getLocation();
    }

    @Override
    @Nullable
    @Contract("null -> null; !null -> !null")
    public Location getLocation(@Nullable Location location) {
        return entity.getLocation(location);
    }

    @Override
    public void setVelocity(@NotNull Vector vector) {
        entity.setVelocity(vector);
    }

    @Override
    @NotNull
    public Vector getVelocity() {
        return entity.getVelocity();
    }

    @Override
    public double getHeight() {
        return entity.getHeight();
    }

    @Override
    public double getWidth() {
        return entity.getWidth();
    }

    @Override
    @NotNull
    public BoundingBox getBoundingBox() {
        return entity.getBoundingBox();
    }

    @Override
    public boolean isOnGround() {
        return entity.isOnGround();
    }

    @Override
    public boolean isInWater() {
        return entity.isInWater();
    }

    @Override
    @NotNull
    public World getWorld() {
        return entity.getWorld();
    }

    @Override
    public void setRotation(float v, float v1) {
        entity.setRotation(v, v1);
    }

    @Override
    public boolean teleport(@NotNull Location location) {
        return entity.teleport(location);
    }

    @Override
    public boolean teleport(@NotNull Location location, @NotNull PlayerTeleportEvent.TeleportCause teleportCause) {
        return entity.teleport(location, teleportCause);
    }

    @Override
    public boolean teleport(@NotNull Entity entity) {
        return this.entity.teleport(entity);
    }

    @Override
    public boolean teleport(@NotNull Entity entity, @NotNull PlayerTeleportEvent.TeleportCause teleportCause) {
        return this.entity.teleport(entity, teleportCause);
    }

    @Override
    @NotNull
    public List<Entity> getNearbyEntities(double v, double v1, double v2) {
        return entity.getNearbyEntities(v, v1, v2);
    }

    @Override
    public int getEntityId() {
        return entity.getEntityId();
    }

    @Override
    public int getFireTicks() {
        return entity.getFireTicks();
    }

    @Override
    public int getMaxFireTicks() {
        return entity.getMaxFireTicks();
    }

    @Override
    public void setFireTicks(int i) {
        entity.setFireTicks(i);
    }

    @Override
    public void remove() {
        entity.remove();
    }

    @Override
    public boolean isDead() {
        return entity.isDead();
    }

    @Override
    public boolean isValid() {
        return entity.isValid();
    }

    @Override
    @NotNull
    public Server getServer() {
        return entity.getServer();
    }

    @Override
    public boolean isPersistent() {
        return entity.isPersistent();
    }

    @Override
    public void setPersistent(boolean b) {
        entity.setPersistent(b);
    }

    @Override
    @Nullable
    public Entity getPassenger() {
        return entity.getPassenger();
    }

    @Override
    public boolean setPassenger(@NotNull Entity entity) {
        return this.entity.setPassenger(entity);
    }

    @Override
    @NotNull
    public List<Entity> getPassengers() {
        return entity.getPassengers();
    }

    @Override
    public boolean addPassenger(@NotNull Entity entity) {
        return this.entity.addPassenger(entity);
    }

    @Override
    public boolean removePassenger(@NotNull Entity entity) {
        return this.entity.removePassenger(entity);
    }

    @Override
    public boolean isEmpty() {
        return entity.isEmpty();
    }

    @Override
    public boolean eject() {
        return entity.eject();
    }

    @Override
    public float getFallDistance() {
        return entity.getFallDistance();
    }

    @Override
    public void setFallDistance(float v) {
        entity.setFallDistance(v);
    }

    @Override
    public void setLastDamageCause(@Nullable EntityDamageEvent entityDamageEvent) {
        entity.setLastDamageCause(entityDamageEvent);
    }

    @Override
    @Nullable
    public EntityDamageEvent getLastDamageCause() {
        return entity.getLastDamageCause();
    }

    @Override
    @NotNull
    public UUID getUniqueId() {
        return entity.getUniqueId();
    }

    @Override
    public int getTicksLived() {
        return entity.getTicksLived();
    }

    @Override
    public void setTicksLived(int i) {
        entity.setTicksLived(i);
    }

    @Override
    public void playEffect(@NotNull EntityEffect entityEffect) {
        entity.playEffect(entityEffect);
    }

    @Override
    @NotNull
    public EntityType getType() {
        return entity.getType();
    }

    @Override
    public boolean isInsideVehicle() {
        return entity.isInsideVehicle();
    }

    @Override
    public boolean leaveVehicle() {
        return entity.leaveVehicle();
    }

    @Override
    @Nullable
    public Entity getVehicle() {
        return entity.getVehicle();
    }

    @Override
    public void setCustomNameVisible(boolean b) {
        entity.setCustomNameVisible(b);
    }

    @Override
    public boolean isCustomNameVisible() {
        return entity.isCustomNameVisible();
    }

    @Override
    public void setGlowing(boolean b) {
        entity.setGlowing(b);
    }

    @Override
    public boolean isGlowing() {
        return entity.isGlowing();
    }

    @Override
    public void setInvulnerable(boolean b) {
        entity.setInvulnerable(b);
    }

    @Override
    public boolean isInvulnerable() {
        return entity.isInvulnerable();
    }

    @Override
    public boolean isSilent() {
        return entity.isSilent();
    }

    @Override
    public void setSilent(boolean b) {
        entity.setSilent(b);
    }

    @Override
    public boolean hasGravity() {
        return entity.hasGravity();
    }

    @Override
    public void setGravity(boolean b) {
        entity.setGravity(b);
    }

    @Override
    public int getPortalCooldown() {
        return entity.getPortalCooldown();
    }

    @Override
    public void setPortalCooldown(int i) {
        entity.setPortalCooldown(i);
    }

    @Override
    @NotNull
    public Set<String> getScoreboardTags() {
        return entity.getScoreboardTags();
    }

    @Override
    public boolean addScoreboardTag(@NotNull String s) {
        return entity.addScoreboardTag(s);
    }

    @Override
    public boolean removeScoreboardTag(@NotNull String s) {
        return entity.removeScoreboardTag(s);
    }

    @Override
    @NotNull
    public PistonMoveReaction getPistonMoveReaction() {
        return entity.getPistonMoveReaction();
    }

    @Override
    @NotNull
    public BlockFace getFacing() {
        return entity.getFacing();
    }

    @Override
    @NotNull
    public Pose getPose() {
        return entity.getPose();
    }

    @Override
    @NotNull
    public Entity.Spigot spigot() {
        return entity.spigot();
    }

    @Override
    public Entity getEntity() {
        return null;
    }

    @Override
    public void setMetadata(@NotNull String s, @NotNull MetadataValue metadataValue) {
        entity.setMetadata(s, metadataValue);
    }

    @Override
    @NotNull
    public List<MetadataValue> getMetadata(@NotNull String s) {
        return entity.getMetadata(s);
    }

    @Override
    public boolean hasMetadata(@NotNull String s) {
        return entity.hasMetadata(s);
    }

    @Override
    public void removeMetadata(@NotNull String s, @NotNull Plugin plugin) {
        entity.removeMetadata(s, plugin);
    }

    @Override
    public void sendMessage(@NotNull String s) {
        entity.sendMessage(s);
    }

    @Override
    public void sendMessage(@NotNull String[] strings) {
        entity.sendMessage(strings);
    }

    @Override
    public void sendMessage(@Nullable UUID uuid, @NotNull String s) {
        entity.sendMessage(uuid, s);
    }

    @Override
    public void sendMessage(@Nullable UUID uuid, @NotNull String[] strings) {
        entity.sendMessage(uuid, strings);
    }

    @Override
    @NotNull
    public String getName() {
        return entity.getName();
    }

    @Override
    public boolean isPermissionSet(@NotNull String s) {
        return entity.isPermissionSet(s);
    }

    @Override
    public boolean isPermissionSet(@NotNull Permission permission) {
        return entity.isPermissionSet(permission);
    }

    @Override
    public boolean hasPermission(@NotNull String s) {
        return entity.hasPermission(s);
    }

    @Override
    public boolean hasPermission(@NotNull Permission permission) {
        return entity.hasPermission(permission);
    }

    @Override
    @NotNull
    public PermissionAttachment addAttachment(@NotNull Plugin plugin, @NotNull String s, boolean b) {
        return entity.addAttachment(plugin, s, b);
    }

    @Override
    @NotNull
    public PermissionAttachment addAttachment(@NotNull Plugin plugin) {
        return entity.addAttachment(plugin);
    }

    @Override
    @Nullable
    public PermissionAttachment addAttachment(@NotNull Plugin plugin, @NotNull String s, boolean b, int i) {
        return entity.addAttachment(plugin, s, b, i);
    }

    @Override
    @Nullable
    public PermissionAttachment addAttachment(@NotNull Plugin plugin, int i) {
        return entity.addAttachment(plugin, i);
    }

    @Override
    public void removeAttachment(@NotNull PermissionAttachment permissionAttachment) {
        entity.removeAttachment(permissionAttachment);
    }

    @Override
    public void recalculatePermissions() {
        entity.recalculatePermissions();
    }

    @Override
    @NotNull
    public Set<PermissionAttachmentInfo> getEffectivePermissions() {
        return entity.getEffectivePermissions();
    }

    @Override
    public boolean isOp() {
        return entity.isOp();
    }

    @Override
    public void setOp(boolean b) {
        entity.setOp(b);
    }

    @Override
    public LivingEntity getLivingEntity() {
        if (this.entity instanceof LivingEntity)
            return (LivingEntity) this.entity;

        throw new IllegalStateException("Entity is not a living entity");
    }

    @Override
    public A_LivingEntity getALivingEntity() {
        if (this.entity instanceof LivingEntity)
            return (A_LivingEntity) this;

        throw new IllegalStateException("Entity is not a living entity");
    }

    @Override
    @Nullable
    public String getCustomName() {
        return entity.getCustomName();
    }

    @Override
    public void setCustomName(@Nullable String s) {
        entity.setCustomName(s);
    }

    @Override
    @NotNull
    public PersistentDataContainer getPersistentDataContainer() {
        return entity.getPersistentDataContainer();
    }
}
