package com.myproject.demo.splitexecutor;

import com.myproject.demo.dto.TnxRequest;
import com.myproject.demo.entity.TnxEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EqualSplitExecutor implements Executor {

    @Override
    public List<TnxEntity.SplitTnx> execute(TnxRequest tnxRequest) {
        double totalAmt = tnxRequest.getTotalAmount();
        List<TnxEntity.SplitTnx> splitTnxes = new ArrayList<>();
        int totalPeople = tnxRequest.getSplits().size();
        double singleShare = totalAmt/totalPeople;

        for (String userId : tnxRequest.getSplits().keySet()) {
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
