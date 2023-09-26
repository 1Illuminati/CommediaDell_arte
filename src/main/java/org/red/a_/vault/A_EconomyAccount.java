package org.red.a_.vault;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.jetbrains.annotations.NotNull;
import org.red.CommediaDell_arte;
import org.red.library.vault.EconomyAccount;

import java.util.HashMap;
import java.util.Map;

public class A_EconomyAccount implements EconomyAccount {
    private double balance = 0;
    private double bank = 0;
    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public void addBalance(double balance) {
        this.balance += balance;
    }

    @Override
    public boolean hasBalance(double balance) {
        return this.balance >= balance;
    }

    @Override
    public boolean withdrawBank(double balance) {
        if (hasBank(balance)) {
            this.bank -= balance;
            this.balance += balance;
            return true;
        }

        return false;
    }

    @Override
    public boolean depositBank(double balance) {
        if (hasBalance(balance)) {
            this.bank += balance;
            this.balance -= balance;
            return true;
        }

        return false;
    }

    @Override
    public boolean hasBank(double balance) {
        return this.bank >= balance;
    }

    @Override
    public double getBank() {
        return bank;
    }

    @Override
    public void setBank(double balance) {
        this.bank = balance;
    }

    @Override
    public void addBank(double balance) {
        this.bank += balance;
    }

    public void copy(A_EconomyAccount account) {
        this.balance = account.getBalance();
        this.bank = account.getBank();
    }

    @NotNull
    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("balance", balance);
        map.put("bank", bank);
        return map;
    }

    public static A_EconomyAccount deserialize(Map<String, Object> map) {
        A_EconomyAccount account = new A_EconomyAccount();
        account.setBalance(Double.parseDouble(map.get("balance").toString()));
        account.setBank(Double.parseDouble(map.get("bank").toString()));
        return account;
    }
}
