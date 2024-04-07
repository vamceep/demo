package com.myproject.demo.splitexecutor;

import com.myproject.demo.dto.TnxRequest;
import com.myproject.demo.entity.TnxEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EqualSplitSplitCalculator implements SplitCalculator {

    @Override
    public List<TnxEntity.SplitTnx> calculate(TnxRequest tnxRequest) {
        double totalAmt = tnxRequest.getTotalAmount();
        List<TnxEntity.SplitTnx> splitTnxes = new ArrayList<>();
        int totalPeople = tnxRequest.getBorrowers().size();
        double singleShare = totalAmt/totalPeople;

        for (String userId : tnxRequest.getBorrowers()) {
            if(!userId.equals(tnxRequest.getLenderId())) {
                splitTnxes.add(TnxEntity.SplitTnx.builder()
                        .amount(singleShare)
                        .borrower(userId)
                        .lender(tnxRequest.getLenderId())
                        .build());
            }
        }
        return splitTnxes;
    }
}
