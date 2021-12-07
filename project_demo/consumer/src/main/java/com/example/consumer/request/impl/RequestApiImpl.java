package com.example.consumer.request.impl;

import com.example.consumer.common.HttpApi;
import com.example.consumer.dto.PaymentDTO;
import com.example.consumer.dto.ResponseDTO;
import com.example.consumer.request.RequestApi;
import com.example.consumer.utils.HttpClient;
import org.springframework.stereotype.Component;

@Component
public class RequestApiImpl  implements RequestApi {
    @Override
    public ResponseDTO postRequestPartner(PaymentDTO payDTO) {
        return HttpClient.postRequestApi(payDTO, HttpApi.URL_API_XPARTNER);
    }
}
