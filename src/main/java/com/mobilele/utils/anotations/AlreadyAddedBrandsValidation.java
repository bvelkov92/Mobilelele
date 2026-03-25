package com.mobilele.utils.anotations;
import com.mobilele.utils.validators.AlreadyAddedBrandsValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AlreadyAddedBrandsValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AlreadyAddedBrandsValidation {

    String message() default "This brand already added!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
