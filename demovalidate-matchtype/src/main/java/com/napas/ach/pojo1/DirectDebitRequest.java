/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.napas.ach.pojo1;

import java.math.BigDecimal;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author nguye
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DirectDebitRequest {
    @Pattern(regexp="^[A-Z0-9]{8,10}$",message="So tai khoan phai la chuoi chi gom so va chu hoa, co do dai tu 8 den 10 ky tu")
    private String accountNumber;
    
    @Min(1000L)
    @Max(499999999L)
    private BigDecimal amount;
}
