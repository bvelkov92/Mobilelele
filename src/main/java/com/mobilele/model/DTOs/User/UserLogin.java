package com.mobilele.model.DTOs.User;

import com.mobilele.utils.anotations.EqualsPasswordsValidation;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsPasswordsValidation
public class UserLogin {

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
