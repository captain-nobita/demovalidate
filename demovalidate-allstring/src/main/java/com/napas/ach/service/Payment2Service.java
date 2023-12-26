/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package com.napas.ach.service;

import com.napas.ach.pojo2.DirectDebitRequest;
import com.napas.ach.pojo2.DirectDebitResponse;
import com.napas.ach.model.MXResponse;
import com.napas.ach.repository.Account2Repository;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author nguye
 */
@Service
public class Payment2Service {
    @Autowired
    private Account2Repository repo;
    
    public ResponseEntity<?> doDebitAccount(DirectDebitRequest msg) {
        BigDecimal subtractingAmount;
        
        //Kiểm tra xem chuỗi đầu vào có phải là số thập phân hay không, nếu không phải thì không thể convert sang Decimal và báo lỗi
        try {
            subtractingAmount = new BigDecimal(msg.getAmount());
        } catch(Exception ex) {
            return ResponseEntity.badRequest().body(new MXResponse(999, "Loi Amount khong phai la so Decimal"));
        }
        
        //Kiểm tra xem số tiền có trong khoảng cho phép không
        if(subtractingAmount.compareTo(new BigDecimal(1000)) < 0 || subtractingAmount.compareTo(new BigDecimal(499999999)) > 0) {
            return ResponseEntity.badRequest().body(new MXResponse(999, "Loi Amount khong nam trong khoang 1000 va 499999999"));
        }
        msg.setAmountInDecimal(subtractingAmount);
        
        //Kiểm tra xem số tài khoản có đúng định dạng không
        if(!msg.getAccountNumber().matches("^[A-Z0-9]{8,10}$")) {
            return ResponseEntity.badRequest().body(new MXResponse(999, "So tai khoan phai la chuoi chi gom so va chu hoa, co do dai tu 8 den 10 ky tu"));
        }
        
        BigDecimal currentAmount = repo.getCurrentAmount(msg);
        if(subtractingAmount.compareTo(currentAmount) > 0) {
            return ResponseEntity.ok(new MXResponse(100, "So tien rut ra lon hon so tien hien co"));
        }
        
        BigDecimal remainAmount = repo.doDebit(msg);
        return ResponseEntity.ok(new DirectDebitResponse(remainAmount.toString(), "98kisfyw8wisfsf"));
    }
}
