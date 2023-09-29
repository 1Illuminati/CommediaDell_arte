package org.red.interactive.block;

import org.bukkit.block.TileState;
import org.bukkit.persistence.PersistentDataContainer;
import org.red.interactive.InteractiveObjInfo;
import org.red.library.interactive.block.InteractiveTile;
import org.red.library.util.persistent.NameSpaceKeyPersistentDataType;

public final class InteractiveTileInfo extends InteractiveObjInfo<TileState> {
    public InteractiveTileInfo(InteractiveTile obj) {
        super(obj);
    }

    @Override
    public void setEventInObj(TileState obj) {
        PersistentDataContainer container = obj.getPersistentDataContainer();
        container.set(INTERACTIVE_KEY, NameSpaceKeyPersistentDataType.INSTANCE, getKey());
        obj.update(true);
    }

    @Override
    public InteractiveTile getObj() {
        return (InteractiveTile) super.getObj();
    }
}
