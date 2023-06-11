package com.upc.Hato.service.Impl;

import com.upc.Hato.model.Payment;
import com.upc.Hato.repository.PaymentRepository;
import com.upc.Hato.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }
}
