package com.myproject.demo.action;

import com.myproject.demo.dto.UpdateTnxRequest;
import com.myproject.demo.service.NotificationService;
import com.myproject.demo.service.TnxService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
public class UpdateTnxAction {
    TnxService tnxService;
    NotificationService notificationService;

    public ResponseEntity<?> apply(UpdateTnxRequest request) {
        tnxService.updateTransaction(request);
        String lenderId = request.getLenderId();
        for (String userId : request.getSplits().keySet()) {
            double amount = request.getSplits().get(userId);
            String message = MessageFormat.format("Transaction update by : {0}. You owe {1} amount: {2}- ",
                    request.getAuthorId(), lenderId, amount);
            notificationService.notify(userId, message);
        }
        return ResponseEntity.ok().body(null);
    }
}
