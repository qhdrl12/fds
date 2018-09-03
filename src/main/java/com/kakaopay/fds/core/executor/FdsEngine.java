package com.kakaopay.fds.core.executor;

import com.kakaopay.fds.core.dto.KakaoMoneyLog;
import com.kakaopay.fds.core.rule.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class FdsEngine {
    private static Logger log = LoggerFactory.getLogger(FdsEngine.class);

    private List<Rule> rules = new ArrayList<>();
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    public FdsEngine() {
    }

    public FdsEngine(List<Rule> rules) {
        this.rules = rules;
    }

    public void registerRule(Rule rule) {
        if (!rules.contains(rule)) {
            rules.add(rule);
        }
    }

    public List<String> match(List<KakaoMoneyLog> kakaoMoneyLogs) {
        List<String> results = new ArrayList<>();

        try {
            List<Future<String>> ruleSets = rules.stream()
                    .map(rule -> executorService.submit(() ->
                            (rule.isMatch(kakaoMoneyLogs)) ? rule.getClass().getSimpleName() : null))
                    .collect(Collectors.toList());

            for (Future<String> ruleSet : ruleSets) {
                String rs = ruleSet.get();
                if (rs != null) {
                    results.add(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }

    @Override
    protected void finalize() throws Throwable {
        executorService.shutdownNow();
        super.finalize();
    }
}
