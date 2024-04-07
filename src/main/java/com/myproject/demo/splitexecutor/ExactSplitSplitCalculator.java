package com.myproject.demo.splitexecutor;

import com.myproject.demo.dto.TnxRequest;
import com.myproject.demo.entity.TnxEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExactSplitSplitCalculator implements SplitCalculator, SplitValidator {

    @Override
    public List<TnxEntity.SplitTnx> calculate(TnxRequest tnxRequest) {
        List<TnxEntity.SplitTnx> splitTnxes = new ArrayList<>();
        if(!isValid(tnxRequest)) {
            throw new RuntimeException("Percentage sum is not valid");
        }

        for (String userId : tnxRequest.getSplits().keySet()) {
            if(!userId.equals(tnxRequest.getLenderId())) {
                splitTnxes.add(TnxEntity.SplitTnx.builder()
                        .amount(tnxRequest.getSplits().get(userId))
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
        for (Double amt: tnxRequest.getSplits().values()) {
            total += amt;
        }
        return total == tnxRequest.getTotalAmount();
    }
}
