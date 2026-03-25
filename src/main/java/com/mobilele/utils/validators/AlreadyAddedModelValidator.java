package com.mobilele.utils.validators;

import com.mobilele.repository.ModelRepository;
import com.mobilele.utils.anotations.AlreadyAddedModelValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class AlreadyAddedModelValidator implements ConstraintValidator<AlreadyAddedModelValidation, String>{

    private final ModelRepository modelRepository;

    public AlreadyAddedModelValidator(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        return this.modelRepository.findByName(name).isEmpty();
        }
}
