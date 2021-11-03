package com.example.valida;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DuyValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DuyValidate {
    String message() default "Can not be null or empty";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
