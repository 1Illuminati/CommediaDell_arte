package org.red.library.entity.player;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.block.data.BlockData;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.map.MapView;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.Scoreboard;
import org.jetbrains.annotations.NotNull;
import org.red.library.CommediaDell_arte;
import org.red.library.entity.a_.impl.A_LivingEntityImpl;
import org.red.library.entity.player.npc.NpcPlayer;
import org.red.library.entity.player.offline.NewOfflinePlayer;
import org.red.library.inventory.CustomGui;
import org.red.library.util.map.CoolTime;
import org.red.library.util.map.DataMap;

import java.net.InetSocketAddress;
import java.util.*;

public class APlayer {
    private static final HashMap<UUID, APlayer> newPlayerMap = new HashMap<>();

    public static APlayer getNewPlayer(UUID playerUUID) {
        Player player = Bukkit.getPlayer(playerUUID);

        if (player == null)
            return null;

        return APlayer.getNewPlayer(player);
    }

    public static APlayer getNewPlayer(String playerName) {
        Player player = Bukkit.getPlayer(playerName);

        if (player == null)
            return null;

        return APlayer.getNewPlayer(player);
    }

    public static APlayer getNewPlayer(Player player) {
        if (player.hasMetadata("NPC")) {
            return NpcPlayer.getNPCPlayer(player);
        }

        if (!newPlayerMap.containsKey(player.getUniqueId()))
            newPlayerMap.put(player.getUniqueId(), new APlayer(player));
        else if (!newPlayerMap.get(player.getUniqueId()).getPlayer().equals(player))
            newPlayerMap.put(player.getUniqueId(), new APlayer(player));

        return newPlayerMap.get(player.getUniqueId());
    }
    private final Player player;
    private final NewOfflinePlayer newOfflinePlayer;
    private final PlayerData playerData;
    private boolean ignoreCloseEvent = false;
    protected APlayer(Player player) {
        super(player);
        this.player = player;
        this.newOfflinePlayer = NewOfflinePlayer.getNewOfflinePlayer(player);
        this.playerData = newOfflinePlayer.getPlayerData();
    }

    public void openInventory(CustomGui gui) {
        this.openInventory(gui.getInventory());
    }

    public void delayOpenInventory(CustomGui gui) {
        this.delayOpenInventory(gui.getInventory(), false);
    }

    public void delayOpenInventory(CustomGui gui, boolean ignoreCloseEvent) {
        this.delayOpenInventory(gui.getInventory(), ignoreCloseEvent);
    }

    public void delayOpenInventory(Inventory inv) {
        this.delayOpenInventory(inv, false);
    }

    public void delayOpenInventory(Inventory inv, boolean ignoreCloseEvent) {
        Bukkit.getScheduler().runTaskLater(CommediaDell_arte.getPlugin(), () -> {
            openInventory(inv);

            if (ignoreCloseEvent)
                setIgnoreCloseEvent(true);
        }, 2L);
    }

    public boolean isIgnoreCloseEvent() {
        return ignoreCloseEvent;
    }

    public void setIgnoreCloseEvent(boolean ignoreCloseEvent) {
        this.ignoreCloseEvent = ignoreCloseEvent;
    }

    public NewOfflinePlayer getNewOfflinePlayer() {
        return newOfflinePlayer;
    }

    public DataMap getDataMap() {
        return newOfflinePlayer.getDataMap();
    }

    public CoolTime getCoolTime() {
        return newOfflinePlayer.getCoolTime();
    }

    public PlayerData getPlayerData() {
        return playerData;
    }

    public boolean isClown() {
        return newOfflinePlayer.isClown();
    }

    public boolean isNpc() {
        return false;
    }

    public void sendActionBar(String message) {
        this.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
    }

    /***
     * 이 밑에서부턴 위임 메서드
     */


    public String getDisplayName() {
        return player.getDisplayName();
    }

    public void setDisplayName(String s) {
        player.setDisplayName(s);
    }

    public String getPlayerListName() {
        return player.getPlayerListName();
    }

