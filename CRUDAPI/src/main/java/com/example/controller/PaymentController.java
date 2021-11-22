package com.example.controller;

import com.example.common.GsonCustom;
import com.example.dto.Response;
import com.example.entity.Payment;
import com.example.service.impl.PaymentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

//fixme
@Slf4j
@RestController
@RequestMapping("/api/v1")
public class PaymentController {
    private final PaymentServiceImpl paymentService;

    public PaymentController(PaymentServiceImpl paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("add_payment")
    public Response createPayment(@Valid @RequestBody Payment payment) {
        log.info("Add payment: {}", GsonCustom.getInstance().toJson(payment));
        return paymentService.savePayment(payment);

    }


}





