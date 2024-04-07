package com.myproject.demo.controller;

import com.myproject.demo.action.UserTnxOverviewAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/overview")
public class GetTnxOverviewController {
    @Autowired
    UserTnxOverviewAction userTnxOverviewAction;
    @GetMapping(path = "{userId}")
    public ResponseEntity<?> getUserTnxOverview(@PathVariable("userId") String userId) {
        return userTnxOverviewAction.apply(userId);
    }
}
