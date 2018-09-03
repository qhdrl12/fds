package com.kakaopay.fds.api.service;

import com.kakaopay.fds.api.response.FdsResponse;

public interface FdsService {
    FdsResponse search(Long userId);
}
