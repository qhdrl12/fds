package com.kakaopay.fds.core.dto;

import com.kakaopay.fds.api.entity.KakaoServiceAccountOpenLog;
import com.kakaopay.fds.core.constant.RuleEnum;
import com.kakaopay.fds.core.util.KakaoLogUtil;

import java.time.LocalDateTime;
import java.util.List;

public class RuleDataSet {
    private List<KakaoMoneyLog> kakaoMoneyLogs;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public RuleDataSet(List<KakaoMoneyLog> kakaoMoneyLogs) {
        this.kakaoMoneyLogs = kakaoMoneyLogs;
    }

    public List<KakaoMoneyLog> getKakaoMoneyLogs() {
        return kakaoMoneyLogs;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public void setDateRange(Enum type, Long timeCondition) {
        if (RuleEnum.ACCOUNT_OPEN.equals(type)) {
            KakaoServiceAccountOpenLog kakaoServiceAccountOpenLog = KakaoLogUtil.findAccountOpenLog(kakaoMoneyLogs);
            if (kakaoServiceAccountOpenLog != null) {
                LocalDateTime start = kakaoServiceAccountOpenLog.getCreateTime();

                this.setStartDateTime(start);
                this.setEndDateTime(start.plusHours(timeCondition));
            }

        } else {
            LocalDateTime end = LocalDateTime.now();

            this.setStartDateTime(end.plusHours(timeCondition));
            this.setEndDateTime(end);
        }
    }
}
