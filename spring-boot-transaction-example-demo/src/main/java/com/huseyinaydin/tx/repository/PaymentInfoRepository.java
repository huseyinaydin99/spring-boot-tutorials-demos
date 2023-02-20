package com.huseyinaydin.tx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.huseyinaydin.tx.entity.PaymentInfo;

/**
 * 
 @author Huseyin_Aydin
 @since 1994
 @category Spring Boot canım
 *
 * 
 */

public interface PaymentInfoRepository extends JpaRepository<PaymentInfo,String> {
}
