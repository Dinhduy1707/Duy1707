package com.example.producer.exception.hanlder;


import com.example.producer.constant.ConstantPayment;
import com.example.producer.exception.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerException {

@org.springframework.web.bind.annotation.ExceptionHandler(ApiBlankException.class)
public ErrorResponse handlerApiIdBlankException(ApiBlankException ex){
        return new ErrorResponse(ConstantPayment.API_ID_BLANK.getValue(),ex.getMessage());
        }

@org.springframework.web.bind.annotation.ExceptionHandler(OrderCodeException.class)
public ErrorResponse handlerOrderCodeBlankException(OrderCodeException ex){
        return new ErrorResponse(ConstantPayment.ORDER_CODE_BLANK.getValue(),ex.getMessage());
        }

@org.springframework.web.bind.annotation.ExceptionHandler(PaymentDateException.class)
public ErrorResponse handlerPayDateWrongFormatException(PaymentDateException ex){
        return new ErrorResponse(ConstantPayment.PAY_DATE_WRONG_FORMAT.getValue(),ex.getMessage());
        }

@org.springframework.web.bind.annotation.ExceptionHandler(PromotionCodeException.class)
public ErrorResponse handlerPromotionCodeNullException(PromotionCodeException ex){
        return new ErrorResponse(ConstantPayment.PROMOTION_CODE_NULL.getValue(),ex.getMessage());
        }

@org.springframework.web.bind.annotation.ExceptionHandler(RealAmountException.class)
public ErrorResponse handlerRealAmountMistakeException(RealAmountException ex){
        return new ErrorResponse(ConstantPayment.REAL_AMOUNT_MISTAKE.getValue(),ex.getMessage());
        }

@org.springframework.web.bind.annotation.ExceptionHandler(TokenKeyException.class)
public ErrorResponse handlerTokenKeyExistsException(TokenKeyException ex){
        return new ErrorResponse(ConstantPayment.TOKEN_KEY_EXISTS.getValue(),ex.getMessage());
        }
@org.springframework.web.bind.annotation.ExceptionHandler(TimeOutException.class)
public ErrorResponse handlerTimeOutReplyException(TimeOutException ex){
        return new ErrorResponse(ConstantPayment.MAINTENANCE_SYSTEM.getValue(),ex.getMessage());
        }
        }

