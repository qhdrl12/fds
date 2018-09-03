package com.kakaopay.fds.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "KAKAO_SENDER_LOG")
public class KakaoSenderLog extends BaseEntity {

    @Column(name = "sender_account_number", nullable = false)
    private String senderAccountNumber;

    @Column(name = "before_sender_account_balance", nullable = false)
    private Long beforeSenderAccountBalance;

    @Column(name = "receiver_account_number", nullable = false)
    private String receiverAccountNumber;

    @Column(name = "receiver_user_id", nullable = false)
    private Long receiverUserId;

    @Column(name = "sender_amount")
    private Long senderAmount;

    @Override
    public Long getAmount() {
        return senderAmount;
    }

    @Override
    public String toString() {
        return "KakaoSenderLog{" +
                super.toString() +
                " senderAccountNumber='" + senderAccountNumber + '\'' +
                ", beforeSenderAccountBalance=" + beforeSenderAccountBalance +
                ", receiverAccountNumber='" + receiverAccountNumber + '\'' +
                ", receiverUserId=" + receiverUserId +
                ", senderAmount=" + senderAmount +
                '}';
    }
}


//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private KakaoReceiverLog kakaoReceiverLog;
