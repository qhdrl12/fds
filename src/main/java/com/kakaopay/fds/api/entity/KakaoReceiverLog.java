package com.kakaopay.fds.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "KAKAO_RECEIVER_LOG")
public class KakaoReceiverLog extends BaseEntity {

    @Column(name = "receiver_account_number", nullable = false)
    private String receiverAccountNumber;

    @Column(name = "before_receiver_account_balance", nullable = false)
    private Long beforeReceiverAccountbalance;

    @Column(name = "sender_account_number", nullable = false)
    private String senderAccountNumber;

    @Column(name = "sender_user_id", nullable = false)
    private Long senrderUserId;

    @Column(name = "receiver_amount", nullable = false)
    private Long receiverAmount;

    @Override
    public Long getAmount() {
        return receiverAmount;
    }

    @Override
    public String toString() {
        return "KakaoReceiverLog{" +
                super.toString() +
                " receiverAccountNumber='" + receiverAccountNumber + '\'' +
                ", beforeReceiverAccountbalance=" + beforeReceiverAccountbalance +
                ", senderAccountNumber='" + senderAccountNumber + '\'' +
                ", senrderUserId=" + senrderUserId +
                ", receiverAmount='" + receiverAmount + '\'' +
                '}';
    }
}
