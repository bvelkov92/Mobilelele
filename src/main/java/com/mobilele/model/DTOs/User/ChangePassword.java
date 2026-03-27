package com.mobilele.model.DTOs.User;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChangePassword {
    @NotBlank
    private String password;

    @NotBlank
    @Size(min = 6)
    private String newPassword;

    @NotBlank
    private String confirmPassword;

    public void setOldPassword(String oldPassword) {
        this.password = oldPassword.trim();
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword.trim();
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword.trim();
    }
}
