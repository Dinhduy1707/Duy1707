package com.example.consumer.request;

import com.example.consumer.dto.PaymentDTO;
import com.example.consumer.dto.ResponseDTO;

public interface RequestApi {
    ResponseDTO postRequestPartner(PaymentDTO paymentDTO);
}
