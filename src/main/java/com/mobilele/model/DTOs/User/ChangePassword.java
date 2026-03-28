package com.mobilele.model.DTOs.User;

import com.mobilele.utils.anotations.EqualsPasswordChangePasswordValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@EqualsPasswordChangePasswordValidation
public class ChangePassword {

    @NotBlank

    private String password;

    @NotBlank
    @Size(min = 6)
    private String newPassword;

    @NotBlank
    private String confirmPassword;

    public void setPassword(String password) {
        this.password = password.trim();
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword.trim();
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword.trim();
    }
}
