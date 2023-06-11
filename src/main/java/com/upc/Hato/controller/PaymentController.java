package com.upc.Hato.controller;

import com.upc.Hato.exception.ValidationException;
import com.upc.Hato.model.Payment;
import com.upc.Hato.repository.PaymentRepository;
import com.upc.Hato.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hato")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    private final PaymentRepository paymentRepository;
    public PaymentController(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    //EndPoint: http://localhost:8080/api/hato/payments
    //Method: GET
    @Transactional(readOnly = true)
    @RequestMapping("/payments")
    public ResponseEntity<List<Payment>> getAllPayments() {
        return new ResponseEntity<List<Payment>>(paymentRepository.findAll(), HttpStatus.OK);
    }

    //EndPoint: http://localhost:8080/api/hato/payments/filterByPaymentType
    //Method: GET
    @Transactional(readOnly = true)
    @RequestMapping("/payments/filterByPaymentType")
    public ResponseEntity<List<Payment>> getPaymentsByPaymentType(@RequestParam(name = "paymentType") String paymentType) {
        return new ResponseEntity<List<Payment>>(paymentRepository.findByPaymentType(paymentType), HttpStatus.OK);
    }

    //EndPoint: http://localhost:8080/api/hato/payments
    //Method: POST
    @Transactional
    @PostMapping("/payments")
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
        existsByCardNumberAndCardHolder(payment);
        validatePayment(payment);
        return new ResponseEntity<>(paymentService.createPayment(payment), HttpStatus.CREATED);
    }

    public void validatePayment(Payment payment) {
        if (payment.getPaymentType() == null || payment.getPaymentType().trim().isEmpty()) {
            throw new ValidationException("The payment's type is required");
        }
        if (payment.getPaymentType().length() > 22) {
            throw new ValidationException("The payment's type does not exceed 22 characters");
        }
        if (payment.getCardNumber() == null || payment.getCardNumber().trim().isEmpty()) {
            throw new ValidationException("The payment's card number is required");
        }
        if (payment.getCardNumber().length() > 16) {
            throw new ValidationException("The payment's card number does not exceed 22 characters");
        }
        if (payment.getCardHolder() == null || payment.getCardHolder().trim().isEmpty()) {
            throw new ValidationException("The payment's card holder is required");
        }
        if (payment.getExpirationDate() == null || payment.getExpirationDate().trim().isEmpty()) {
            throw new ValidationException("The payment's expiration date is required");
        }
        if (payment.getExpirationDate().length() > 5) {
            throw new ValidationException("The payment's expiration date does not exceed 5 characters (MM/YY)");
        }
        if (payment.getCvv() == null || payment.getCvv().trim().isEmpty()) {
            throw new ValidationException("The payment's security code is required");
        }
        if (payment.getCvv().length() > 3) {
            throw new ValidationException("The payment's security must be 3 characters");
        }
    }
    private void existsByCardNumberAndCardHolder(Payment payment) {
        if (paymentRepository.existsByCardNumberAndCardHolder(payment.getCardNumber(), payment.getCardHolder())) {
            throw new ValidationException("The card number and card holder already exist");
        }
    }
}
