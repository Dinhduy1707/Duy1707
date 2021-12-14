package com.example.consumer.utils;

import com.example.consumer.dto.ResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
@Log4j2
public class HttpClient {



    public static ResponseDTO postRequestApi(Object object, String urlApi) {
        log.info("Post request data  to Api : {}", urlApi);
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
            headers.setContentType(MediaType.APPLICATION_JSON);
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<Object> requestBody = new HttpEntity<>(object, headers);
            ResponseEntity<Object> result = restTemplate.postForEntity(urlApi, requestBody, Object.class);
            ResponseDTO responseDTO = new ResponseDTO(result.getStatusCode(), result.getBody());
            log.info("Call api success, status:{}", result.getStatusCode());
            return responseDTO;
        } catch (Exception e) {
            log.error("Error! Call api {} fail exception:{}", urlApi, e);
        }
        return null;
    }

}
