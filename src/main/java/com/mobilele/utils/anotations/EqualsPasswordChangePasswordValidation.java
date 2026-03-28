package com.mobilele.utils.anotations;

import com.mobilele.utils.validators.EqualsPasswordChangePasswordValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Constraint(validatedBy = EqualsPasswordChangePasswordValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface EqualsPasswordChangePasswordValidation {
    String message() default "Passwords miss match!";
}
