package com.huseyinaydin.tx.exception;

/**
 * 
 @author Huseyin_Aydin
 @since 1994
 @category Spring Boot canım
 *
 * 
 */

//İSTİSNALAR KAİDEYİ BOZMAZ.!
//https://www.youtube.com/watch?v=_wrtELSwwQY

public class InsufficientAmountException extends RuntimeException {

    public InsufficientAmountException(String msg){
        super(msg);
    }
}
