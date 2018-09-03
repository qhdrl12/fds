package com.kakaopay.fds.core;

import com.kakaopay.fds.api.entity.KakaoChargeLog;
import com.kakaopay.fds.api.entity.KakaoReceiverLog;
import com.kakaopay.fds.api.entity.KakaoSenderLog;
import com.kakaopay.fds.api.entity.KakaoServiceAccountOpenLog;
import com.kakaopay.fds.api.repository.KakaoChargeLogRepository;
import com.kakaopay.fds.api.repository.KakaoReceiverLogRepository;
import com.kakaopay.fds.api.repository.KakaoSenderLogRepository;
import com.kakaopay.fds.api.repository.KakaoServiceAccountOpenLogRepository;
import com.kakaopay.fds.core.constant.RuleEnum;
import com.kakaopay.fds.core.dto.KakaoMoneyLog;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class KakaoLogSetup {

    private List<KakaoMoneyLog> kakaoMoneyLogs = new ArrayList<>();
    private static List<KakaoMoneyLog> staticKakaoMoneyLogs = new ArrayList<>();
    private static KakaoServiceAccountOpenLog kakaoServiceAccountOpenLog = new KakaoServiceAccountOpenLog();
    private static KakaoChargeLog kakaoChargeLog = new KakaoChargeLog();
    private static KakaoSenderLog kakaoSenderLog = new KakaoSenderLog();
    private static KakaoReceiverLog kakaoReceiverLog = new KakaoReceiverLog();

    static {
        LocalDateTime dateTime = LocalDateTime.now();

        String accountNumber = "222-111-111";
        String bankAccountNumber = "333333-44-333333";

        kakaoServiceAccountOpenLog.setUserId(1L);
        kakaoServiceAccountOpenLog.setCreateTime(dateTime.minusDays(7));
        kakaoServiceAccountOpenLog.setAccountNumber(accountNumber);
        staticKakaoMoneyLogs.add(new KakaoMoneyLog(RuleEnum.ACCOUNT_OPEN, kakaoServiceAccountOpenLog));

        kakaoChargeLog.setUserId(1L);
        kakaoChargeLog.setCreateTime(dateTime.minusMinutes(7*24*60 - 1));
        kakaoChargeLog.setAccountNumber(accountNumber);
        kakaoChargeLog.setChargeAmount(100000L);
        kakaoChargeLog.setBankAccountNumber(bankAccountNumber);
        staticKakaoMoneyLogs.add(new KakaoMoneyLog(RuleEnum.CHARGE, kakaoChargeLog));

        kakaoChargeLog = new KakaoChargeLog();
        kakaoChargeLog.setUserId(1L);
        kakaoChargeLog.setCreateTime(dateTime.minusMinutes(7*24*60 - 2));
        kakaoChargeLog.setAccountNumber(accountNumber);
        kakaoChargeLog.setChargeAmount(100000L);
        kakaoChargeLog.setBankAccountNumber(bankAccountNumber);
        staticKakaoMoneyLogs.add(new KakaoMoneyLog(RuleEnum.CHARGE, kakaoChargeLog));

        kakaoSenderLog.setUserId(1L);
        kakaoSenderLog.setCreateTime(dateTime.minusMinutes(7*24*60 - 3));
        kakaoSenderLog.setSenderAccountNumber(accountNumber);
        kakaoSenderLog.setBeforeSenderAccountBalance(200000L);
        kakaoSenderLog.setReceiverAccountNumber("999-888-888");
        kakaoSenderLog.setReceiverUserId(2L);
        kakaoSenderLog.setSenderAmount(150000L);
        staticKakaoMoneyLogs.add(new KakaoMoneyLog(RuleEnum.SEND, kakaoSenderLog));

        kakaoSenderLog = new KakaoSenderLog();
        kakaoSenderLog.setUserId(1L);
        kakaoSenderLog.setCreateTime(dateTime.minusMinutes(7*24*60 - 3));
        kakaoSenderLog.setSenderAccountNumber(accountNumber);
        kakaoSenderLog.setBeforeSenderAccountBalance(50000L);
        kakaoSenderLog.setReceiverAccountNumber("999-888-888");
        kakaoSenderLog.setReceiverUserId(2L);
        kakaoSenderLog.setSenderAmount(49000L);
        staticKakaoMoneyLogs.add(new KakaoMoneyLog(RuleEnum.SEND, kakaoSenderLog));

        kakaoReceiverLog.setUserId(1L);
        kakaoReceiverLog.setCreateTime(dateTime.minusMinutes(45));
        kakaoReceiverLog.setReceiverAccountNumber(accountNumber);
        kakaoReceiverLog.setBeforeReceiverAccountbalance(1000L);
        kakaoReceiverLog.setSenderAccountNumber("999-888-888");
        kakaoReceiverLog.setSenrderUserId(2L);
        kakaoReceiverLog.setReceiverAmount(150000L);
        staticKakaoMoneyLogs.add(new KakaoMoneyLog(RuleEnum.RECEIVE, kakaoReceiverLog));
    }

    public KakaoLogSetup(KakaoServiceAccountOpenLogRepository kakaoServiceAccountOpenLogRepository,
                         KakaoChargeLogRepository kakaoChargeLogRepository,
                         KakaoSenderLogRepository kakaoSenderLogRepository,
                         KakaoReceiverLogRepository kakaoReceiverLogRepository) {
        long userId = 1;
        KakaoServiceAccountOpenLog kakaoServiceAccountOpenLog = kakaoServiceAccountOpenLogRepository.findByUserId(userId);

        if (kakaoServiceAccountOpenLog != null) {
            LocalDateTime accountOpenTime = kakaoServiceAccountOpenLog.getCreateTime();

            List<KakaoReceiverLog> kakaoReceiverLogs = kakaoReceiverLogRepository.findAllByUserIdAndCreateTimeAfter(userId, accountOpenTime);
            List<KakaoSenderLog> kakaoSenderLogs = kakaoSenderLogRepository.findAllByUserIdAndCreateTimeAfter(userId, accountOpenTime);
            List<KakaoChargeLog> kakaoChargeLogs = kakaoChargeLogRepository.findAllByUserIdAndCreateTimeAfter(userId, accountOpenTime);

            kakaoMoneyLogs.add(new KakaoMoneyLog(RuleEnum.ACCOUNT_OPEN, kakaoServiceAccountOpenLog));
            kakaoReceiverLogs.forEach(log -> kakaoMoneyLogs.add(new KakaoMoneyLog(RuleEnum.RECEIVE, log)));
            kakaoSenderLogs.forEach(log -> kakaoMoneyLogs.add(new KakaoMoneyLog(RuleEnum.SEND, log)));
            kakaoChargeLogs.forEach(log -> kakaoMoneyLogs.add(new KakaoMoneyLog(RuleEnum.CHARGE, log)));
        }
    }

    public List<KakaoMoneyLog> getKakaoMoneyLogs() {
        return kakaoMoneyLogs;
    }

    public static List<KakaoMoneyLog> getStaticKakaoMoneyLogs() {
        return staticKakaoMoneyLogs;
    }

    public static KakaoServiceAccountOpenLog getKakaoServiceAccountOpenLog() {
        return kakaoServiceAccountOpenLog;
    }

    public static KakaoChargeLog getKakaoChargeLog() {
        return kakaoChargeLog;
    }

    public static KakaoSenderLog getKakaoSenderLog() {
        return kakaoSenderLog;
    }

    public static KakaoReceiverLog getKakaoReceiverLog() {
        return kakaoReceiverLog;
    }
}
