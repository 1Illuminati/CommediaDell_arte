package org.red.library.item;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.red.library.item.event.EventItem;
import org.red.library.item.event.EventItemManager;

import java.util.List;
import java.util.Map;

public class ItemBuilder {
    private final ItemStack itemStack;
    private final ItemMeta itemMeta;
    private final PersistentDataContainer persistentDataContainer;

    public ItemBuilder(Material material) {
        this(new ItemStack(material));
    }

    public ItemBuilder(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.itemMeta = itemStack.getItemMeta();
        this.persistentDataContainer = this.itemMeta.getPersistentDataContainer();
    }

    public ItemBuilder addAttribute(Attribute attribute, double amount, AttributeModifier.Operation operation) {
        itemMeta.addAttributeModifier(attribute, new AttributeModifier(attribute.getKey().getKey(), amount, operation));
        return this;
    }

    public ItemBuilder addAttribute(Attribute attribute, AttributeModifier attributeModifier) {
        itemMeta.addAttributeModifier(attribute, attributeModifier);
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment ench, int level) {
        itemStack.addEnchantment(ench, level);
        return this;
    }

    public ItemBuilder addEnchantments(Map<Enchantment, Integer> enchantments) {
        itemStack.addEnchantments(enchantments);
        return this;
    }

    public ItemBuilder addUnsafeEnchantment(Enchantment ench, int level) {
        itemStack.addUnsafeEnchantment(ench, level);
        return this;
    }

    public ItemBuilder addUnsafeEnchantments(Map<Enchantment, Integer> enchantments) {
        itemStack.addUnsafeEnchantments(enchantments);
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        itemStack.setAmount(amount);
        return this;
    }

    public ItemBuilder setData(MaterialData data) {
        itemStack.setData(data);
        return this;
    }

    public ItemBuilder setDurability(short durability) {
        itemStack.setDurability(durability);
        return this;
    }

    public ItemBuilder setItemMeta(ItemMeta itemMeta) {
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder setType(Material type) {
        itemStack.setType(type);
        return this;
    }

    public ItemBuilder addEnchant(Enchantment arg0, int arg1, boolean arg2) {
        itemMeta.addEnchant(arg0, arg1, arg2);
        return this;
    }

    public ItemBuilder addItemFlags(ItemFlag... arg0) {
        itemMeta.addItemFlags(arg0);
        return this;
    }

    public ItemBuilder setDisplayName(String arg0) {
        itemMeta.setDisplayName(arg0);
        return this;
    }

    public ItemBuilder setLocalizedName(String arg0) {
        itemMeta.setLocalizedName(arg0);
        return this;
    }

    public ItemBuilder setLore(List<String> arg0) {
        itemMeta.setLore(arg0);
        return this;
    }

    public ItemBuilder setUnbreakable(boolean arg0) {
        itemMeta.setUnbreakable(arg0);
        return this;
    }

    public ItemBuilder setCustomModelData(Integer data) {
        itemMeta.setCustomModelData(data);
        return this;
    }

    public <T, Z> ItemBuilder setPersistentDataContainer(NamespacedKey key, PersistentDataType<T, Z> type, Z value) {
        persistentDataContainer.set(key, type, value);
        return this;
    }

    public ItemBuilder setEventItem(String code) {
        EventItemManager.setItemInEvent(this.itemStack, code);
        return this;
    }

    public ItemBuilder setEventItem(EventItem eventItem) {
        EventItemManager.setItemInEvent(this.itemStack, eventItem.getCode());
        return this;
    }

    public ItemStack build() {
        return this.setItemMeta(this.itemMeta).itemStack;
    }
}
