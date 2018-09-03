package com.kakaopay.fds.core.util;

import java.util.function.Predicate;

public class PredicateUtil {

    public static Predicate<Long> isGreaterThan(Long threshold) {
        return amount -> amount >= threshold;
    }

    public static Predicate<Long> isLessThan(Long threshold) {
        return amount -> amount <= threshold;
    }
}
