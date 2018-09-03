package com.kakaopay.fds.api.controller;

import com.kakaopay.fds.api.response.FdsResponse;
import com.kakaopay.fds.api.service.FdsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("v1")
public class FdsControllerV1 {

    private FdsService fdsService;

    @Autowired
    public FdsControllerV1(FdsService fdsService) {
        this.fdsService = fdsService;
    }

    @GetMapping("fraud/{user_id}")
    public ResponseEntity<FdsResponse> get(@PathVariable("user_id") long userId) {
        log.info("user id {}", userId);

        return new ResponseEntity<>(this.fdsService.search(userId), HttpStatus.OK);
    }
}
