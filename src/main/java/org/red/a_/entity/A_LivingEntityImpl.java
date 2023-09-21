package org.red.a_.entity;

import org.bukkit.*;
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
import org.red.library.a_.A_Data;
import org.red.a_.A_ManagerImpl;
import org.red.library.a_.entity.A_LivingEntity;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class A_LivingEntityImpl extends A_EntityImpl implements A_LivingEntity {
    private final LivingEntity livingEntity;
    public A_LivingEntityImpl(LivingEntity livingEntity, A_Data aData, A_ManagerImpl.A_Version version) {
        super(livingEntity, aData, version);
        this.livingEntity = livingEntity;
    }

    @Override
    @NotNull
    public <T extends Projectile> T launchProjectile(@NotNull Class<? extends T> aClass) {
        return livingEntity.launchProjectile(aClass);
    }

    @Override
    @NotNull
    public <T extends Projectile> T launchProjectile(@NotNull Class<? extends T> aClass, @Nullable Vector vector) {
        return livingEntity.launchProjectile(aClass, vector);
    }

    @Override
    public LivingEntity getEntity() {
        return livingEntity;
    }

    @Override
    public double getEyeHeight() {
        return livingEntity.getEyeHeight();
    }

    @Override
    public double getEyeHeight(boolean b) {
        return livingEntity.getEyeHeight(b);
    }

    @Override
    @NotNull
    public Location getEyeLocation() {
        return livingEntity.getEyeLocation();
    }

    @Override
    @NotNull
    public List<Block> getLineOfSight(@Nullable Set<Material> set, int i) {
        return livingEntity.getLineOfSight(set, i);
    }

    @Override
    @NotNull
    public Block getTargetBlock(@Nullable Set<Material> set, int i) {
        return livingEntity.getTargetBlock(set, i);
    }

    @Override
    @NotNull
    public List<Block> getLastTwoTargetBlocks(@Nullable Set<Material> set, int i) {
        return livingEntity.getLastTwoTargetBlocks(set, i);
    }

    @Override
    @Nullable
    public Block getTargetBlockExact(int i) {
        return livingEntity.getTargetBlockExact(i);
    }

    @Override
    @Nullable
    public Block getTargetBlockExact(int i, @NotNull FluidCollisionMode fluidCollisionMode) {
        return livingEntity.getTargetBlockExact(i, fluidCollisionMode);
    }

    @Override
    @Nullable
    public RayTraceResult rayTraceBlocks(double v) {
        return livingEntity.rayTraceBlocks(v);
    }

    @Override
    @Nullable
    public RayTraceResult rayTraceBlocks(double v, @NotNull FluidCollisionMode fluidCollisionMode) {
        return livingEntity.rayTraceBlocks(v, fluidCollisionMode);
    }

    @Override
    public int getRemainingAir() {
        return livingEntity.getRemainingAir();
    }

    @Override
    public void setRemainingAir(int i) {
        livingEntity.setRemainingAir(i);
    }

    @Override
    public int getMaximumAir() {
        return livingEntity.getMaximumAir();
    }

    @Override
    public void setMaximumAir(int i) {
        livingEntity.setMaximumAir(i);
    }

    @Override
    public int getArrowCooldown() {
        return livingEntity.getArrowCooldown();
    }

    @Override
    public void setArrowCooldown(int i) {
        livingEntity.setArrowCooldown(i);
    }

    @Override
    public int getArrowsInBody() {
        return livingEntity.getArrowsInBody();
    }

    @Override
    public void setArrowsInBody(int i) {
        livingEntity.setArrowsInBody(i);
    }

    @Override
    public int getMaximumNoDamageTicks() {
        return livingEntity.getMaximumNoDamageTicks();
    }

    @Override
    public void setMaximumNoDamageTicks(int i) {
        livingEntity.setMaximumNoDamageTicks(i);
    }

    @Override
    public double getLastDamage() {
        return livingEntity.getLastDamage();
    }

    @Override
    public void setLastDamage(double v) {
        livingEntity.setLastDamage(v);
    }

    @Override
    public int getNoDamageTicks() {
        return livingEntity.getNoDamageTicks();
    }

    @Override
    public void setNoDamageTicks(int i) {
        livingEntity.setNoDamageTicks(i);
    }

    @Override
    @Nullable
    public Player getKiller() {
        return livingEntity.getKiller();
    }

    @Override
    public boolean addPotionEffect(@NotNull PotionEffect potionEffect) {
        return livingEntity.addPotionEffect(potionEffect);
    }

    @Override
    public boolean addPotionEffect(@NotNull PotionEffectType type, int duration, int amplifier) {
        return this.addPotionEffect(new PotionEffect(type, duration, amplifier));
    }

    @Override
    public boolean addPotionEffect(@NotNull PotionEffect potionEffect, boolean b) {
        return livingEntity.addPotionEffect(potionEffect, b);
    }

    @Override
    public boolean addPotionEffects(@NotNull Collection<PotionEffect> collection) {
        return livingEntity.addPotionEffects(collection);
    }

    @Override
    public boolean hasPotionEffect(@NotNull PotionEffectType potionEffectType) {
        return livingEntity.hasPotionEffect(potionEffectType);
    }

    @Override
    @Nullable
    public PotionEffect getPotionEffect(@NotNull PotionEffectType potionEffectType) {
        return livingEntity.getPotionEffect(potionEffectType);
    }

    @Override
    public void removePotionEffect(@NotNull PotionEffectType potionEffectType) {
        livingEntity.removePotionEffect(potionEffectType);
    }

    @Override
    @NotNull
    public Collection<PotionEffect> getActivePotionEffects() {
        return livingEntity.getActivePotionEffects();
    }

    @Override
    public boolean hasLineOfSight(@NotNull Entity entity) {
        return livingEntity.hasLineOfSight(entity);
    }

    @Override
    public boolean getRemoveWhenFarAway() {
        return livingEntity.getRemoveWhenFarAway();
    }

    @Override
    public void setRemoveWhenFarAway(boolean b) {
        livingEntity.setRemoveWhenFarAway(b);
    }

    @Override
    @Nullable
    public EntityEquipment getEquipment() {
        return livingEntity.getEquipment();
    }

    @Override
    public void setCanPickupItems(boolean b) {
        livingEntity.setCanPickupItems(b);
    }

    @Override
    public boolean getCanPickupItems() {
        return livingEntity.getCanPickupItems();
    }

    @Override
    public boolean isLeashed() {
        return livingEntity.isLeashed();
    }

    @Override
    @NotNull
    public Entity getLeashHolder() throws IllegalStateException {
        return livingEntity.getLeashHolder();
    }

    @Override
    public boolean setLeashHolder(@Nullable Entity entity) {
        return livingEntity.setLeashHolder(entity);
    }

    @Override
    public boolean isGliding() {
        return livingEntity.isGliding();
    }

    @Override
    public void setGliding(boolean b) {
        livingEntity.setGliding(b);
    }

    @Override
    public boolean isSwimming() {
        return livingEntity.isSwimming();
    }

    @Override
    public void setSwimming(boolean b) {
        livingEntity.setSwimming(b);
    }

    @Override
    public boolean isRiptiding() {
        return livingEntity.isRiptiding();
    }

    @Override
    public boolean isSleeping() {
        return livingEntity.isSleeping();
    }

    @Override
    public void setAI(boolean b) {
        livingEntity.setAI(b);
    }

    @Override
    public boolean hasAI() {
        return livingEntity.hasAI();
    }

    @Override
    public void attack(@NotNull Entity entity) {
        livingEntity.attack(entity);
    }

    @Override
    public void swingMainHand() {
        livingEntity.swingMainHand();
    }

    @Override
    public void swingOffHand() {
        livingEntity.swingOffHand();
    }

    @Override
    public void setCollidable(boolean b) {
        livingEntity.setCollidable(b);
    }

    @Override
    public boolean isCollidable() {
        return livingEntity.isCollidable();
    }

    @Override
    @NotNull
    public Set<UUID> getCollidableExemptions() {
        return livingEntity.getCollidableExemptions();
    }

    @Override
    @Nullable
    public <T> T getMemory(@NotNull MemoryKey<T> memoryKey) {
        return livingEntity.getMemory(memoryKey);
    }

    @Override
    public <T> void setMemory(@NotNull MemoryKey<T> memoryKey, @Nullable T t) {
        livingEntity.setMemory(memoryKey, t);
    }

    @Override
    @NotNull
    public EntityCategory getCategory() {
        return livingEntity.getCategory();
    }

    @Override
    public void setInvisible(boolean b) {
        livingEntity.setInvisible(b);
    }

    @Override
    public boolean isInvisible() {
        return livingEntity.isInvisible();
    }

    @Override
    @Nullable
    public AttributeInstance getAttribute(@NotNull Attribute attribute) {
        return livingEntity.getAttribute(attribute);
    }

    @Override
    public void damage(double v) {
        livingEntity.damage(v);
    }

    @Override
    public void damage(double v, @Nullable Entity entity) {
        livingEntity.damage(v, entity);
    }

    @Override
    public double getHealth() {
        return livingEntity.getHealth();
    }

    @Override
    public void setHealth(double v) {
        livingEntity.setHealth(v);
    }

    @Override
    public double getAbsorptionAmount() {
        return livingEntity.getAbsorptionAmount();
    }

    @Override
    public void setAbsorptionAmount(double v) {
        livingEntity.setAbsorptionAmount(v);
    }

    @Override
    public double getMaxHealth() {
        return livingEntity.getMaxHealth();
    }

    @Override
    public void setMaxHealth(double v) {
        livingEntity.setMaxHealth(v);
    }

    @Override
    public void resetMaxHealth() {
        livingEntity.resetMaxHealth();
    }
}
