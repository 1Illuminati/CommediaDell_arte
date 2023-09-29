package org.red.block.loot;

import org.red.library.block.loot.LootChest;
import org.red.library.inventory.CustomGui;

public class LootChestGUI extends CustomGui {
    public LootChestGUI(LootChest lootChest) {
        super(lootChest.name(),27);
        this.setStorageContents(lootChest.reward());
    }
}
