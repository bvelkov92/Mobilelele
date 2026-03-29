package com.mobilele.utils.validators;

import com.mobilele.model.DTOs.User.EditProfile;
import com.mobilele.model.entity.Users;
import com.mobilele.repository.UserRepository;
import com.mobilele.utils.anotations.IsExistEmailValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IsExistEmailValidator implements ConstraintValidator<IsExistEmailValidation, EditProfile> {
    String message;
    private final UserRepository userRepository;

    public IsExistEmailValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(IsExistEmailValidation constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(EditProfile editProfile, ConstraintValidatorContext context) {
        Users foundUserByEmail = this.userRepository.findUserByEmail(editProfile.getEmail()).orElse(null);
        Users foundUserByUsername = this.userRepository.findUserByUsername(editProfile.getUsername()).orElse(null);

        if (foundUserByEmail!=null && foundUserByUsername!=null) {
            boolean isUsedByOtherUser = isFound(foundUserByEmail.getUsername(), foundUserByUsername.getUsername());
            boolean isUsedBySameUser = isFound(foundUserByUsername.getEmail(), editProfile.getEmail());
            return !isUsedByOtherUser || isUsedBySameUser;
        }
        return false;
    }

    private boolean isFound(String option1, String option2){
        return (option1.trim().equals(option2.trim()));

    }
}
