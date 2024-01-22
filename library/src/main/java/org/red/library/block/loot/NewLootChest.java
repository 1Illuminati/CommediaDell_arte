package org.red.library.block.loot;

import org.bukkit.Keyed;
import org.bukkit.Location;
import org.bukkit.block.Chest;
import org.bukkit.inventory.ItemStack;
import org.red.library.a_.entity.player.A_Player;
import org.red.library.a_.world.A_World;

public interface NewLootChest extends Keyed {

    /**
     * 실질적 상자 클래스를 반환합니다.
     * @return 상자 (org.bukkit.block.BlockState)
     */
    Chest getChest();

    /**
     * 상자의 보상 아이템을 반환합니다.
     * 그 누구도 털지 않은 상태의 원본 보상을 반환합니다.
     * @return 보상 아이템 (org.bukkit.inventory.ItemStack[])
     */
    ItemStack[] reward();

    /**
     * 상자의 위치를 반환합니다.
     * @return 상자의 위치 (org.bukkit.Location)
     */
    Location getLocation();

    /**
     * 상자가 설치된 A월드를 반환합니다.
     * @return 상자가 설치된 월드 (org.red.library.a_.world.A_World)
     */
    A_World getWorld();

    /**
     * 상자의 남은 보상 아이템을 반환합니다.
     * 만약 isRewardPlayer가 false일 경우 reward()와 동일한 값을 반환합니다.
     * @return 남은 보상 아이템 (org.bukkit.inventory.ItemStack[])
     */
    ItemStack[] getLessReward();

    boolean isCoolTimeGroup();

    boolean isRewardPlayer();

    /**
     * 상자의 쿨타임을 반환합니다.
     * @return 상자의 쿨타임 (double)
     */
    double getCoolTime();

    /**
     * 플레이어가 상자를 열 수 있을때까지의 남은 쿨타임을 반환합니다.
     * BoxRestrictionType이 Group일 경우 모든 플레이어의 쿨타임 반환 값이 동일합니다.
     * @param player 쿨타임을 확인할 플레이어 (org.red.library.a_.entity.player.A_Player)
     * @return 남은 쿨타임 (double)
     */
    double getLessCoolTime(A_Player player);

    /**
     * 플레이어가 상자를 열 수 있을때 까지 남은 시간을 설정합니다.
     * BoxRestrictionType이 Group일 경우 모든 플레이어에게 동일한 쿨타임이 적용됩니다.
     * @param player 쿨타임을 설정할 플레이어 (org.red.library.a_.entity.player.A_Player)
     * @param time 설정할 쿨타임 (double)
     */
    void setCoolTime(A_Player player, double time);

    /**
     * 상자를 열 수 있는지 확인합니다.
     * @param player 확인할 플레이어 (org.red.library.a_.entity.player.A_Player)
     * @return 열 수 있는지 여부 (boolean)
     */
    boolean canOpen(A_Player player);

    String name();

}
