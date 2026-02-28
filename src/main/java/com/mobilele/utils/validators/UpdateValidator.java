package com.mobilele.utils.validators;
import com.mobilele.utils.UpdateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UpdateValidation.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UpdateValidator {

    String message() default "The value is same!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
