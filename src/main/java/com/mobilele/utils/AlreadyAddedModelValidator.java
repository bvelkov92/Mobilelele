package com.mobilele.utils;

import com.mobilele.repository.ModelRepository;
import com.mobilele.utils.validators.AlreadyAddedModelValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class AlreadyAddedModelValidator implements ConstraintValidator<AlreadyAddedModelValidation, String>{

    private final ModelRepository modelRepository;

    public AlreadyAddedModelValidator(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Override
    public void initialize(AlreadyAddedModelValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        return this.modelRepository.findByName(name).isEmpty();
        }
}
