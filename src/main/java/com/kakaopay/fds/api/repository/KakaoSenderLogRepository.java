package com.kakaopay.fds.api.repository;

import com.kakaopay.fds.api.entity.KakaoSenderLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface KakaoSenderLogRepository extends JpaRepository<KakaoSenderLog, Long> {
    List<KakaoSenderLog> findAllByUserId(@Param("userId") Long userId);
    List<KakaoSenderLog> findAllByUserIdAndCreateTimeAfter(@Param("userId") Long userId, @Param("createTime") LocalDateTime date);
}
