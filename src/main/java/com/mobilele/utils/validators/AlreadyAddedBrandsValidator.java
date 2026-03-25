package com.mobilele.utils.validators;

import com.mobilele.repository.BrandRepository;
import com.mobilele.utils.anotations.AlreadyAddedBrandsValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AlreadyAddedBrandsValidator implements ConstraintValidator<AlreadyAddedBrandsValidation, String> {

    private final BrandRepository brandRepository;

    public AlreadyAddedBrandsValidator(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public boolean isValid(String brandName, ConstraintValidatorContext constraintValidatorContext) {

        if (brandName==null){
            return true;
        }
        return this.brandRepository.findByName(brandName).isEmpty();
    }
}
