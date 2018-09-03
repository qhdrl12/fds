package com.kakaopay.fds.api.service;

import com.kakaopay.fds.api.entity.KakaoChargeLog;
import com.kakaopay.fds.api.entity.KakaoReceiverLog;
import com.kakaopay.fds.api.entity.KakaoSenderLog;
import com.kakaopay.fds.api.entity.KakaoServiceAccountOpenLog;
import com.kakaopay.fds.api.repository.KakaoChargeLogRepository;
import com.kakaopay.fds.api.repository.KakaoReceiverLogRepository;
import com.kakaopay.fds.api.repository.KakaoSenderLogRepository;
import com.kakaopay.fds.api.repository.KakaoServiceAccountOpenLogRepository;
import com.kakaopay.fds.api.response.FdsResponse;
import com.kakaopay.fds.core.constant.RuleEnum;
import com.kakaopay.fds.core.dto.KakaoMoneyLog;
import com.kakaopay.fds.core.dto.RuleDataSet;
import com.kakaopay.fds.core.executor.FdsEngine;
import com.kakaopay.fds.core.rule.RuleA;
import com.kakaopay.fds.core.rule.RuleB;
import com.kakaopay.fds.core.rule.RuleC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class FdsServiceImpl implements FdsService {

    private KakaoServiceAccountOpenLogRepository kakaoServiceAccountOpenLogRepository;
    private KakaoChargeLogRepository kakaoChargeLogRepository;
    private KakaoSenderLogRepository kakaoSenderLogRepository;
    private KakaoReceiverLogRepository kakaoReceiverLogRepository;

    private static FdsEngine fdsEngine;

    @PostConstruct
    public void setup() {
        fdsEngine = new FdsEngine();
        fdsEngine.registerRule(new RuleA());
        fdsEngine.registerRule(new RuleB());
        fdsEngine.registerRule(new RuleC());
    }

    @Autowired
    public FdsServiceImpl(KakaoServiceAccountOpenLogRepository kakaoServiceAccountOpenLogRepository,
                          KakaoChargeLogRepository kakaoChargeLogRepository,
                          KakaoSenderLogRepository kakaoSenderLogRepository,
                          KakaoReceiverLogRepository kakaoReceiverLogRepository) {
        this.kakaoServiceAccountOpenLogRepository = kakaoServiceAccountOpenLogRepository;
        this.kakaoChargeLogRepository = kakaoChargeLogRepository;
        this.kakaoSenderLogRepository = kakaoSenderLogRepository;
        this.kakaoReceiverLogRepository = kakaoReceiverLogRepository;
    }

    @Override
    public FdsResponse search(Long userId) {
        FdsResponse fdsResponse = new FdsResponse(userId);
        List<String> matches = new ArrayList<>();
        // 계좌 생성일 조회
        KakaoServiceAccountOpenLog kakaoServiceAccountOpenLog = this.kakaoServiceAccountOpenLogRepository.findByUserId(userId);

        if (kakaoServiceAccountOpenLog != null) {
            LocalDateTime accountOpenTime = kakaoServiceAccountOpenLog.getCreateTime();

            List<KakaoReceiverLog> kakaoReceiverLogs = this.kakaoReceiverLogRepository.findAllByUserIdAndCreateTimeAfter(userId, accountOpenTime);
            List<KakaoSenderLog> kakaoSenderLogs = this.kakaoSenderLogRepository.findAllByUserIdAndCreateTimeAfter(userId, accountOpenTime);
            List<KakaoChargeLog> kakaoChargeLogs = this.kakaoChargeLogRepository.findAllByUserIdAndCreateTimeAfter(userId, accountOpenTime);

            List<KakaoMoneyLog> kakaoLogs = new ArrayList<>();
            kakaoLogs.add(new KakaoMoneyLog(RuleEnum.ACCOUNT_OPEN, kakaoServiceAccountOpenLog));
            kakaoReceiverLogs.forEach(log -> kakaoLogs.add(new KakaoMoneyLog(RuleEnum.RECEIVE, log)));
            kakaoSenderLogs.forEach(log -> kakaoLogs.add(new KakaoMoneyLog(RuleEnum.SEND, log)));
            kakaoChargeLogs.forEach(log -> kakaoLogs.add(new KakaoMoneyLog(RuleEnum.CHARGE, log)));

            matches = fdsEngine.match(kakaoLogs);
        } else {
            log.info("The user({}) account opening log does not exist.", userId);
        }
        fdsResponse.setRule(matches);

        return fdsResponse;
    }
}