    public void setPlayerListName(String s) {
        player.setPlayerListName(s);
    }

    public String getPlayerListHeader() {
        return player.getPlayerListHeader();
    }

    public String getPlayerListFooter() {
        return player.getPlayerListFooter();
    }

    public void setPlayerListHeader(String s) {
        player.setPlayerListHeader(s);
    }

    public void setPlayerListFooter(String s) {
        player.setPlayerListFooter(s);
    }

    public void setPlayerListHeaderFooter(String s, String s1) {
        player.setPlayerListHeaderFooter(s, s1);
    }

    public void setCompassTarget(Location location) {
        player.setCompassTarget(location);
    }

    public Location getCompassTarget() {
        return player.getCompassTarget();
    }

    public InetSocketAddress getAddress() {
        return player.getAddress();
    }

    public void sendRawMessage(String s) {
        player.sendRawMessage(s);
    }

    public void kickPlayer(String s) {
        player.kickPlayer(s);
    }

    public void chat(String s) {
        player.chat(s);
    }

    public boolean performCommand(String s) {
        return player.performCommand(s);
    }

    public boolean isSneaking() {
        return player.isSneaking();
    }

    public void setSneaking(boolean b) {
        player.setSneaking(b);
    }

    public boolean isSprinting() {
        return player.isSprinting();
    }

    public void setSprinting(boolean b) {
        player.setSprinting(b);
    }

    public void saveData() {
        player.saveData();
    }

    public void loadData() {
        player.loadData();
    }

    public void setSleepingIgnored(boolean b) {
        player.setSleepingIgnored(b);
    }

    public boolean isSleepingIgnored() {
        return player.isSleepingIgnored();
    }

    public Location getBedSpawnLocation() {
        return player.getBedSpawnLocation();
    }

    public void setBedSpawnLocation(Location location) {
        player.setBedSpawnLocation(location);
    }

    public void setBedSpawnLocation(Location location, boolean b) {
        player.setBedSpawnLocation(location, b);
    }

    @Deprecated
    public void playNote(Location location, byte b, byte b1) {
        player.playNote(location, b, b1);
    }

    public void playNote(Location location, Instrument instrument, Note note) {
        player.playNote(location, instrument, note);
    }

    public void playSound(Location location, Sound sound, float v, float v1) {
        player.playSound(location, sound, v, v1);
    }

    public void playSound(Location location, String s, float v, float v1) {
        player.playSound(location, s, v, v1);
    }

    public void playSound(Location location, Sound sound, SoundCategory soundCategory, float v, float v1) {
        player.playSound(location, sound, soundCategory, v, v1);
    }

    public void playSound(Location location, String s, SoundCategory soundCategory, float v, float v1) {
        player.playSound(location, s, soundCategory, v, v1);
    }

    public void stopSound(Sound sound) {
        player.stopSound(sound);
    }

    public void stopSound(String s) {
        player.stopSound(s);
    }

    public void stopSound(Sound sound, SoundCategory soundCategory) {
        player.stopSound(sound, soundCategory);
    }

    public void stopSound(String s, SoundCategory soundCategory) {
        player.stopSound(s, soundCategory);
    }

    @Deprecated
    public void playEffect(Location location, Effect effect, int i) {
        player.playEffect(location, effect, i);
    }

    public <T> void playEffect(Location location, Effect effect, T t) {
        player.playEffect(location, effect, t);
    }

    @Deprecated
    public void sendBlockChange(Location location, Material material, byte b) {
        player.sendBlockChange(location, material, b);
    }

    public void sendBlockChange(Location location, BlockData blockData) {
        player.sendBlockChange(location, blockData);
    }

    public void sendBlockDamage(Location location, float v) {
        player.sendBlockDamage(location, v);
    }

    @Deprecated
    public boolean sendChunkChange(Location location, int i, int i1, int i2, byte[] bytes) {
        return player.sendChunkChange(location, i, i1, i2, bytes);
    }

    public void sendSignChange(Location location, String[] strings) throws IllegalArgumentException {
        player.sendSignChange(location, strings);
    }

