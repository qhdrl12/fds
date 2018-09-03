package com.kakaopay.fds.core.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DateTest {

    private LocalDateTime start = LocalDateTime.now();
    private LocalDateTime end = start.plusDays(2);

    @Test
    public void 날짜비교_성공() {
        LocalDateTime dateTime = LocalDateTime.now();
        boolean validDateTime = DateUtil.isValidDateTime(dateTime.plusHours(1), start, end);

        assertThat(validDateTime).isTrue();
    }

    @Test
    public void 날짜비교_실패() {
        LocalDateTime dateTime = LocalDateTime.now();
        boolean validDateTime = DateUtil.isValidDateTime(dateTime.minusHours(5), start, end);

        assertThat(validDateTime).isFalse();
    }
}
