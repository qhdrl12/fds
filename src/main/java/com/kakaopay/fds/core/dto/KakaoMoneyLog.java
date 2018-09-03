package com.kakaopay.fds.core.dto;

import com.kakaopay.fds.api.entity.BaseEntity;
import com.kakaopay.fds.api.entity.KakaoSenderLog;

public class KakaoMoneyLog<T extends BaseEntity> {
    private Enum type;
    private T log;

    public KakaoMoneyLog(Enum type, T log) {
        this.type = type;
        this.log = log;
    }

    public Enum getType() {
        return type;
    }

    public T getLog() {
        return log;
    }

    public Long extractBlance() {
        long amount = log.getAmount();
        return (log instanceof KakaoSenderLog) ? amount * -1 : amount;
    }

    public Long extractBalance(Enum type) {
        if (type == null || type.equals(this.type))  {
            return extractBlance();
        }

        return 0L;
    }

    @Override
    public String toString() {
        return "KakaoMoneyLog{" +
                "type=" + type.name() +
                ", log=" + log +
                '}';
    }
}
