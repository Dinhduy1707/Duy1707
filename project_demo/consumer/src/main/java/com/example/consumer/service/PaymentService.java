package com.example.consumer.service;

import com.example.consumer.repository.PaymentRepository;
import com.example.consumer.entity.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {

        this.paymentRepository = paymentRepository;
    }

    public Payment saveData(Payment payment) {
        paymentRepository.save(payment);
        return payment;
    }

}
