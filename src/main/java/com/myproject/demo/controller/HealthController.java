package com.myproject.demo.controller;

import com.myproject.demo.action.AddTnxAction;
import com.myproject.demo.action.DeleteTnxAction;
import com.myproject.demo.action.UpdateTnxAction;
import com.myproject.demo.action.UserTnxOverviewAction;
import com.myproject.demo.dto.TnxRequest;
import com.myproject.demo.enums.SplitType;
import com.myproject.demo.service.TnxService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class HealthController {

    @Autowired AddTnxAction addTnxAction;
    @Autowired UpdateTnxAction updateTnxAction;
    @Autowired DeleteTnxAction deleteTnxAction;
    @Autowired UserTnxOverviewAction userTnxOverviewAction;
    @Autowired
    TnxService tnxService;

    @GetMapping("/ping")
    public ResponseEntity<?>  ping() {
        return ResponseEntity.ok("Service is up!");
    }

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        TnxRequest req = TnxRequest.builder()
                .authorId("1")
                .splitType(SplitType.PERCENT)
                .lenderId("1")
                .totalAmount(100.0d)
                .splits(Map.of("1", 10d, "2", 60d, "3", 30d))
                .build();
        log.info("-–----------–––-–----------–––");
        addTnxAction.apply(req);
        logTnxs("1", tnxService.getActiveTnxsForUser("1"));
        log.info("-–----------–––-–----------–––");
        Map<String, Map<String, Double>> activeTnxsOfAllUsers = tnxService.getActiveTnxsOfAllUsers();
        for (String subject: activeTnxsOfAllUsers.keySet()) {
            log.info("User: {}", subject);
            logTnxs(subject, activeTnxsOfAllUsers.get(subject));
        }
        log.info("-–----------–––-–----------–––");
        addTnxAction.apply(TnxRequest.builder()
                .authorId("2")
                .splitType(SplitType.EQUAL)
                .lenderId("2")
                .totalAmount(75.0d)
                .borrowers(List.of("1", "2", "3"))
                .build());
        log.info("-–----------–––-–----------–––");
        activeTnxsOfAllUsers = tnxService.getActiveTnxsOfAllUsers();
        for (String subject: activeTnxsOfAllUsers.keySet()) {
            log.info("User: {}", subject);
            logTnxs(subject, activeTnxsOfAllUsers.get(subject));
        }
        log.info("-–----------–––-–----------–––");
        return null;
    }

    private void logTnxs(String subject, Map<String, Double> tnxs) {
        for (String thirdParty : tnxs.keySet()) {
            double amt = tnxs.get(thirdParty);
            if(amt < 0d)
                log.info("{} owes {} amount : {}", thirdParty, subject, -amt);
            else
                log.info("{} owes {} amount: {}", subject, thirdParty, amt);
        }
    }


}
