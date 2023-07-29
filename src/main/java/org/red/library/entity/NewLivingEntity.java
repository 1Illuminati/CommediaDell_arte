package org.red.library.entity;

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

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class NewLivingEntity extends NewEntity {
    private final LivingEntity livingEntity;
    protected NewLivingEntity(LivingEntity livingEntity) {
        super(livingEntity);
        this.livingEntity = livingEntity;
    }

    @Override
    public LivingEntity getEntity() {
        return livingEntity;
    }

    public double getEyeHeight() {
        return livingEntity.getEyeHeight();
    }

    public double getEyeHeight(boolean b) {
        return livingEntity.getEyeHeight(b);
    }

    public Location getEyeLocation() {
        return livingEntity.getEyeLocation();
    }

    public List<Block> getLineOfSight(Set<Material> set, int i) {
        return livingEntity.getLineOfSight(set, i);
    }

    public Block getTargetBlock(Set<Material> set, int i) {
        return livingEntity.getTargetBlock(set, i);
    }

    public List<Block> getLastTwoTargetBlocks(Set<Material> set, int i) {
        return livingEntity.getLastTwoTargetBlocks(set, i);
    }

    public Block getTargetBlockExact(int i) {
        return livingEntity.getTargetBlockExact(i);
    }

    public Block getTargetBlockExact(int i, FluidCollisionMode fluidCollisionMode) {
        return livingEntity.getTargetBlockExact(i, fluidCollisionMode);
    }

    public RayTraceResult rayTraceBlocks(double v) {
        return livingEntity.rayTraceBlocks(v);
    }

    public RayTraceResult rayTraceBlocks(double v, FluidCollisionMode fluidCollisionMode) {
        return livingEntity.rayTraceBlocks(v, fluidCollisionMode);
    }

    public int getRemainingAir() {
        return livingEntity.getRemainingAir();
    }

    public void setRemainingAir(int i) {
        livingEntity.setRemainingAir(i);
    }

    public int getMaximumAir() {
        return livingEntity.getMaximumAir();
    }

    public void setMaximumAir(int i) {
        livingEntity.setMaximumAir(i);
    }

    public int getArrowCooldown() {
        return livingEntity.getArrowCooldown();
    }

    public void setArrowCooldown(int i) {
        livingEntity.setArrowCooldown(i);
    }

    public int getArrowsInBody() {
        return livingEntity.getArrowsInBody();
    }

    public void setArrowsInBody(int i) {
        livingEntity.setArrowsInBody(i);
    }

    public int getMaximumNoDamageTicks() {
        return livingEntity.getMaximumNoDamageTicks();
    }

    public void setMaximumNoDamageTicks(int i) {
        livingEntity.setMaximumNoDamageTicks(i);
    }

    public double getLastDamage() {
        return livingEntity.getLastDamage();
    }

    public void setLastDamage(double v) {
        livingEntity.setLastDamage(v);
    }

    public int getNoDamageTicks() {
        return livingEntity.getNoDamageTicks();
    }

    public void setNoDamageTicks(int i) {
        livingEntity.setNoDamageTicks(i);
    }

    public Player getKiller() {
        return livingEntity.getKiller();
    }

    public boolean addPotionEffect(PotionEffect potionEffect) {
        return livingEntity.addPotionEffect(potionEffect);
    }

    @Deprecated
    public boolean addPotionEffect(PotionEffect potionEffect, boolean b) {
        return livingEntity.addPotionEffect(potionEffect, b);
    }

    public boolean addPotionEffects(Collection<PotionEffect> collection) {
        return livingEntity.addPotionEffects(collection);
    }

    public boolean hasPotionEffect(PotionEffectType potionEffectType) {
        return livingEntity.hasPotionEffect(potionEffectType);
    }

    public PotionEffect getPotionEffect(PotionEffectType potionEffectType) {
        return livingEntity.getPotionEffect(potionEffectType);
    }

    public void removePotionEffect(PotionEffectType potionEffectType) {
        livingEntity.removePotionEffect(potionEffectType);
    }

    public Collection<PotionEffect> getActivePotionEffects() {
        return livingEntity.getActivePotionEffects();
    }

    public boolean hasLineOfSight(Entity entity) {
        return livingEntity.hasLineOfSight(entity);
    }

    public boolean getRemoveWhenFarAway() {
        return livingEntity.getRemoveWhenFarAway();
    }

    public void setRemoveWhenFarAway(boolean b) {
        livingEntity.setRemoveWhenFarAway(b);
    }

    public EntityEquipment getEquipment() {
        return livingEntity.getEquipment();
    }

    public void setCanPickupItems(boolean b) {
        livingEntity.setCanPickupItems(b);
    }

    public boolean getCanPickupItems() {
        return livingEntity.getCanPickupItems();
    }

    public boolean isLeashed() {
        return livingEntity.isLeashed();
    }

    public Entity getLeashHolder() throws IllegalStateException {
        return livingEntity.getLeashHolder();
    }

    public boolean setLeashHolder(Entity entity) {
        return livingEntity.setLeashHolder(entity);
    }

    public boolean isGliding() {
        return livingEntity.isGliding();
    }

    public void setGliding(boolean b) {
        livingEntity.setGliding(b);
    }

    public boolean isSwimming() {
        return livingEntity.isSwimming();
    }

    public void setSwimming(boolean b) {
        livingEntity.setSwimming(b);
    }

    public boolean isRiptiding() {
        return livingEntity.isRiptiding();
    }

    public boolean isSleeping() {
        return livingEntity.isSleeping();
    }

    public void setAI(boolean b) {
        livingEntity.setAI(b);
    }

    public boolean hasAI() {
        return livingEntity.hasAI();
    }

    public void attack(Entity entity) {
        livingEntity.attack(entity);
    }

    public void swingMainHand() {
        livingEntity.swingMainHand();
    }

    public void swingOffHand() {
        livingEntity.swingOffHand();
    }

    public void setCollidable(boolean b) {
        livingEntity.setCollidable(b);
    }

    public boolean isCollidable() {
        return livingEntity.isCollidable();
    }

    public Set<UUID> getCollidableExemptions() {
        return livingEntity.getCollidableExemptions();
    }

    public <T> T getMemory(MemoryKey<T> memoryKey) {
        return livingEntity.getMemory(memoryKey);
    }

    public <T> void setMemory(MemoryKey<T> memoryKey, T t) {
        livingEntity.setMemory(memoryKey, t);
    }

    public EntityCategory getCategory() {
        return livingEntity.getCategory();
    }

    public void setInvisible(boolean b) {
        livingEntity.setInvisible(b);
    }

    public boolean isInvisible() {
        return livingEntity.isInvisible();
    }

    public AttributeInstance getAttribute(Attribute attribute) {
        return livingEntity.getAttribute(attribute);
    }

    public void damage(double v) {
        livingEntity.damage(v);
    }

    public void damage(double v, Entity entity) {
        livingEntity.damage(v, entity);
    }

    public double getHealth() {
        return livingEntity.getHealth();
    }

    public void setHealth(double v) {
        livingEntity.setHealth(v);
    }

    public double getAbsorptionAmount() {
        return livingEntity.getAbsorptionAmount();
    }

    public void setAbsorptionAmount(double v) {
        livingEntity.setAbsorptionAmount(v);
    }

    @Deprecated
    public double getMaxHealth() {
        return livingEntity.getMaxHealth();
    }

    @Deprecated
    public void setMaxHealth(double v) {
        livingEntity.setMaxHealth(v);
    }

    @Deprecated
    public void resetMaxHealth() {
        livingEntity.resetMaxHealth();
    }

    public <T extends Projectile> T launchProjectile(Class<? extends T> aClass) {
        return livingEntity.launchProjectile(aClass);
    }

    public <T extends Projectile> T launchProjectile(Class<? extends T> aClass, Vector vector) {
        return livingEntity.launchProjectile(aClass, vector);
    }
}
