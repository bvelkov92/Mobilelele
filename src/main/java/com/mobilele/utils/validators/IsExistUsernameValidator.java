package com.mobilele.utils.validators;

import com.mobilele.model.entity.Users;
import com.mobilele.repository.UserRepository;
import com.mobilele.utils.anotations.IsExistUsernameValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IsExistUsernameValidator implements ConstraintValidator<IsExistUsernameValidation, String> {
    String message;
    private final UserRepository userRepository;

    public IsExistUsernameValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(IsExistUsernameValidation constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        Users foundUser = this.userRepository.findUserByUsername(username).orElse(null);
        return foundUser == null;
    }
}
