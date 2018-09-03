package com.kakaopay.fds.core.rule;

import com.kakaopay.fds.core.constant.RuleEnum;
import com.kakaopay.fds.core.dto.KakaoMoneyLog;
import com.kakaopay.fds.core.dto.RuleDataSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.kakaopay.fds.core.constant.RuleConfigConstant.*;
import static com.kakaopay.fds.core.util.KakaoLogUtil.*;

public class RuleB implements Rule {

    private static Logger log = LoggerFactory.getLogger(RuleB.class);

    @Override
    public boolean isMatch(List<KakaoMoneyLog> kakaoMoneyLogs) {

        RuleDataSet ruleDataSet = new RuleDataSet(kakaoMoneyLogs);
        // 1. DateRange 설정
        ruleDataSet.setDateRange(RuleEnum.ACCOUNT_OPEN, getConfig(RULE_TIME_CONDITION));
        // 2. 시간 && 타입으로 필터링
        List<KakaoMoneyLog> filterLogs = LogFilterByDateAndType(ruleDataSet, RuleEnum.RECEIVE);

        // 3. 설정된 금액 이상으로 받은 로그 필터링 후 카운트 && 설정된 카운트 이상 검출 검사
        return ruleCountByAmountAndCount(filterLogs,
                getConfig(RULE_RECEIVE_AMOUNT_THRESHOLD),
                getConfig(RULE_RECEIVE_COUNT_THRESHOLD));
    }
}