    public void sendSignChange(Location location, String[] strings, DyeColor dyeColor) throws IllegalArgumentException {
        player.sendSignChange(location, strings, dyeColor);
    }

    public void sendMap(MapView mapView) {
        player.sendMap(mapView);
    }

    public void updateInventory() {
        player.updateInventory();
    }

    public void setPlayerTime(long l, boolean b) {
        player.setPlayerTime(l, b);
    }

    public long getPlayerTime() {
        return player.getPlayerTime();
    }

    public long getPlayerTimeOffset() {
        return player.getPlayerTimeOffset();
    }

    public boolean isPlayerTimeRelative() {
        return player.isPlayerTimeRelative();
    }

    public void resetPlayerTime() {
        player.resetPlayerTime();
    }

    public void setPlayerWeather(WeatherType weatherType) {
        player.setPlayerWeather(weatherType);
    }

    public WeatherType getPlayerWeather() {
        return player.getPlayerWeather();
    }

    public void resetPlayerWeather() {
        player.resetPlayerWeather();
    }

    public void giveExp(int i) {
        player.giveExp(i);
    }

    public void giveExpLevels(int i) {
        player.giveExpLevels(i);
    }

    public float getExp() {
        return player.getExp();
    }

    public void setExp(float v) {
        player.setExp(v);
    }

    public int getLevel() {
        return player.getLevel();
    }

    public void setLevel(int i) {
        player.setLevel(i);
    }

    public int getTotalExperience() {
        return player.getTotalExperience();
    }

    public void setTotalExperience(int i) {
        player.setTotalExperience(i);
    }

    public void sendExperienceChange(float v) {
        player.sendExperienceChange(v);
    }

    public void sendExperienceChange(float v, int i) {
        player.sendExperienceChange(v, i);
    }

    public boolean getAllowFlight() {
        return player.getAllowFlight();
    }

    public void setAllowFlight(boolean b) {
        player.setAllowFlight(b);
    }

    @Deprecated
    public void hidePlayer(Player player) {
        this.player.hidePlayer(player);
    }

    public void hidePlayer(Plugin plugin, Player player) {
        this.player.hidePlayer(plugin, player);
    }

    @Deprecated
    public void showPlayer(Player player) {
        this.player.showPlayer(player);
    }

    public void showPlayer(Plugin plugin, Player player) {
        this.player.showPlayer(plugin, player);
    }

    public boolean canSee(Player player) {
        return this.player.canSee(player);
    }

    public boolean isFlying() {
        return player.isFlying();
    }

    public void setFlying(boolean b) {
        player.setFlying(b);
    }

    public void setFlySpeed(float v) throws IllegalArgumentException {
        player.setFlySpeed(v);
    }

    public void setWalkSpeed(float v) throws IllegalArgumentException {
        player.setWalkSpeed(v);
    }

    public float getFlySpeed() {
        return player.getFlySpeed();
    }

    public float getWalkSpeed() {
        return player.getWalkSpeed();
    }

    @Deprecated
    public void setTexturePack(String s) {
        player.setTexturePack(s);
    }

    public void setResourcePack(String s) {
        player.setResourcePack(s);
    }

    public void setResourcePack(String s, byte[] bytes) {
        player.setResourcePack(s, bytes);
    }

    public Scoreboard getScoreboard() {
        return player.getScoreboard();
    }

    public void setScoreboard(Scoreboard scoreboard) throws IllegalArgumentException, IllegalStateException {
        player.setScoreboard(scoreboard);
    }

    public boolean isHealthScaled() {
        return player.isHealthScaled();
    }

    public void setHealthScaled(boolean b) {
        player.setHealthScaled(b);
    }

    public void setHealthScale(double v) throws IllegalArgumentException {
        player.setHealthScale(v);
    }

    public double getHealthScale() {
        return player.getHealthScale();
    }

    public Entity getSpectatorTarget() {
        return player.getSpectatorTarget();
    }

    public void setSpectatorTarget(Entity entity) {
        player.setSpectatorTarget(entity);
    }

