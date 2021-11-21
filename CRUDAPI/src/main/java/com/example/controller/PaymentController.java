package com.example.controller;

import com.example.common.GsonCustom;
import com.example.entity.Payment;
import com.example.model.PaymentDTO;
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
import java.util.ArrayList;
import java.util.List;
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
    public ResponseEntity<PaymentDTO> createPayment(@Valid @RequestBody Payment payment) {

        LOG.info("Add payment: {}", GsonCustom.getInstance().toJson(payment));

        if (!Validator.checkSum(payment)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        List<PaymentDTO> dtoList = new ArrayList<>();
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setMessageType("Success");
        paymentDTO.setRespCode("00");
        dtoList.add(paymentDTO);

        paymentService.saveRedis(payment);
        LOG.info("Add succsess");
        return new ResponseEntity(dtoList, HttpStatus.OK);
    }


}





