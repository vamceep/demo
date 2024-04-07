package com.myproject.demo.action;

import com.myproject.demo.service.NotificationService;
import com.myproject.demo.service.TnxService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeleteTnxAction {
    TnxService tnxService;
    NotificationService notificationService;

    public ResponseEntity<?> apply(String authorId, int tnxId) {
//        tnxOverviewService.getOverview(tnxId);
        tnxService.deleteTransaction(tnxId, authorId);
//        todo: notify users about the transaction deletion.
//        for (String userId : request.getSplits().keySet()) {
//            String message = MessageFormat.format("Transaction deleted by : {0}",
//                    request.getAuthorId());
//            notificationService.notify(userId, message);
//        }
        return ResponseEntity.ok().body(null);

    }
}
