package com.upc.Hato.repository;

import com.upc.Hato.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
     List<Payment> findByPaymentType(String paymentType);
     boolean existsByCardNumberAndCardHolder(String cardNumber, String cardHolder);
}