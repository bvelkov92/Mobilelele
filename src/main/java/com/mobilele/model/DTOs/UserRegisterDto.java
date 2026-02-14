package com.mobilele.model.DTOs;

import jakarta.validation.constraints.Email;
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
public class UserRegisterDto {

   @NotNull
   @Size(min = 6)
   private String username;

   @Email
   private String email;

   @Size(min = 6, max = 12)
   @NotNull
   private String password;

   @NotNull
   private String confirmPassword;

}
