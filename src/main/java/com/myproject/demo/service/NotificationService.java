package com.myproject.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationService {
    public void notify(String userId, String message) {
        log.info("to :{}, message: {}",userId, message);
    }
}
