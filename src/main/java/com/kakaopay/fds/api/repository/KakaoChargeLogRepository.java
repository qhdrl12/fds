package com.kakaopay.fds.api.repository;

import com.kakaopay.fds.api.entity.KakaoChargeLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface KakaoChargeLogRepository extends JpaRepository<KakaoChargeLog, Long> {
    List<KakaoChargeLog> findAllByUserId(@Param("userId") Long userId);
    List<KakaoChargeLog> findAllByUserIdAndCreateTimeAfter(@Param("userId") Long userId, @Param("createTime") LocalDateTime date);
}
