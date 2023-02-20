package com.huseyinaydin.tx.utils;

import java.util.HashMap;
import java.util.Map;

import com.huseyinaydin.tx.exception.InsufficientAmountException;

/**
 * 
 @author Huseyin_Aydin
 @since 1994
 @category Spring Boot canım
 *
 * 
 */

public class PaymentUtils {

    private static Map<String, Double> paymentMap = new HashMap<>();

    static {
        paymentMap.put("acc1", 12000.0);
        paymentMap.put("acc2", 10000.0);
        paymentMap.put("acc3", 5000.0);
        paymentMap.put("acc4", 8000.0);
    }


    public static boolean validateCreditLimit(String accNo, double paidAmount) {
        if (paidAmount > paymentMap.get(accNo)) {
            throw new InsufficientAmountException("Yetersiz bakiye cnm (: || insufficient fund..!");
        } else {
            return true;
        }
    }
}
