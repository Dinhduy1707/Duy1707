package com.example.producer.service;

import com.example.producer.dto.PaymentDTO;
import com.example.producer.dto.ResponseDTO;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public interface PaymentService {
    ResponseDTO validateAndSaveDataPayment(PaymentDTO paymentDto) throws InterruptedException, IOException;
}
