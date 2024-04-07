package com.myproject.demo.service;

import com.myproject.demo.dto.TnxRequest;
import com.myproject.demo.dto.UpdateTnxRequest;
import com.myproject.demo.entity.TnxEntity;
import com.myproject.demo.enums.TnxStatus;
import com.myproject.demo.factory.SplitExecutorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TnxService {
    @Autowired
    UserService userService;

    @Autowired
    SplitExecutorFactory splitExecutorFactory;

    private static final ArrayList<TnxEntity> tnx;
    static {
        tnx = new ArrayList<>();
    }

    public int addTransaction(TnxRequest request) {
        int id = tnx.size();
        tnx.add(TnxEntity.builder()
                .tnxId(id)
                .author(request.getAuthorId())
                .lender(request.getLenderId())
                .date(new Date())
                .splits(splitExecutorFactory.getExecutor(request.getSplitType()).execute(request))
                .status(TnxStatus.ACTIVE)
                .build());
        return id;
    }

    public void updateTransaction(UpdateTnxRequest request) {
        tnx.get(request.getOldTnxId()).setStatus(TnxStatus.INACTIVE);
        tnx.add(TnxEntity.builder()
                .tnxId(tnx.size())
                .author(request.getAuthorId())
                .lender(request.getLenderId())
                .date(new Date())
                .splits(splitExecutorFactory.getExecutor(request.getSplitType()).execute(request))
                .status(TnxStatus.ACTIVE)
                .build());
    }

    public void deleteTransaction(int tnxId, String authorId) {
        tnx.get(tnxId).setStatus(TnxStatus.DELETED);
        tnx.get(tnxId).setMetaData(TnxEntity.MetaData.builder()
                .deletedBy(authorId)
                .build());

    }

    public Map<String, Double> getActiveTnxsForUser(String userId) {
        Map<String, Double> usersAndAmount = new HashMap<>();
        List<TnxEntity> activeTransactions = tnx.stream().filter(x -> x.getStatus().equals(TnxStatus.ACTIVE)).toList();
        for (TnxEntity activeTransaction : activeTransactions) {
            List<TnxEntity.SplitTnx> splits = activeTransaction.getSplits().stream()
                    .filter(x -> x.getBorrower().equals(userId) || x.getLender().equals(userId)).toList();
            for (TnxEntity.SplitTnx splitTnx : splits) {
                if(splitTnx.getLender().equals(userId)) {
                    double balance = usersAndAmount.getOrDefault(splitTnx.getBorrower(), 0d);
                    usersAndAmount.put(splitTnx.getBorrower(), balance - splitTnx.getAmount());
                }
                else {
                    double balance = usersAndAmount.getOrDefault(splitTnx.getLender(), 0d);
                    usersAndAmount.put(splitTnx.getLender(), balance + splitTnx.getAmount());
                }
            }
        }
        return usersAndAmount;
    }

    public Map<String,Map<String, Double>> getActiveTnxsOfAllUsers() {
        Map<String,Map<String, Double>> overview = new HashMap<>();

        for (String userId : userService.getUsers()) {
            Map<String, Double> activeTnxsForUser = getActiveTnxsForUser(userId);
            if(!activeTnxsForUser.isEmpty()) {
                overview.put(userId, activeTnxsForUser);
            }
        }
        return overview;
    }

    public TnxEntity getTnxWithId(int transactionId) {
        return tnx.stream().filter(x -> x.getTnxId() == transactionId).findFirst().get();
    }
}
