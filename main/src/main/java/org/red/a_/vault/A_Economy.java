package org.red.a_.vault;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.red.CommediaDell_arte;
import org.red.library.A_;
import org.red.library.a_.entity.player.offline.A_OfflinePlayer;

import java.util.ArrayList;
import java.util.List;

/**
 * 볼트 플러그인과의 호환을 위해 만들어진 클래스
 * 볼트 플러그인과 A_EconomyAccount를 연결해주는 역할을 합니다.
 * 단 해당 플러그인은 월드를 구분하여 지갑을 제공하는 기능을 비활성화 합니다.
 * 은행기능은 아직 미완성 추후에 추가할 예정
 */
public class A_Economy implements Economy {
    private static A_Economy aEconomy;
    public static void setEconomy() {
        CommediaDell_arte.sendLog("Vault Plugin Checked");
        A_Economy aEconomy = new A_Economy();
        Bukkit.getServicesManager().register(Economy.class, aEconomy, CommediaDell_arte.getPlugin(), org.bukkit.plugin.ServicePriority.Highest);
        A_Economy.aEconomy = aEconomy;
    }

    public static A_Economy getEconomy() {
        return aEconomy;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return "A_Economy";
    }

    @Override
    public boolean hasBankSupport() {
        return false;
    }

    @Override
    public int fractionalDigits() {
        return -1;
    }

    @Override
    public String format(double v) {
        return v + "원";
    }

    @Override
    public String currencyNamePlural() {
        return "원";
    }

    @Override
    public String currencyNameSingular() {
        return "원";
    }

    @Override
    public boolean hasAccount(String s) {
        return true;
    }

    @Override
    public boolean hasAccount(OfflinePlayer offlinePlayer) {
        return true;
    }

    @Override
    public boolean hasAccount(String s, String s1) {
        return true;
    }

    @Override
    public boolean hasAccount(OfflinePlayer offlinePlayer, String s) {
        return true;
    }

    @Override
    public double getBalance(String s) {
        return this.getBalance(A_.getAOfflinePlayer(s));
    }

    @Override
    public double getBalance(OfflinePlayer offlinePlayer) {
        return this.getBalance(A_.getAOfflinePlayer(offlinePlayer));
    }

    @Override
    public double getBalance(String s, String s1) {
        return this.getBalance(s);
    }

    @Override
    public double getBalance(OfflinePlayer offlinePlayer, String s) {
        return this.getBalance(offlinePlayer);
    }

    public double getBalance(A_OfflinePlayer aOfflinePlayer) {
        return aOfflinePlayer.getEconomyAccount().getBalance();
    }

    @Override
    public boolean has(String s, double v) {
        return this.has(A_.getAOfflinePlayer(s), v);
    }

    @Override
    public boolean has(OfflinePlayer offlinePlayer, double v) {
        return this.has(A_.getAOfflinePlayer(offlinePlayer), v);
    }

    @Override
    public boolean has(String s, String s1, double v) {
        return this.has(s, v);
    }

    @Override
    public boolean has(OfflinePlayer offlinePlayer, String s, double v) {
        return this.has(offlinePlayer, v);
    }

    public boolean has(A_OfflinePlayer aOfflinePlayer, double v) {
        return aOfflinePlayer.getEconomyAccount().hasBalance(v);
    }

    @Override
    public EconomyResponse withdrawPlayer(String s, double v) {
        return withdrawPlayer(A_.getAOfflinePlayer(s), v);
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, double v) {
        return withdrawPlayer(A_.getAOfflinePlayer(offlinePlayer), v);
    }

    @Override
    public EconomyResponse withdrawPlayer(String s, String s1, double v) {
        return withdrawPlayer(s, v);
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, String s, double v) {
        return withdrawPlayer(offlinePlayer, v);
    }

    public EconomyResponse withdrawPlayer(A_OfflinePlayer aOfflinePlayer, double v) {
        if (this.has(aOfflinePlayer, v)) {
            aOfflinePlayer.getEconomyAccount().addBalance(-v);
            return new EconomyResponse(v, aOfflinePlayer.getEconomyAccount().getBalance(), EconomyResponse.ResponseType.SUCCESS, "test success");
        }

        return new EconomyResponse(v, aOfflinePlayer.getEconomyAccount().getBalance(), EconomyResponse.ResponseType.FAILURE, "test failure");
    }

    @Override
    public EconomyResponse depositPlayer(String s, double v) {
        return this.depositPlayer(A_.getAOfflinePlayer(s), v);
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, double v) {
        return this.depositPlayer(A_.getAOfflinePlayer(offlinePlayer), v);
    }

    @Override
    public EconomyResponse depositPlayer(String s, String s1, double v) {
        return this.depositPlayer(s, v);
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, String s, double v) {
        return this.depositPlayer(offlinePlayer, v);
    }

    public EconomyResponse depositPlayer(A_OfflinePlayer aOfflinePlayer, double v) {
        aOfflinePlayer.getEconomyAccount().addBalance(v);
        return new EconomyResponse(v, aOfflinePlayer.getEconomyAccount().getBalance(), EconomyResponse.ResponseType.SUCCESS, "test success");
    }

    @Override
    public EconomyResponse createBank(String s, String s1) {
        return null;
    }

    @Override
    public EconomyResponse createBank(String s, OfflinePlayer offlinePlayer) {
        return null;
    }

    @Override
    public EconomyResponse deleteBank(String s) {
        return null;
    }

    @Override
    public EconomyResponse bankBalance(String s) {
        return null;
    }

    @Override
    public EconomyResponse bankHas(String s, double v) {
        return null;
    }

    @Override
    public EconomyResponse bankWithdraw(String s, double v) {
        return null;
    }

    @Override
    public EconomyResponse bankDeposit(String s, double v) {
        return null;
    }

    @Override
    public EconomyResponse isBankOwner(String s, String s1) {
        return null;
    }

    @Override
    public EconomyResponse isBankOwner(String s, OfflinePlayer offlinePlayer) {
        return null;
    }

    @Override
    public EconomyResponse isBankMember(String s, String s1) {
        return null;
    }

    @Override
    public EconomyResponse isBankMember(String s, OfflinePlayer offlinePlayer) {
        return null;
    }

    @Override
    public List<String> getBanks() {
        return new ArrayList<>();
    }

    @Override
    public boolean createPlayerAccount(String s) {
        return false;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer offlinePlayer) {
        return false;
    }

    @Override
    public boolean createPlayerAccount(String s, String s1) {
        return false;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer offlinePlayer, String s) {
        return false;
    }
}
