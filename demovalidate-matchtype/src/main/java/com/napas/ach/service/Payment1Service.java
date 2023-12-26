/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package com.napas.ach.service;

import com.napas.ach.pojo1.DirectDebitRequest;
import com.napas.ach.pojo1.DirectDebitResponse;
import com.napas.ach.model.MXResponse;
import com.napas.ach.repository.Account1Repository;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author nguye
 */
@Service
public class Payment1Service {
    @Autowired
    private Account1Repository repo;
    
    public ResponseEntity<?> doDebitAccount(DirectDebitRequest msg) {
        BigDecimal currentAmount = repo.getCurrentAmount(msg);
        
        BigDecimal subtractingAmount = msg.getAmount();
        if(subtractingAmount.compareTo(currentAmount) > 0) {
            return ResponseEntity.ok(new MXResponse(100, "So tien rut ra lon hon so tien hien co"));
        }
        
        BigDecimal remainAmount = repo.doDebit(msg);
        return ResponseEntity.ok(new DirectDebitResponse(remainAmount, "98kisfyw8wisfsf"));
    }
}
