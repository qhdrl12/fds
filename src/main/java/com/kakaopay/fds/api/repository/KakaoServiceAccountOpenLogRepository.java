package com.kakaopay.fds.api.repository;

import com.kakaopay.fds.api.entity.KakaoServiceAccountOpenLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface KakaoServiceAccountOpenLogRepository extends JpaRepository<KakaoServiceAccountOpenLog, Long> {
    KakaoServiceAccountOpenLog findByUserId(@Param("user_id") Long userId);
}
