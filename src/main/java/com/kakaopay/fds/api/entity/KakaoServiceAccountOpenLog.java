package com.kakaopay.fds.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "KAKAO_SERVICE_ACCOUNT_OPEN_LOG")
public class KakaoServiceAccountOpenLog extends BaseEntity {

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @Override
    public Long getAmount() {
        return 0L;
    }

    @Override
    public String toString() {
        return "KakaoServiceAccountOpenLog{" +
                super.toString() +
                " accountNumber='" + accountNumber + '\'' +
                '}';
    }
}
