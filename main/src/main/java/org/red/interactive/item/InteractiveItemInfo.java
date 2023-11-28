package org.red.interactive.item;

import org.bukkit.inventory.ItemStack;
import org.red.interactive.InteractiveObjInfo;
import org.red.library.interactive.item.InteractiveItem;
import org.red.library.item.ItemBuilder;
import org.red.library.util.persistent.NameSpaceKeyPersistentDataType;

public final class InteractiveItemInfo extends InteractiveObjInfo<ItemStack> {
    public InteractiveItemInfo(InteractiveItem obj) {
        super(obj);
    }

    @Override
    public void setEventInObj(ItemStack obj) {
        new ItemBuilder(obj).setPersistentDataContainer(INTERACTIVE_KEY, NameSpaceKeyPersistentDataType.INSTANCE, getKey()).build();
    }

    @Override
    public InteractiveItem getObj() {
        return (InteractiveItem) super.getObj();
    }
}
