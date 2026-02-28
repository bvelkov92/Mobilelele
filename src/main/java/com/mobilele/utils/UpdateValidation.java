package com.mobilele.utils;

import com.mobilele.utils.validators.UpdateValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UpdateValidation implements ConstraintValidator<UpdateValidator, String> {


    @Override
    public void initialize(UpdateValidator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {


        return false;
    }
}
