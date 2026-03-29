package com.mobilele.utils.anotations;


import com.mobilele.utils.validators.IsExistUsernameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;


import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Constraint(validatedBy = IsExistUsernameValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface IsExistUsernameValidation {

     String message() default "This username exist!";

     Class<?>[] groups() default {};

     Class<? extends Payload>[] payload() default {};
}
