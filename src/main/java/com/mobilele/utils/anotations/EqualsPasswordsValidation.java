package com.mobilele.utils.anotations;

import com.mobilele.utils.validators.EqualsPasswordsValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Constraint(validatedBy = EqualsPasswordsValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface EqualsPasswordsValidation {

    String message() default "Passwords miss match!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
