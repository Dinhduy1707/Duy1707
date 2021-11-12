package com.example.valida;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
public class NotBlankIplm implements ConstraintValidator<NotBlank, String>{
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && !value.isEmpty() && !value.isBlank();
    }
}
