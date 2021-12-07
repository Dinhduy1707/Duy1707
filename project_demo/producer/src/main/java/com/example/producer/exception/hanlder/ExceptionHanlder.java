package com.example.producer.exception.hanlder;

import com.example.producer.constant.ConstansPayment;
import com.example.producer.exception.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHanlder {
    @ExceptionHandler(ApiBlankException.class)
    public ErrorResponse handlerApiIdBlankException(ApiBlankException ex) {
        return new ErrorResponse(ConstansPayment.API_ID_BLANK.getValue(), ex.getMessage());
    }

    @ExceptionHandler(OrderCodeException.class)
    public ErrorResponse handlerOrderCodeBlankException(OrderCodeException ex) {
        return new ErrorResponse(ConstansPayment.ORDER_CODE_BLANK.getValue(), ex.getMessage());
    }

    @ExceptionHandler(PaymentDateException.class)
    public ErrorResponse handlerPayDateWrongFormatException(PaymentDateException ex) {
        return new ErrorResponse(ConstansPayment.PAY_DATE_WRONG_FORMAT.getValue(), ex.getMessage());
    }

    @ExceptionHandler(PromotionCodeException.class)
    public ErrorResponse handlerPromotionCodeNullException(PromotionCodeException ex) {
        return new ErrorResponse(ConstansPayment.PROMOTION_CODE_NULL.getValue(), ex.getMessage());
    }

    @ExceptionHandler(RealAmountException.class)
    public ErrorResponse handlerRealAmountMistakeException(RealAmountException ex) {
        return new ErrorResponse(ConstansPayment.REAL_AMOUNT_MISTAKE.getValue(), ex.getMessage());
    }

    @ExceptionHandler(TokenKeyException.class)
    public ErrorResponse handlerTokenKeyExistsException(TokenKeyException ex) {
        return new ErrorResponse(ConstansPayment.TOKEN_KEY_EXISTS.getValue(), ex.getMessage());
    }
    @ExceptionHandler(TimeOutException.class)
    public ErrorResponse handlerTimeOutReplyException(TimeOutException ex) {
        return new ErrorResponse(ConstansPayment.MAINTENANCE_SYSTEM.getValue(), ex.getMessage());
    }

}
