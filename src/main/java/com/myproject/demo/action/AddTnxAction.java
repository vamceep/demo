package com.myproject.demo.action;

import com.myproject.demo.dto.AddTnxResponse;
import com.myproject.demo.dto.TnxRequest;
import com.myproject.demo.service.NotificationService;
import com.myproject.demo.service.TnxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
public class AddTnxAction  {
    @Autowired
    TnxService tnxService;
    @Autowired
    NotificationService notificationService;

    public ResponseEntity<AddTnxResponse> apply(TnxRequest request) {
        tnxService.addTransaction(request);
        String lenderId = request.getLenderId();
        for (String userId : request.getSplits().keySet()) {
            double amount = request.getSplits().get(userId);
            notificationService.notify(userId, "");
        }
        return ResponseEntity.ok().body(null);
    }
}