    @Deprecated
    public void sendTitle(String s, String s1) {
        player.sendTitle(s, s1);
    }

    public void sendTitle(String s, String s1, int i, int i1, int i2) {
        player.sendTitle(s, s1, i, i1, i2);
    }

    public void resetTitle() {
        player.resetTitle();
    }

    public void spawnParticle(Particle particle, Location location, int i) {
        player.spawnParticle(particle, location, i);
    }

    public void spawnParticle(Particle particle, double v, double v1, double v2, int i) {
        player.spawnParticle(particle, v, v1, v2, i);
    }

    public <T> void spawnParticle(Particle particle, Location location, int i, T t) {
        player.spawnParticle(particle, location, i, t);
    }

    public <T> void spawnParticle(Particle particle, double v, double v1, double v2, int i, T t) {
        player.spawnParticle(particle, v, v1, v2, i, t);
    }

    public void spawnParticle(Particle particle, Location location, int i, double v, double v1, double v2) {
        player.spawnParticle(particle, location, i, v, v1, v2);
    }

    public void spawnParticle(Particle particle, double v, double v1, double v2, int i, double v3, double v4, double v5) {
        player.spawnParticle(particle, v, v1, v2, i, v3, v4, v5);
    }

    public <T> void spawnParticle(Particle particle, Location location, int i, double v, double v1, double v2, T t) {
        player.spawnParticle(particle, location, i, v, v1, v2, t);
    }

    public <T> void spawnParticle(Particle particle, double v, double v1, double v2, int i, double v3, double v4, double v5, T t) {
        player.spawnParticle(particle, v, v1, v2, i, v3, v4, v5, t);
    }

    public void spawnParticle(Particle particle, Location location, int i, double v, double v1, double v2, double v3) {
        player.spawnParticle(particle, location, i, v, v1, v2, v3);
    }

    public void spawnParticle(Particle particle, double v, double v1, double v2, int i, double v3, double v4, double v5, double v6) {
        player.spawnParticle(particle, v, v1, v2, i, v3, v4, v5, v6);
    }

    public <T> void spawnParticle(Particle particle, Location location, int i, double v, double v1, double v2, double v3, T t) {
        player.spawnParticle(particle, location, i, v, v1, v2, v3, t);
    }

    public <T> void spawnParticle(Particle particle, double v, double v1, double v2, int i, double v3, double v4, double v5, double v6, T t) {
        player.spawnParticle(particle, v, v1, v2, i, v3, v4, v5, v6, t);
    }

    public AdvancementProgress getAdvancementProgress(Advancement advancement) {
        return player.getAdvancementProgress(advancement);
    }

    public int getClientViewDistance() {
        return player.getClientViewDistance();
    }

    public int getPing() {
        return player.getPing();
    }

    public String getLocale() {
        return player.getLocale();
    }

    public void updateCommands() {
        player.updateCommands();
    }

    public void openBook(ItemStack itemStack) {
        player.openBook(itemStack);
    }

    public PlayerInventory getInventory() {
        return player.getInventory();
    }

    public Inventory getEnderChest() {
        return player.getEnderChest();
    }

    public MainHand getMainHand() {
        return player.getMainHand();
    }

    public boolean setWindowProperty(InventoryView.Property property, int i) {
        return player.setWindowProperty(property, i);
    }

    public InventoryView getOpenInventory() {
        return player.getOpenInventory();
    }

    public InventoryView openInventory(Inventory inventory) {
        return player.openInventory(inventory);
    }

    public InventoryView openWorkbench(Location location, boolean b) {
        return player.openWorkbench(location, b);
    }

    public InventoryView openEnchanting(Location location, boolean b) {
        return player.openEnchanting(location, b);
    }

    public void openInventory(InventoryView inventoryView) {
        player.openInventory(inventoryView);
    }

    public InventoryView openMerchant(Villager villager, boolean b) {
        return player.openMerchant(villager, b);
    }

    public InventoryView openMerchant(Merchant merchant, boolean b) {
        return player.openMerchant(merchant, b);
    }

