package com.kakaopay.fds.core.util;

import com.kakaopay.fds.api.entity.KakaoServiceAccountOpenLog;
import com.kakaopay.fds.core.KakaoLogSetup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KakaoLogTest {

    @Test
    public void 계좌_개설_로그_추출() {
        KakaoServiceAccountOpenLog accountOpenLog = KakaoLogUtil.findAccountOpenLog(KakaoLogSetup.getStaticKakaoMoneyLogs());
        System.out.println("accountOpenLog : " + accountOpenLog);
        assertThat(accountOpenLog).isNotNull();
    }

    @Test
    public void 로그_설정금액_설정횟수_비교_성공() {
        boolean result = KakaoLogUtil.ruleCountByAmountAndCount(KakaoLogSetup.getStaticKakaoMoneyLogs(), 150000L, 1L);
        System.out.println("result : " + result);
        assertThat(result).isTrue();
    }

    //주의 필터링 된 로그를 이용하여야 정확한 결과가 나옴
    @Test
    public void 로그_설정금액_설정횟수_비교_실패() {
        System.out.println("log : " + KakaoLogSetup.getStaticKakaoMoneyLogs());
        assertThat(KakaoLogUtil.ruleCountByAmountAndCount(KakaoLogSetup.getStaticKakaoMoneyLogs(), 160000L, 2L)).isFalse();
    }
}
