package com.mobilele.utils.validators;

import com.mobilele.model.DTOs.User.ChangePassword;
import com.mobilele.utils.anotations.EqualsPasswordChangePasswordValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EqualsPasswordChangePasswordValidator implements ConstraintValidator<EqualsPasswordChangePasswordValidation, ChangePassword> {
    String message;

    @Override
    public void initialize(EqualsPasswordChangePasswordValidation constraintAnnotation) {
       this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(ChangePassword changePassword, ConstraintValidatorContext context) {

        if (changePassword.getNewPassword().equals(changePassword.getConfirmPassword())){
            return true;
        }else {
           context.disableDefaultConstraintViolation();
           context.buildConstraintViolationWithTemplate(message)
                   .addPropertyNode("confirmPassword")
                   .addConstraintViolation();

           return false;
        }
    }
}
