package com.kakaopay.fds.core.util;

import com.kakaopay.fds.api.entity.KakaoServiceAccountOpenLog;
import com.kakaopay.fds.core.constant.RuleEnum;
import com.kakaopay.fds.core.dto.RuleDataSet;
import com.kakaopay.fds.core.dto.KakaoMoneyLog;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.kakaopay.fds.core.util.DateUtil.isValidDateTime;
import static com.kakaopay.fds.core.util.PredicateUtil.isGreaterThan;

public class KakaoLogUtil {

    public static KakaoServiceAccountOpenLog findAccountOpenLog(List<KakaoMoneyLog> kakaoMoneyLogs) {
        Optional<KakaoMoneyLog> kakaoMoneyLog = kakaoMoneyLogs.stream()
                .filter(log -> RuleEnum.ACCOUNT_OPEN.equals(log.getType()))
                .findFirst();

        return kakaoMoneyLog.map(log ->  (KakaoServiceAccountOpenLog) log.getLog()).orElse(null);
    }

    public static List<KakaoMoneyLog> LogFilterByDateAndType(RuleDataSet ruleDataSet, Enum type) {
        return ruleDataSet.getKakaoMoneyLogs().stream()
                .filter(log -> {
                    LocalDateTime dateTime = log.getLog().getCreateTime();
                    boolean condition = isValidDateTime(dateTime, ruleDataSet.getStartDateTime(), ruleDataSet.getEndDateTime());
                    if (type != null) {
                        condition = condition && log.getType().equals(type);
                    }
                    return  condition;
                }).collect(Collectors.toList());
    }

    public static List<KakaoMoneyLog> LogFilterByDateAndType(List<KakaoMoneyLog> kakaoMoneyLogs, LocalDateTime start, LocalDateTime end, Enum type) {
        return kakaoMoneyLogs.stream()
                .filter(log -> {
                    LocalDateTime dateTime = log.getLog().getCreateTime();
                    boolean condition = isValidDateTime(dateTime, start, end);
                    if (type != null) {
                        condition = condition && log.getType().equals(type);
                    }
                    return  condition;
                }).collect(Collectors.toList());
    }

    public static boolean ruleCountByAmountAndCount(List<KakaoMoneyLog> kakaoMoneyLog, Long amountThreshold, Long countThreshold) {
        long count = kakaoMoneyLog.stream()
                .filter(log -> isGreaterThan(amountThreshold).test(log.getLog().getAmount()))
//                .filter(log -> log.getLog().getAmount() >= amountThreshold)
                .count();
        return count >= countThreshold;
    }
}
