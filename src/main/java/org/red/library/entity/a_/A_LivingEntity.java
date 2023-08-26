package org.red.library.entity.a_;

import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.entity.memory.MemoryKey;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface A_LivingEntity extends A_Entity {
    LivingEntity getEntity();

    @Nullable
    AttributeInstance getAttribute(@NotNull Attribute var1);

    @NotNull
    <T extends Projectile> T launchProjectile(@NotNull Class<? extends T> var1);

    @NotNull
    <T extends Projectile> T launchProjectile(@NotNull Class<? extends T> var1, @Nullable Vector var2);

    void damage(double var1);

    void damage(double var1, @Nullable Entity var3);

    double getHealth();

    void setHealth(double var1);

    double getAbsorptionAmount();

    void setAbsorptionAmount(double var1);

    double getMaxHealth();

    void setMaxHealth(double var1);

    void resetMaxHealth();

    double getEyeHeight();

    double getEyeHeight(boolean var1);

    @NotNull
    Location getEyeLocation();

    @NotNull
    List<Block> getLineOfSight(@Nullable Set<Material> var1, int var2);

    @NotNull
    Block getTargetBlock(@Nullable Set<Material> var1, int var2);

    @NotNull
    List<Block> getLastTwoTargetBlocks(@Nullable Set<Material> var1, int var2);

    @Nullable
    Block getTargetBlockExact(int var1);

    @Nullable
    Block getTargetBlockExact(int var1, @NotNull FluidCollisionMode var2);

    @Nullable
    RayTraceResult rayTraceBlocks(double var1);

    @Nullable
    RayTraceResult rayTraceBlocks(double var1, @NotNull FluidCollisionMode var3);

    int getRemainingAir();

    void setRemainingAir(int var1);

    int getMaximumAir();

    void setMaximumAir(int var1);

    int getArrowCooldown();

    void setArrowCooldown(int var1);

    int getArrowsInBody();

    void setArrowsInBody(int var1);

    int getMaximumNoDamageTicks();

    void setMaximumNoDamageTicks(int var1);

    double getLastDamage();

    void setLastDamage(double var1);

    int getNoDamageTicks();

    void setNoDamageTicks(int var1);

    @Nullable
    Player getKiller();

    boolean addPotionEffect(@NotNull PotionEffect var1);

    boolean addPotionEffect(@NotNull PotionEffect var1, boolean var2);

    boolean addPotionEffect(@NotNull PotionEffectType var1, int duration, int amplifier);

    boolean addPotionEffects(@NotNull Collection<PotionEffect> var1);

    boolean hasPotionEffect(@NotNull PotionEffectType var1);

    @Nullable
    PotionEffect getPotionEffect(@NotNull PotionEffectType var1);

    void removePotionEffect(@NotNull PotionEffectType var1);

    @NotNull
    Collection<PotionEffect> getActivePotionEffects();

    boolean hasLineOfSight(@NotNull Entity var1);

    boolean getRemoveWhenFarAway();

    void setRemoveWhenFarAway(boolean var1);

    @Nullable
    EntityEquipment getEquipment();

    void setCanPickupItems(boolean var1);

    boolean getCanPickupItems();

    boolean isLeashed();

    @NotNull
    Entity getLeashHolder() throws IllegalStateException;

    boolean setLeashHolder(@Nullable Entity var1);

    boolean isGliding();

    void setGliding(boolean var1);

    boolean isSwimming();

    void setSwimming(boolean var1);

    boolean isRiptiding();

    boolean isSleeping();

    void setAI(boolean var1);

    boolean hasAI();

    void attack(@NotNull Entity var1);

    void swingMainHand();

    void swingOffHand();

    void setCollidable(boolean var1);

    boolean isCollidable();

    @NotNull
    Set<UUID> getCollidableExemptions();

    @Nullable
    <T> T getMemory(@NotNull MemoryKey<T> var1);

    <T> void setMemory(@NotNull MemoryKey<T> var1, @Nullable T var2);

    @NotNull
    EntityCategory getCategory();

    void setInvisible(boolean var1);

    boolean isInvisible();
}
