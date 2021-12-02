package com.example.producer.service.Impl;

import com.example.producer.dto.PaymentDTO;
import com.example.producer.dto.ResponseDTO;
import com.example.producer.dto.ResultDTO;
import com.example.producer.exception.TimeOutException;
import com.example.producer.service.PaymentService;
import com.example.producer.service.RabbitService;
import com.example.producer.validate.PaymentValidate;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService {
    private final PaymentValidate paymentValidate;
    private final RabbitService rabbitService;

    public PaymentServiceImpl(PaymentValidate paymentValidate, RabbitService rabbitService) {
        this.rabbitService = rabbitService;
        this.paymentValidate = paymentValidate;
    }

    @Override
    public ResponseDTO validateAndSaveDataPayment(PaymentDTO paymentDto) {

        log.info("Service to save payment: {}", paymentDto.toString());

        paymentValidate.validate(paymentDto);
        paymentValidate.isTokenKeyExisted(paymentDto.getTokenKey());

        ResultDTO responsePartDTO = rabbitService.sendDataPayment(paymentDto);
        if (responsePartDTO == null) {
            throw new TimeOutException();
        }
        ResponseDTO resultDTO = ResponseDTO.builder()
                .code(responsePartDTO.getCode())
                .message(responsePartDTO.getMessage())
                .checkSum(paymentDto.getCheckSum())
                .addValue(paymentDto.getAddValue())
                .responseId(UUID.randomUUID().toString())
                .build();
        log.info("Saved payment successfully: {}", paymentDto.toString());
        return resultDTO;

    }


}
