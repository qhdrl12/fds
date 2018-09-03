package com.kakaopay.fds.core.rule;

import com.kakaopay.fds.api.repository.KakaoChargeLogRepository;
import com.kakaopay.fds.api.repository.KakaoReceiverLogRepository;
import com.kakaopay.fds.api.repository.KakaoSenderLogRepository;
import com.kakaopay.fds.api.repository.KakaoServiceAccountOpenLogRepository;
import com.kakaopay.fds.core.KakaoLogSetup;
import com.kakaopay.fds.core.dto.KakaoMoneyLog;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RuleATest {

    @Autowired
    KakaoServiceAccountOpenLogRepository kakaoServiceAccountOpenLogRepository;

    @Autowired
    KakaoChargeLogRepository kakaoChargeLogRepository;

    @Autowired
    KakaoSenderLogRepository kakaoSenderLogRepository;

    @Autowired
    KakaoReceiverLogRepository kakaoReceiverLogRepository;

    private static List<KakaoMoneyLog> kakaoMoneyLogs = new ArrayList<>();
    private static Rule rule;

    @Before
    public void setup() {
        rule = new RuleA();
        KakaoLogSetup kakaoLogSetup = new KakaoLogSetup(kakaoServiceAccountOpenLogRepository, kakaoChargeLogRepository, kakaoSenderLogRepository, kakaoReceiverLogRepository);
        kakaoMoneyLogs = kakaoLogSetup.getKakaoMoneyLogs();
    }

    @Test
    public void 룰_검사() {
        assertThat(rule.isMatch(kakaoMoneyLogs)).isTrue();
    }

    @Test
    public void LOG_데이터가_없을경우() {
        assertThat(rule.isMatch(new ArrayList<>())).isFalse();
    }
}
