package com.example.producer.service;

import com.example.producer.dto.PaymentDTO;
import com.example.producer.dto.ResultDTO;

public interface RabbitService {
    ResultDTO sendDataPayment(PaymentDTO paymentDTO);
}
