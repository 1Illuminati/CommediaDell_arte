package org.red.nms.v1_17_R1;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.red.library.item.AbstractItemManager;

import java.lang.reflect.Field;
import java.util.Base64;
import java.util.UUID;

public class ItemManager extends AbstractItemManager {
    @Override
    public ItemStack getSkullByUrl(String url) {
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        if (url.isEmpty()) {
            return skull;
        }

        SkullMeta skullMeta = (SkullMeta)skull.getItemMeta();

        StringBuilder builder = new StringBuilder();
        url.chars().filter(c -> (c >= '0' && c <= '9') || (c >= 'a' && c <= 'f')).forEach(c -> {
            if (builder.length() != 0 && builder.length() / 4 == 0) builder.append("-");
            builder.append((char) c);
        });
        GameProfile profile = new GameProfile(UUID.fromString(builder.toString()), null);
        byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField;
        try {
            profileField = skullMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(skullMeta, profile);
        }
        catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        skull.setItemMeta(skullMeta);
        return skull;
    }
}
