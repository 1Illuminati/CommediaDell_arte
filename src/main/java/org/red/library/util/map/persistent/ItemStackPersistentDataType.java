package org.red.library.util.map.persistent;

import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class ItemStackPersistentDataType implements PersistentDataType<PersistentDataContainer, ItemStack> {
    @Override
    public Class<PersistentDataContainer> getPrimitiveType() {
        return null;
    }

    @Override
    public Class<ItemStack> getComplexType() {
        BooleanPersistentDataType booleanPersistentDataType = new BooleanPersistentDataType();
        return null;
    }

    @Override
    public PersistentDataContainer toPrimitive(ItemStack itemStack, PersistentDataAdapterContext persistentDataAdapterContext) {
        return null;
    }

    @Override
    public ItemStack fromPrimitive(PersistentDataContainer dataContainer, PersistentDataAdapterContext persistentDataAdapterContext) {
        return null;
    }
}
