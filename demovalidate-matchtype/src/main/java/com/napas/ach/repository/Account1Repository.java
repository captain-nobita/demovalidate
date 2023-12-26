/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Component.java to edit this template
 */
package com.napas.ach.repository;

import com.napas.ach.pojo1.DirectDebitRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 *
 * @author nguye
 */
@Component
public class Account1Repository {
    private Map<String, BigDecimal> accounts;

    public Account1Repository() {
        accounts = new HashMap<>();
        accounts.put("A123456789", new BigDecimal("10000000"));
        accounts.put("A123456789", new BigDecimal("20000000"));
    }
    
    public BigDecimal getCurrentAmount(DirectDebitRequest msg) {
        return accounts.get(msg.getAccountNumber());
    }
    
    public BigDecimal doDebit(DirectDebitRequest msg) {
        BigDecimal currentAmount = accounts.get(msg.getAccountNumber());
        return currentAmount.subtract(msg.getAmount());
    }
}
