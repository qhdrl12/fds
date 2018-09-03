package com.kakaopay.fds.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static java.util.stream.Collectors.joining;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FdsResponse {
    private Long user_id;
    private Boolean is_fraud;
    private String rule;

    public FdsResponse(Long user_id) {
        this.user_id = user_id;
    }

    public void setIs_fraud(boolean is_fraud) {
        this.is_fraud = is_fraud;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public void setRule(List<String> rule) {
        this.is_fraud = rule.size() > 0;
        this.rule = String.join(",", rule);
    }
}
