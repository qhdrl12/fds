package com.kakaopay.fds.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "KAKAO_CHARGE_LOG")
public class KakaoChargeLog extends BaseEntity {

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @Column(name = "charge_amount", nullable = false)
    private Long chargeAmount;

    @Column(name = "bank_account_number", nullable = false)
    private String bankAccountNumber;

    @Override
    public Long getAmount() {
        return chargeAmount;
    }

    @Override
    public String toString() {
        return "KakaoChargeLog{" +
                super.toString() +
                " accountNumber='" + accountNumber + '\'' +
                ", chargeAmount=" + chargeAmount +
                ", bankAccountNumber='" + bankAccountNumber + '\'' +
                '}';
    }
}
