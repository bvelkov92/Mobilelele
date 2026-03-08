package com.mobilele.model.DTOs.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegister {

   @NotBlank
   @Size(min = 6)
   private String username;

   @Email
   @NotBlank
   private String email;


   //TODO: Validation of passwords.
   @Size(min = 6, max = 12)
   @NotBlank
   private String password;

   @NotBlank
   private String confirmPassword;

}
