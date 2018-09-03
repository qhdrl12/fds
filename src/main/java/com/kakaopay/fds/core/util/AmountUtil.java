package com.kakaopay.fds.core.util;

import com.kakaopay.fds.core.dto.KakaoMoneyLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AmountUtil {
    private static Logger log = LoggerFactory.getLogger(AmountUtil.class);

    public static Long sumBalance(List<KakaoMoneyLog> kakaoMoneyLogs, Enum type) {
        Long sumAmount = kakaoMoneyLogs.stream()
                .map(log -> log.extractBalance(type))
                .reduce((x, y ) -> x + y)
                .orElse(0L);

        log.debug("sumAmount[{}]={}", (type == null) ? "ALL" : type, sumAmount);
        return sumAmount;
    }
}
