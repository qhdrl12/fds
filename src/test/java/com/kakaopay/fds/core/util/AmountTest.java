package com.kakaopay.fds.core.util;

import com.kakaopay.fds.core.KakaoLogSetup;
import com.kakaopay.fds.core.constant.RuleEnum;
import com.kakaopay.fds.core.dto.KakaoMoneyLog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AmountTest {

    @Test
    public void 로그에서_금액_추출하기_계좌개설() {
        Long balance = new KakaoMoneyLog(RuleEnum.ACCOUNT_OPEN,
                KakaoLogSetup.getKakaoServiceAccountOpenLog()).extractBlance();
        System.out.println("Amount : " + balance);
        assertThat(balance).isZero();
    }

    @Test
    public void 로그에서_금액_추출하기_충전() {
        Long balance = new KakaoMoneyLog(RuleEnum.CHARGE,
KakaoLogSetup.getKakaoChargeLog()).extractBlance();
        System.out.println("Amount : " + balance);
        assertThat(balance).isGreaterThan(0L);
    }

    @Test
    public void 로그에서_금액_추출하기_전달() {
        Long balance = new KakaoMoneyLog(RuleEnum.SEND,
                KakaoLogSetup.getKakaoSenderLog()).extractBlance();
        System.out.println("Amount : " + balance);
        assertThat(balance).isNegative();
    }

    @Test
    public void 로그에서_금액_추출하기_받기() {
        Long balance = new KakaoMoneyLog(RuleEnum.RECEIVE,
                KakaoLogSetup.getKakaoReceiverLog()).extractBlance();
        System.out.println("Amount : " + balance);
        assertThat(balance).isGreaterThan(0L);
    }

    @Test
    public void 로그에서_금액_추출하기_타입구분_다른타입() {
        Long balance = new KakaoMoneyLog(RuleEnum.RECEIVE,
                KakaoLogSetup.getKakaoReceiverLog()).extractBalance(RuleEnum.SEND);
        System.out.println("Amount : " + balance);
        assertThat(balance).isZero();
    }

    @Test
    public void 로그에서_금액_추출하기_타입구분_같은타입() {
        Long balance = new KakaoMoneyLog(RuleEnum.RECEIVE,
                KakaoLogSetup.getKakaoReceiverLog()).extractBalance(RuleEnum.RECEIVE);
        System.out.println("Amount : " + balance);
        assertThat(balance).isGreaterThan(0L);
    }
}
