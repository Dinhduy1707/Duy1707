package com.example.valida;

import com.example.model.Product;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
public class DuyValidator implements ConstraintValidator<DuyValidate, String>{


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) return false;
        else{
            return true;
        }
    }
}
