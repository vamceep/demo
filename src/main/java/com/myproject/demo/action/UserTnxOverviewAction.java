package com.myproject.demo.action;

import com.myproject.demo.service.TnxService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserTnxOverviewAction {
    TnxService tnxService;


    public ResponseEntity<?> apply(String userId) {
        return ResponseEntity.ok(tnxService.getActiveTnxsForUser(userId));
    }
}
