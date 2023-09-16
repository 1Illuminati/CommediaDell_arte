package org.red.library.vault;

import org.bukkit.configuration.serialization.ConfigurationSerializable;

public interface EconomyAccount extends ConfigurationSerializable {
    /**
     * 플레이어의 잔액을 반환합니다.
     * @return 플레이어가 현재 가진 돈
     */
    double getBalance();

    /**
     * 플레이어의 잔액을 설정합니다.
     * @param balance 설정할 잔액
     */
    void setBalance(double balance);

    /**
     * 플레이어의 잔액을 더합니다.
     * @param balance 더할 금액
     */
    void addBalance(double balance);

    /**
     * 플레이어의 해당 금액을 가지고 있는지 확인합니다.
     * @param balance 확인할 금액
     * @return 해당 금액이상을 가지고 있으면 true, 아니면 false
     */
    boolean hasBalance(double balance);

    /**
     * 플레이어 은행 계좌에서 돈을 인출합니다.
     * @param balance 인출할 금액
     * @return 인출에 성공하면 true, 실패하면 false
     */
    boolean withdrawBank(double balance);

    /**
     * 플레이어 은행 계좌에 돈을 입금합니다.
     * @param balance 입금할 금액
     * @return 입금에 성공하면 true, 실패하면 false
     */
    boolean depositBank(double balance);

    /**
     * 플레이어 은행 계좌에 해당 금액을 가지고 있는지 확인합니다.
     * @param balance 확인할 금액
     * @return 해당 금액이상을 가지고 있으면 true, 아니면 false
     */
    boolean hasBank(double balance);

    /**
     * 플레이어의 은행 계좌에 있는 잔액을 반환합니다.
     * @return 플레이어의 은행 계좌에 있는 잔액
     */
    double getBank();

    /**
     * 플레이어 은행 계좌의 잔액을 설정합니다.
     * @param balance 설정할 잔액
     */
    void setBank(double balance);

    /**
     *  플레이어 은행 계좌에 돈을 더합니다.
     * @param balance 더할 금액
     */
    void addBank(double balance);
}