    public void closeInventory() {
        player.closeInventory();
    }

    @Deprecated
    public ItemStack getItemInHand() {
        return player.getItemInHand();
    }

    @Deprecated
    public void setItemInHand(ItemStack itemStack) {
        player.setItemInHand(itemStack);
    }

    public ItemStack getItemOnCursor() {
        return player.getItemOnCursor();
    }

    public void setItemOnCursor(ItemStack itemStack) {
        player.setItemOnCursor(itemStack);
    }

    public boolean hasCooldown(Material material) {
        return player.hasCooldown(material);
    }

    public int getCooldown(Material material) {
        return player.getCooldown(material);
    }

    public void setCooldown(Material material, int i) {
        player.setCooldown(material, i);
    }

    public int getSleepTicks() {
        return player.getSleepTicks();
    }

    public boolean sleep(Location location, boolean b) {
        return player.sleep(location, b);
    }

    public void wakeup(boolean b) {
        player.wakeup(b);
    }

    public Location getBedLocation() {
        return player.getBedLocation();
    }

    public GameMode getGameMode() {
        return player.getGameMode();
    }

    public void setGameMode(GameMode gameMode) {
        player.setGameMode(gameMode);
    }

    public boolean isBlocking() {
        return player.isBlocking();
    }

    public boolean isHandRaised() {
        return player.isHandRaised();
    }

    public int getExpToLevel() {
        return player.getExpToLevel();
    }

    public float getAttackCooldown() {
        return player.getAttackCooldown();
    }

    public boolean discoverRecipe(NamespacedKey namespacedKey) {
        return player.discoverRecipe(namespacedKey);
    }

    public int discoverRecipes(Collection<NamespacedKey> collection) {
        return player.discoverRecipes(collection);
    }

    public boolean undiscoverRecipe(NamespacedKey namespacedKey) {
        return player.undiscoverRecipe(namespacedKey);
    }

    public int undiscoverRecipes(Collection<NamespacedKey> collection) {
        return player.undiscoverRecipes(collection);
    }

    public boolean hasDiscoveredRecipe(NamespacedKey namespacedKey) {
        return player.hasDiscoveredRecipe(namespacedKey);
    }

    public Set<NamespacedKey> getDiscoveredRecipes() {
        return player.getDiscoveredRecipes();
    }

    @Deprecated
    public Entity getShoulderEntityLeft() {
        return player.getShoulderEntityLeft();
    }

    @Deprecated
    public void setShoulderEntityLeft(Entity entity) {
        player.setShoulderEntityLeft(entity);
    }

    @Deprecated
    public Entity getShoulderEntityRight() {
        return player.getShoulderEntityRight();
    }

    @Deprecated
    public void setShoulderEntityRight(Entity entity) {
        player.setShoulderEntityRight(entity);
    }

    public boolean dropItem(boolean b) {
        return player.dropItem(b);
    }

    public float getExhaustion() {
        return player.getExhaustion();
    }

    public void setExhaustion(float v) {
        player.setExhaustion(v);
    }

    public float getSaturation() {
        return player.getSaturation();
    }

    public void setSaturation(float v) {
        player.setSaturation(v);
    }

    public int getFoodLevel() {
        return player.getFoodLevel();
    }

    public void setFoodLevel(int i) {
        player.setFoodLevel(i);
    }

    public int getSaturatedRegenRate() {
        return player.getSaturatedRegenRate();
    }

    public void setSaturatedRegenRate(int i) {
        player.setSaturatedRegenRate(i);
    }

    public int getUnsaturatedRegenRate() {
        return player.getUnsaturatedRegenRate();
    }

    public void setUnsaturatedRegenRate(int i) {
        player.setUnsaturatedRegenRate(i);
    }

    public int getStarvationRate() {
        return player.getStarvationRate();
    }

    public void setStarvationRate(int i) {
        player.setStarvationRate(i);
    }

    public boolean isConversing() {
        return player.isConversing();
    }

    public void acceptConversationInput(String s) {
        player.acceptConversationInput(s);
    }

