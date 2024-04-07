package com.myproject.demo.controller;

import com.myproject.demo.action.AddTnxAction;
import com.myproject.demo.action.UpdateTnxAction;
import com.myproject.demo.action.DeleteTnxAction;
import com.myproject.demo.dto.TnxRequest;
import com.myproject.demo.dto.AddTnxResponse;
import com.myproject.demo.dto.UpdateTnxRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/crud")
@RequiredArgsConstructor(onConstructor =  @__(@Autowired))
public class SplitwiseCrudController {
    AddTnxAction addTnxAction;

    UpdateTnxAction updateTnxAction;

    DeleteTnxAction deleteTnxAction;

    @PutMapping(path ="/add-tnx")
    public ResponseEntity<AddTnxResponse> createTnx(@RequestBody TnxRequest transactionRequest) {
        return addTnxAction.apply(transactionRequest);
    }

    @PostMapping(path ="/update-tnx")
    public ResponseEntity<?> updateTransaction(@RequestBody UpdateTnxRequest transactionRequest) {
        return ResponseEntity.ok(updateTnxAction.apply(transactionRequest));
    }

    @DeleteMapping(path ="/delete")
    public ResponseEntity<?> deleteTransaction(@RequestParam String userId, @RequestParam int tnxId) {
        return ResponseEntity.ok(deleteTnxAction.apply(userId, tnxId));
    }
}
