package com.kakaopay.fds.core.rule;

import com.kakaopay.fds.core.constant.RuleEnum;
import com.kakaopay.fds.core.dto.KakaoMoneyLog;
import com.kakaopay.fds.core.dto.RuleDataSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.kakaopay.fds.core.constant.RuleConfigConstant.*;
import static com.kakaopay.fds.core.util.AmountUtil.sumBalance;
import static com.kakaopay.fds.core.util.KakaoLogUtil.LogFilterByDateAndType;
import static com.kakaopay.fds.core.util.PredicateUtil.isGreaterThan;
import static com.kakaopay.fds.core.util.PredicateUtil.isLessThan;

public class RuleA implements Rule {

    private static Logger log = LoggerFactory.getLogger(RuleA.class);

    @Override
    public boolean isMatch(List<KakaoMoneyLog> kakaoMoneyLogs) {

        RuleDataSet ruleDataSet = new RuleDataSet(kakaoMoneyLogs);
        // 1. DateRange 설정
        ruleDataSet.setDateRange(RuleEnum.ACCOUNT_OPEN, getConfig(RULE_TIME_CONDITION));
        // 2. 시간 && 타입으로 필터링
        List<KakaoMoneyLog> filterLogs = LogFilterByDateAndType(ruleDataSet, null);
        // 3. 충전 합계
        Long chargeAmount = sumBalance(filterLogs, RuleEnum.CHARGE);
        // 4. 총 합계
        Long totalAmount = sumBalance(filterLogs, null);

        // 5. 충전 합계가 설정 값보다 같거나 높고, 현재 잔액이 설정값 보다 같거나 작을 경우
        return isGreaterThan(getConfig(RULE_CHARGE_AMOUNT_THRESHOLD)).test(chargeAmount) &&
                isLessThan(getConfig(RULE_TOTAL_AMOUNT_THRESHOLD)).test(totalAmount);
    }
}
