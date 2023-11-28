package org.red.item.material;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.red.a_.world.A_WorldImpl;
import org.red.library.A_;
import org.red.library.inventory.CustomGui;
import org.red.library.item.ItemBuilder;
import org.red.library.item.material.MaterialAct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BanMaterialGui extends CustomGui {
    private final BanMaterial banMaterial;
    private final int page;
    public BanMaterialGui(World world) {
        this((A_WorldImpl) A_.getAWorld(world));
    }

    public BanMaterialGui(A_WorldImpl aWorld) {
        this(aWorld.getBanMaterial());
    }

    public BanMaterialGui(BanMaterial banMaterial) {
        this(banMaterial, 0);
    }

    public BanMaterialGui(BanMaterial banMaterial, int page) {
        super("BanMaterial", 54);
        this.banMaterial = banMaterial;
        this.page = page;

        List<BanMaterial.BanMaterialInfo> banMaterialInfoList = banMaterial.getBanMaterialInfos();
        for (int i = 0; i < 45; i++) {
            if (i + page * 45 >= banMaterialInfoList.size()) break;
            BanMaterial.BanMaterialInfo banMaterialInfo = banMaterialInfoList.get(i + page * 45);
            List<String> lore = new ArrayList<>();
            Arrays.stream(MaterialAct.values()).forEach(act -> lore.add("§f" + act.name() + ": " + (banMaterialInfo.isActAllowed(act) ? "§a허용" : "§c금지")));
            setItem(i, new ItemBuilder(banMaterialInfo.getMaterial()).setLore(lore).build());

            this.setButton(i, event -> {
                event.setCancelled(true);
                event.getWhoClicked().openInventory(new BanMaterialInfoGui(this, banMaterialInfo).getInventory());
            });
        }

        if (page != 0) {
            this.setItem(45, new ItemBuilder(Material.ARROW).setDisplayName("§f이전 페이지").build());
            this.setButton(45, event -> {
                event.setCancelled(true);
                event.getWhoClicked().openInventory(new BanMaterialGui(banMaterial, page - 1).getInventory());
            });
        }

        this.setItem(53, new ItemBuilder(Material.ARROW).setDisplayName("§f다음 페이지").build());
        this.setButton(53, event -> {
            event.setCancelled(true);
            event.getWhoClicked().openInventory(new BanMaterialGui(banMaterial, page + 1).getInventory());
        });
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        Inventory inventory = event.getClickedInventory();
        if (inventory == null) return;
        if (inventory.getType() != InventoryType.PLAYER) return;
        event.setCancelled(true);

        ItemStack itemStack = event.getCurrentItem();
        if (itemStack == null) return;

        banMaterial.createNewInfo(itemStack.getType());
        event.getWhoClicked().openInventory(new BanMaterialGui(banMaterial, page).getInventory());
    }

    public BanMaterial getBanMaterial() {
        return banMaterial;
    }

    public int getPage() {
        return page;
    }
}