    public boolean beginConversation(Conversation conversation) {
        return player.beginConversation(conversation);
    }

    public void abandonConversation(Conversation conversation) {
        player.abandonConversation(conversation);
    }

    public void abandonConversation(Conversation conversation, ConversationAbandonedEvent conversationAbandonedEvent) {
        player.abandonConversation(conversation, conversationAbandonedEvent);
    }

    public void sendRawMessage(UUID uuid, String s) {
        player.sendRawMessage(uuid, s);
    }

    public boolean isOnline() {
        return player.isOnline();
    }

    public boolean isBanned() {
        return player.isBanned();
    }

    public boolean isWhitelisted() {
        return player.isWhitelisted();
    }

    public void setWhitelisted(boolean b) {
        player.setWhitelisted(b);
    }

    public Player getPlayer() {
        return player.getPlayer();
    }

    public long getFirstPlayed() {
        return player.getFirstPlayed();
    }

    public long getLastPlayed() {
        return player.getLastPlayed();
    }

    public boolean hasPlayedBefore() {
        return player.hasPlayedBefore();
    }

    public void incrementStatistic(Statistic statistic) throws IllegalArgumentException {
        player.incrementStatistic(statistic);
    }

    public void decrementStatistic(Statistic statistic) throws IllegalArgumentException {
        player.decrementStatistic(statistic);
    }

    public void incrementStatistic(Statistic statistic, int i) throws IllegalArgumentException {
        player.incrementStatistic(statistic, i);
    }

    public void decrementStatistic(Statistic statistic, int i) throws IllegalArgumentException {
        player.decrementStatistic(statistic, i);
    }

    public void setStatistic(Statistic statistic, int i) throws IllegalArgumentException {
        player.setStatistic(statistic, i);
    }

    public int getStatistic(Statistic statistic) throws IllegalArgumentException {
        return player.getStatistic(statistic);
    }

    public void incrementStatistic(Statistic statistic, Material material) throws IllegalArgumentException {
        player.incrementStatistic(statistic, material);
    }

    public void decrementStatistic(Statistic statistic, Material material) throws IllegalArgumentException {
        player.decrementStatistic(statistic, material);
    }

    public int getStatistic(Statistic statistic, Material material) throws IllegalArgumentException {
        return player.getStatistic(statistic, material);
    }

    public void incrementStatistic(Statistic statistic, Material material, int i) throws IllegalArgumentException {
        player.incrementStatistic(statistic, material, i);
    }

    public void decrementStatistic(Statistic statistic, Material material, int i) throws IllegalArgumentException {
        player.decrementStatistic(statistic, material, i);
    }

    public void setStatistic(Statistic statistic, Material material, int i) throws IllegalArgumentException {
        player.setStatistic(statistic, material, i);
    }

    public void incrementStatistic(Statistic statistic, EntityType entityType) throws IllegalArgumentException {
        player.incrementStatistic(statistic, entityType);
    }

    public void decrementStatistic(Statistic statistic, EntityType entityType) throws IllegalArgumentException {
        player.decrementStatistic(statistic, entityType);
    }

    public int getStatistic(Statistic statistic, EntityType entityType) throws IllegalArgumentException {
        return player.getStatistic(statistic, entityType);
    }

    public void incrementStatistic(Statistic statistic, EntityType entityType, int i) throws IllegalArgumentException {
        player.incrementStatistic(statistic, entityType, i);
    }

    public void decrementStatistic(Statistic statistic, EntityType entityType, int i) {
        player.decrementStatistic(statistic, entityType, i);
    }

    public void setStatistic(Statistic statistic, EntityType entityType, int i) {
        player.setStatistic(statistic, entityType, i);
    }

    public @NotNull Map<String, Object> serialize() {
        return player.serialize();
    }

    public void sendPluginMessage(Plugin plugin, String s, byte[] bytes) {
        player.sendPluginMessage(plugin, s, bytes);
    }

    public Set<String> getListeningPluginChannels() {
        return player.getListeningPluginChannels();
    }
}
