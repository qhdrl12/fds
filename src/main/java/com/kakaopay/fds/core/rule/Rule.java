package com.kakaopay.fds.core.rule;

import com.kakaopay.fds.core.config.RuleConfigLoader;
import com.kakaopay.fds.core.dto.KakaoMoneyLog;

import java.util.List;

public interface Rule {

    boolean isMatch(List<KakaoMoneyLog> kakaoMoneyLogs);

    default Long getConfig(String key) {
        return RuleConfigLoader.getLongValue(this.getClass().getSimpleName() + key);
    }
}
