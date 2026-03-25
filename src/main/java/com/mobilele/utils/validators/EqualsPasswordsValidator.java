package com.mobilele.utils.validators;

import com.mobilele.model.DTOs.User.UserRegister;
import com.mobilele.utils.anotations.EqualsPasswordsValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EqualsPasswordsValidator implements ConstraintValidator<EqualsPasswordsValidation, UserRegister> {

        String message;

    @Override
    public void initialize(EqualsPasswordsValidation constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(UserRegister userRegister, ConstraintValidatorContext constraintValidatorContext) {

            boolean areNotNull= userRegister.getPassword() != null && userRegister.getConfirmPassword() != null;
            if (areNotNull && userRegister.getPassword().equals(userRegister.getConfirmPassword())){
                    return true;
            }else {

                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate(message)
                        .addPropertyNode("confirmPassword")
                        .addConstraintViolation();

                return false;
            }
    }
}
