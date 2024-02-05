package org.red.work;

import org.bukkit.World;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.red.library.A_;
import org.red.library.a_.world.A_World;
import org.red.library.inventory.CustomGui;

import java.util.ArrayList;
import java.util.Arrays;

public class Gui extends CustomGui {
    public Gui(World world) {
        super(world.getName(), 54);

        A_World aWorld = A_.getAWorld(world);

        int i = 0;
        for (ItemStack itemStack : aWorld.getDataMap().getList("spawner_drop_item_set", new ArrayList<ItemStack>())) {
            this.setItem(i++, itemStack);
        }
    }

    public void onClose(InventoryCloseEvent event) {
        A_World aWorld = A_.getAWorld(event.getPlayer().getWorld());
        ArrayList<ItemStack> itemStacks = (ArrayList<ItemStack>) aWorld.getDataMap().getList("spawner_drop_item_set", new ArrayList<ItemStack>());
        itemStacks.clear();
        Arrays.asList(this.getContents()).forEach(itemStack -> {
            if (itemStack != null) {
                itemStacks.add(itemStack);
            }
        });
    }
}
