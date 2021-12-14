package com.example.producer.service;

import com.example.producer.dto.PaymentDTO;
import com.example.producer.dto.ResponseDTO;
import com.example.producer.dto.ResponsePartDTO;

public interface RabbitService {
    ResponsePartDTO sendDataPayment(PaymentDTO paymentDTO);
}
