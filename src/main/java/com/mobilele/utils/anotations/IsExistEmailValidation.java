package com.mobilele.utils.anotations;


import com.mobilele.utils.validators.IsExistEmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;


import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Constraint(validatedBy = IsExistEmailValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface IsExistEmailValidation {

     String message() default "This email exist!";

     Class<?>[] groups() default {};

     Class<? extends Payload>[] payload() default {};
}
