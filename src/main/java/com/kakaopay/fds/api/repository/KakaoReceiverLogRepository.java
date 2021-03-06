package com.kakaopay.fds.api.repository;

import com.kakaopay.fds.api.entity.KakaoReceiverLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface KakaoReceiverLogRepository extends JpaRepository<KakaoReceiverLog, Long> {
    List<KakaoReceiverLog> findAllByUserId(@Param("userId") Long userId);
    List<KakaoReceiverLog> findAllByUserIdAndCreateTimeAfter(@Param("userId") Long userId, @Param("createTime") LocalDateTime date);
}
