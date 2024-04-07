package com.myproject.demo.splitexecutor;

import com.myproject.demo.dto.TnxRequest;
import com.myproject.demo.entity.TnxEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PercentageSplitSplitCalculator implements SplitCalculator, SplitValidator {

    @Override
    public List<TnxEntity.SplitTnx> calculate(TnxRequest tnxRequest) {
        double totalAmt = tnxRequest.getTotalAmount();
        List<TnxEntity.SplitTnx> splitTnxes = new ArrayList<>();
        if(!isValid(tnxRequest)) {
            throw new RuntimeException("Percentage sum is not valid");
        }

        for (String userId : tnxRequest.getSplits().keySet()) {
            if(!userId.equals(tnxRequest.getLenderId())) {
                double percent = tnxRequest.getSplits().get(userId);
                double amt = (totalAmt * percent)/100;
                splitTnxes.add(TnxEntity.SplitTnx.builder()
                        .amount(amt)
                        .borrower(userId)
                        .lender(tnxRequest.getLenderId())
                        .build());
            }
        }
        return splitTnxes;
    }

    @Override
    public boolean isValid(TnxRequest tnxRequest) {
        double total = 0;
        for (Double value : tnxRequest.getSplits().values()) {
            total += value;
        }
        return total == 100d;
    }
}
