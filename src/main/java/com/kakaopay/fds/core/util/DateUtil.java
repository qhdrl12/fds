package com.kakaopay.fds.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class DateUtil {

    private static Logger log = LoggerFactory.getLogger(DateUtil.class);

    public static boolean isValidDateTime(LocalDateTime date, LocalDateTime start, LocalDateTime end) {
        log.debug("date {}, start {}, end {}", date, start, end);

        return (date.isAfter(start) || date.isEqual(start))
                && ((date.isBefore(end)) || date.isEqual(end));
    }
}
