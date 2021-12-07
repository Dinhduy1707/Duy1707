package com.example.consumer.dto;

import com.example.consumer.comstans.StatusMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {
    private int code;
    private String message;
    private Object body;
    public ResponseDTO(HttpStatus httpStatus, Object body){
        this.body=body;
        if(httpStatus==HttpStatus.OK){
            this.message= StatusMessage.SUCCESS.getMessage();
            this.code=StatusMessage.SUCCESS.getValue();
        }else {
            this.message= StatusMessage.QUESTIONABLE_TRANSACTIONS.getMessage();
            this.code=StatusMessage.QUESTIONABLE_TRANSACTIONS.getValue();
        }
    }

    @Override
    public String toString() {
        return "ResponsePartnerDTO{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", body=" + body +
                '}';
    }
}
