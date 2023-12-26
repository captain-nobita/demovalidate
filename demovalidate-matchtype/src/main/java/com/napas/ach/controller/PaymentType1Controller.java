/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.napas.ach.controller;

import com.napas.ach.pojo1.DirectDebitRequest;
import com.napas.ach.model.MXResponse;
import com.napas.ach.service.Payment1Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author nguye
 */
@RestController
@RequestMapping("/api/matchtype")
public class PaymentType1Controller {
    @Autowired
    private Payment1Service service;
    
    @PostMapping
    public ResponseEntity<?> doDebitAccount(@Valid @RequestBody DirectDebitRequest msg) {
        return service.doDebitAccount(msg);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class) // exception handled
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleValidationExceptions(
            MethodArgumentNotValidException e
    ) {
        StringBuilder listError = new StringBuilder();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            listError.append(String.format("Field:'%s' error:'%s'", fieldName, errorMessage));
        });
        return ResponseEntity.badRequest().body(new MXResponse(999, listError.toString()));
    }
    
}
