package com.example.controller;

import com.example.common.GsonCustom;
import com.example.entity.Payment;
import com.example.repository.PaymentRepository;
import com.example.valida.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
//fixme

@RestController
@RequestMapping("/api/v1")
public class PaymentController {
    private static final Logger LOG = LogManager.getLogger(PaymentController.class);
    private static PaymentRepository paymentService;
    public PaymentController(PaymentRepository paymentService) {
        this.paymentService = paymentService;
    }

    @RequestMapping(value = "/add_payment",
            method = RequestMethod.POST)
    public ResponseEntity createPayment(@Valid @RequestBody Payment payment) {
        LOG.info("Add payment: {}", GsonCustom.getInstance().toJson(payment));
        if (!Validator.checkSum(payment)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        paymentService.saveRedis(payment);
        LOG.info("Add succsess");
        return new ResponseEntity(payment, HttpStatus.OK);
    }

}





