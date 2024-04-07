package com.myproject.demo.action;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myproject.demo.dto.AddTnxResponse;
import com.myproject.demo.dto.TnxRequest;
import com.myproject.demo.service.NotificationService;
import com.myproject.demo.service.TnxService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
@Slf4j
public class AddTnxAction  {
    @Autowired
    TnxService tnxService;
    @Autowired
    NotificationService notificationService;

    public ResponseEntity<AddTnxResponse> apply(TnxRequest request) {
        logRequest(request);
        int tnxId = tnxService.addTransaction(request);
        notificationService.notify(tnxId);

        return ResponseEntity.ok().body(null);
    }

    private static void logRequest(TnxRequest request) throws RuntimeException {
        try {
            log.info("Adding Transaction: {}", new ObjectMapper().writeValueAsString(request));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
