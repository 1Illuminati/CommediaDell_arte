package org.red.library.entity.a_;

import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
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
import org.red.library.a_.A_DataHolder;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface A_Entity extends A_DataHolder {
    Entity getEntity();

    void setMetadata(@NotNull String var1, @NotNull MetadataValue var2);

    @NotNull
    List<MetadataValue> getMetadata(@NotNull String var1);

    boolean hasMetadata(@NotNull String var1);

    void removeMetadata(@NotNull String var1, @NotNull Plugin var2);

    void sendMessage(@NotNull String var1);

    void sendMessage(@NotNull String[] var1);

    void sendMessage(@Nullable UUID var1, @NotNull String var2);

    void sendMessage(@Nullable UUID var1, @NotNull String[] var2);

    @NotNull
    String getName();

    @NotNull
    PersistentDataContainer getPersistentDataContainer();

    @Nullable
    String getCustomName();

    void setCustomName(@Nullable String var1);

    @NotNull
    Location getLocation();

    @Contract("null -> null; !null -> !null")
    @Nullable
    Location getLocation(@Nullable Location var1);

    void setVelocity(@NotNull Vector var1);

    @NotNull
    Vector getVelocity();

    double getHeight();

    double getWidth();

    @NotNull
    BoundingBox getBoundingBox();

    boolean isOnGround();

    boolean isInWater();

    @NotNull
    World getWorld();

    void setRotation(float var1, float var2);

    boolean teleport(@NotNull Location var1);

    boolean teleport(@NotNull Location var1, @NotNull PlayerTeleportEvent.TeleportCause var2);

    boolean teleport(@NotNull Entity var1);

    boolean teleport(@NotNull Entity var1, @NotNull PlayerTeleportEvent.TeleportCause var2);

    @NotNull
    List<Entity> getNearbyEntities(double var1, double var3, double var5);

    int getEntityId();

    int getFireTicks();

    int getMaxFireTicks();

    void setFireTicks(int var1);

    void remove();

    boolean isDead();

    boolean isValid();

    @NotNull
    Server getServer();

    boolean isPersistent();

    void setPersistent(boolean var1);

    @Nullable
    Entity getPassenger();

    boolean setPassenger(@NotNull Entity var1);

    @NotNull
    List<Entity> getPassengers();

    boolean addPassenger(@NotNull Entity var1);

    boolean removePassenger(@NotNull Entity var1);

    boolean isEmpty();

    boolean eject();

    float getFallDistance();

    void setFallDistance(float var1);

    void setLastDamageCause(@Nullable EntityDamageEvent var1);

    @Nullable
    EntityDamageEvent getLastDamageCause();

    @NotNull
    UUID getUniqueId();

    int getTicksLived();

    void setTicksLived(int var1);

    void playEffect(@NotNull EntityEffect var1);

    @NotNull
    EntityType getType();

    boolean isInsideVehicle();

    boolean leaveVehicle();

    @Nullable
    Entity getVehicle();

    void setCustomNameVisible(boolean var1);

    boolean isCustomNameVisible();

    void setGlowing(boolean var1);

    boolean isGlowing();

    void setInvulnerable(boolean var1);

    boolean isInvulnerable();

    boolean isSilent();

    void setSilent(boolean var1);

    boolean hasGravity();

    void setGravity(boolean var1);

    int getPortalCooldown();

    void setPortalCooldown(int var1);

    @NotNull
    Set<String> getScoreboardTags();

    boolean addScoreboardTag(@NotNull String var1);

    boolean removeScoreboardTag(@NotNull String var1);

    @NotNull
    PistonMoveReaction getPistonMoveReaction();

    @NotNull
    BlockFace getFacing();

    @NotNull
    Pose getPose();

    @NotNull
    Entity.Spigot spigot();

    boolean isPermissionSet(@NotNull String var1);

    boolean isPermissionSet(@NotNull Permission var1);

    boolean hasPermission(@NotNull String var1);

    boolean hasPermission(@NotNull Permission var1);

    @NotNull
    PermissionAttachment addAttachment(@NotNull Plugin var1, @NotNull String var2, boolean var3);

    @NotNull
    PermissionAttachment addAttachment(@NotNull Plugin var1);

    @Nullable
    PermissionAttachment addAttachment(@NotNull Plugin var1, @NotNull String var2, boolean var3, int var4);

    @Nullable
    PermissionAttachment addAttachment(@NotNull Plugin var1, int var2);

    void removeAttachment(@NotNull PermissionAttachment var1);

    void recalculatePermissions();

    @NotNull
    Set<PermissionAttachmentInfo> getEffectivePermissions();

    boolean isOp();

    void setOp(boolean var1);

    LivingEntity getLivingEntity();

    A_LivingEntity getALivingEntity();
}
