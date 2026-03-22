package com.mobilele.utils.validators;

import com.mobilele.utils.AlreadyAddedModelValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AlreadyAddedModelValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AlreadyAddedModelValidation {

    String message() default "This model already added!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
