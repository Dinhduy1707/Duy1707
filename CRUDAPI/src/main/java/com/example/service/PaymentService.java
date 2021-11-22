package com.example.service;

import com.example.dto.Response;
import com.example.entity.Payment;

public interface PaymentService {
   Response savePayment(Payment payment);
}
