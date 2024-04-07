package com.myproject.demo.service;

import com.myproject.demo.entity.TnxEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@Slf4j
public class NotificationService {
    @Autowired
    TnxService tnxService;
    public void notify(int transactionId) {
        TnxEntity tnx = tnxService.getTnxWithId(transactionId);
        for (TnxEntity.SplitTnx split : tnx.getSplits()) {
            log.info("to :{}, message: {}",split.getBorrower(), MessageFormat.format("you(user_{0} owe {1} amount: {2} ",
                    split.getBorrower(), split.getLender(), split.getAmount()));
        }
    }

    public void notify(String userId, String message) {
        log.info("to :{}, message: {}",userId, message);
    }
}
