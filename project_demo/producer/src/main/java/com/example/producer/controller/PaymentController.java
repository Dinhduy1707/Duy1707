package com.example.producer.controller;

import com.example.producer.common.LogCommon;
import com.example.producer.dto.PaymentDTO;
import com.example.producer.service.PaymentService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.UUID;

@Log4j2
@RestController
@RequestMapping("/api/v1")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping(value = "/payment")
    public ResponseEntity<?> checkAndSendDataToQueue(@RequestBody PaymentDTO payDTO) throws InterruptedException, IOException {
        ThreadContext.put(LogCommon.token, UUID.randomUUID().toString().replaceAll("-", ""));
        return new ResponseEntity(paymentService.validateAndSaveDataPayment(payDTO), HttpStatus.OK);
    }
}